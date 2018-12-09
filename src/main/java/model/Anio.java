/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author wcade
 */
public class Anio {
    int anio;
    private ArrayList<Mes> mess;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public ArrayList<Mes> getMess() {
        return mess;
    }

    public void setMess(ArrayList<Mes> mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return anio+"";
    }
    
}
