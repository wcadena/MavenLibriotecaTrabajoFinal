/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author wcade
 */
public class Libros implements Comparable<Libros> {

    private int id;
    private String author;
    private String genre;
    private String publisher;
    private String title;
    
    private Integer numero;
    
    private Date created_at;
    private Date update_at;
    private Date delete_at;
    
    private Mes mes;

    public void setId_secuencial(int id) {
        try {
            //Whatever the file path is.
            File statText = new File("autonumerico.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);
            w.write(""+id);
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
        }
    }

    public int getId_secuencial() throws FileNotFoundException {
        File file = new File("autonumerico.txt");
        //System.out.println("======>"+file.getPath());
        Scanner scanner = new Scanner(file);
        List<Integer> integers = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int dato = scanner.nextInt();
                integers.add(dato);
                return dato;
            } else {
                scanner.next();
            }
        }
        return -1;
    }
    public int getIdSec() throws FileNotFoundException{
        int dato = this.getId_secuencial();
        this.setId_secuencial(dato+1);
        return dato;
    }
    
    public Libros() throws FileNotFoundException {
        this.id = this.getIdSec();
        this.created_at = new Date();;
        this.update_at = new Date();;
        this.delete_at = new Date();;
    }

    public Libros(String author, String genre, String publisher, String title, Integer numero) throws FileNotFoundException {
        this.id = this.getIdSec();
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.title = title;
        this.numero = numero;
    }
     
    public Libros( String author, String genre, String publisher, String title, Integer numero, Mes mes) throws FileNotFoundException {
        this.id = this.getIdSec();
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.title = title;
        this.numero = numero;
        this.created_at = new Date();;
        this.update_at = new Date();;
        this.delete_at = new Date();;
        this.mes = mes;
    }
    
    Predicate<Libros> nameEqualsTo(final String name) {
    return new Predicate<Libros>() {

        public boolean apply(Libros dataPoint) {
            return dataPoint.title.equals(name);
        }

        @Override
        public boolean test(Libros t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Predicate<Libros> and(Predicate<? super Libros> other) {
            return Predicate.super.and(other); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Predicate<Libros> negate() {
            return Predicate.super.negate(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Predicate<Libros> or(Predicate<? super Libros> other) {
            return Predicate.super.or(other); //To change body of generated methods, choose Tools | Templates.
        }
    };
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Date getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(Date delete_at) {
        this.delete_at = delete_at;
    }

    public Mes getMes() {
        return mes;
    }
    
    /**
     * se crea mutador de fecha de libro para presentar los anios del mes.
     * @return 
     */
    public String getAnio() {
        return mes.getAnio()+"";
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        //return "Libros{" + "id=" + id + ", author=" + author + ", genre=" + genre + ", publisher=" + publisher + ", title=" + title + ", numero=" + numero + ", created_at=" + created_at + ", update_at=" + update_at + ", delete_at=" + delete_at + ", mes=" + mes + '}';
        //return "Libros{" + "id=" + id + ", author=" + author + ", genre=" + genre + ", publisher=" + publisher + ", title=" + title + ", numero=" + numero + ", mes=" + mes + '}';
        return "Titulo: " + title + " Autor: " + author + " Genero: " + genre + " Publicadora: " + publisher + " Numero: " + numero + " Mes: " + mes + " " ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.author);
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.numero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libros other = (Libros) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Libros o) {
        //return this.numero.compareTo(o.numero);        
        //return this.title.compareTo(o.title);        
        return Integer.valueOf(this.id).compareTo(o.id);
    }         
    /*Comparator for sorting the list by roll no*/
    public static Comparator<Libros> PorNumero = new Comparator<Libros>() {

	public int compare(Libros s1, Libros s2) {

	   //int rollno1 = s1.getRollno();
	   //int rollno2 = s2.getRollno();

	   /*For ascending order*/
	   //return rollno1-rollno2;
            return s1.numero.compareTo(s2.numero);
	   /*For descending order*/
	   //rollno2-rollno1;
   }};
    
    public static Comparator<Libros> PorTitulo = new Comparator<Libros>() {

	public int compare(Libros s1, Libros s2) {
	   String StudentName1 = s1.getTitle().toUpperCase();
	   String StudentName2 = s2.getTitle().toUpperCase();

	   //ascending order
	   return StudentName1.compareTo(StudentName2);

	   //descending order
	   //return StudentName2.compareTo(StudentName1);
    }};
}
