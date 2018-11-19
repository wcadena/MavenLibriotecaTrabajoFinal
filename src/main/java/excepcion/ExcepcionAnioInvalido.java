/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 *
 * @author wcade
 */
public class ExcepcionAnioInvalido extends Exception {
    public ExcepcionAnioInvalido(String msg) {
        super(msg);
        // throw new ExcepcionAnioInvalido("Números fuera del intervalo");
    }
}
