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
public class UtilLibrosNumero {

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

    public static UtilLibrosNumero findCustomerByid(List<UtilLibrosNumero> utilLibrosNumeros, String titulo) {
        for (int i = 0; i < utilLibrosNumeros.size(); i++) {
            UtilLibrosNumero aux = utilLibrosNumeros.get(i);
            if (aux.getTitulo() == titulo) {
                return aux;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "UtilLibrosNumero{" + "titulo=" + titulo + ", numeros=" + numeros + '}';
    }
    
    
}
