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
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author wcade
 */
public class Libros {

    private int id;
    private String author;
    private String genre;
    private String publisher;
    private String title;
    
    private String numero;
    
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
        System.out.println("======>"+file.getPath());
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

    public Libros(String author, String genre, String publisher, String title, String numero) throws FileNotFoundException {
        this.id = this.getIdSec();
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.title = title;
        this.numero = numero;
    }
     
    public Libros( String author, String genre, String publisher, String title, String numero, Mes mes) throws FileNotFoundException {
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
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

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "Libros{" + "id=" + id + ", author=" + author + ", genre=" + genre + ", publisher=" + publisher + ", title=" + title + ", numero=" + numero + ", created_at=" + created_at + ", update_at=" + update_at + ", delete_at=" + delete_at + ", mes=" + mes + '}';
    }
    
    
    
}
