/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author wcade
 */
public class Mes {
    private int mes;
    private String nombre;
   

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
        int num=mes;
        switch (num){
        case 1:
            this.setNombre("Enero");
            break;
        case 2:
            this.setNombre("Febrero");
            break;
        case 3:
            this.setNombre("Marzo");
            break;
        case 4:
            this.setNombre("Abril");
            break;
        case 5:
            this.setNombre("Mayo");
            break;
        case 6:
            this.setNombre("Junio");
            break;
        case 7:
            this.setNombre("Julio");
            break;
        case 8:
            this.setNombre("Agosto");
            break;
        case 9:
            this.setNombre("Septiembre");
            break;
        case 10:
            this.setNombre("Octubre");
            break;
        case 11:
            this.setNombre("Noviembre");
            break;    
        case 12:
            this.setNombre("Diciembre");
            break;
        default:
            this.setNombre("NO-ES_MES-CORECTO");
            break;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    @Override
    public String toString() {
        return "Mes{" + "mes=" + mes + ", nombre=" + nombre + "" + '}';
    }
    
}
