/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testin;

import java.util.Random;

/**
 *
 * @author wcade
 */
public class Utilitario {
    public static int randInt(int min, int max) {
        Random foo = new Random();
        int randomNumber = foo.nextInt(max - min) + min;
        if (randomNumber == min) {
            // Since the random number is between the min and max values, simply add 1
            return min + 1;
        } else {
            return randomNumber;
        }
    }
}
