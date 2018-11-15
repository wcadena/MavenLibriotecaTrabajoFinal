/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import model.Libros;

/**
 *
 * @author wcade
 */
public class LibroController {
    private List<Libros> biblioteca;

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
        this.biblioteca.add(generador);
    }

    
    public List<Libros> getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(ArrayList<Libros> biblioteca) {
        this.biblioteca = biblioteca;
    }
    
}
