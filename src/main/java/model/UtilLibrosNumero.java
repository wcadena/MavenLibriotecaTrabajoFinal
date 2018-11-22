/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wcade
 */
public class UtilLibrosNumero implements Comparable<UtilLibrosNumero> {

    private String titulo;
    private List<String> numeros;

    public UtilLibrosNumero(String titulo) {
        this.titulo = titulo;
        numeros = new ArrayList();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<String> numeros) {
        this.numeros = numeros;
    }

    public static int findCustomerByid(List<UtilLibrosNumero> utilLibrosNumeros, String titulo) {
        for (int i = 0; i < utilLibrosNumeros.size(); i++) {
            UtilLibrosNumero aux = utilLibrosNumeros.get(i);
            if (aux.getTitulo().equals(titulo) ) {
                
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "UtilLibrosNumero{" + "titulo=" + titulo + ", numeros=" + numeros + '}';
    }

    @Override
    public int compareTo(UtilLibrosNumero o) {
        int numero1 = this.numeros.size();
        int numero2 = o.numeros.size();
        return Integer.valueOf(numero2).compareTo(numero1);
    }
    
    
}
