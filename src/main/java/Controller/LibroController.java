/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.github.javafaker.Faker;
import excepcion.ExcepcionAnioInvalido;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Anio;
import model.Libros;
import model.Mes;
import static testin.Utilitario.randInt;


/**
 *
 * @author wcade
 */
public class LibroController {
    private List<Libros> biblioteca;
    private List<Anio> anios;

    public List<Anio> getAnios() {
        return anios;
    }

    
    public LibroController(ArrayList<Libros> biblioteca) {        
        this.biblioteca = biblioteca;
    }

    public LibroController() {
        biblioteca = new ArrayList();
    }
    public LibroController(Libros generador) {
        List<Libros>  lista_generadores = new ArrayList<Libros>();
        lista_generadores.add(generador);        
        this.biblioteca = lista_generadores;
    }
    
    public void addLibro(Libros generador){
        Mes auxMes= generador.getMes();
        System.out.println("===+++++++++++++++++++>"+auxMes);
        auxMes.addLibro(generador);
        this.biblioteca.add(generador);
    }

    
    public List<Libros> getBiblioteca() {
        return biblioteca;
    }
    
    public List<Libros> filtrar(List<Mes> meses,String Numero_revista){
        List<Libros> lib = new ArrayList<Libros>();
        List<Libros> libtot = this.getBiblioteca();
        for (int i = 0; i < libtot.size(); i++) {
            Libros auxLib = libtot.get(i);
            if(auxLib.getNumero().equals(Numero_revista) ){//ve si cumple el test numero                
                if(meses.size() == 0){
                   lib.add(auxLib); 
                }
                for (int j = 0; j < meses.size(); j++) {//ve si es del tipo mes seleccionado
                    Mes auxMes = meses.get(j);
                    if(auxLib.getMes().getMes() == auxMes.getMes()){
                        lib.add(auxLib);
                    }
                }
            }            
        }   
        return libtot;
    }

    public void setBiblioteca(ArrayList<Libros> biblioteca) {
        this.biblioteca = biblioteca;
    }
    
    public void llenarAnios() throws ExcepcionAnioInvalido{
        this.llenarAnios(2018);
    }
    
    /**
     * llena los anios desde 2018
     */
    public void llenarAnios(int anioinit) throws ExcepcionAnioInvalido{        
        anios = new ArrayList();
        for (int i = 0; i < 20; i++) {
            int anio = anioinit - i;
            Anio a = new Anio();
            a.setAnio(anio);
            ArrayList<Mes> ms;
            ms = new ArrayList();
            for (int j = 1; j <= 12; j++) {
                Mes m = new Mes(anio);
                m.setMes(j);

                ms.add(m);
            }
            a.setMess(ms);
            anios.add(a);
        }
    }
    public void LLenarLibreria() throws FileNotFoundException, ParseException{
        this.LLenarLibreria(200);
    }
    public void LLenarLibreria( int libros) throws FileNotFoundException, ParseException{
                
        
        Faker faker = new Faker();
        for (int i = 0; i < libros; i++) {
            Libros l = new Libros();
            l.setAuthor(faker.book().author());
            l.setTitle(faker.book().title());
            l.setGenre(faker.book().genre());
            l.setPublisher(faker.book().publisher());
            l.setId(i);
            l.setNumero(""+faker.idNumber().hashCode());
            Date dNow = new Date();

            String date_s = "1985-01-18 00:00:00.0";

            // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dateFrom = dt.parse(date_s);

            l.setCreated_at(faker.date().between(dateFrom, dNow));
            l.setUpdate_at(faker.date().between(dateFrom, dNow));
            Anio a_util = anios.get(randInt(0, 20));
            Mes m = a_util.getMess().get(randInt(1, 12));
            l.setMes(m);
            biblioteca.add(l);
            
        }
        
    }
    
}
