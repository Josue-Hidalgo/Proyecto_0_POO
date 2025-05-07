/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author javie
 */
public class ValidadorDatos {
    public static Object[] stringToDouble(String str) {
        Object[] par = new Object[2];
        try {
             par[0] = Double.parseDouble(str);
             par[1] = true;
             return par;
        }
        catch (NumberFormatException | NullPointerException e) {
            par[0] = 0.0D;
            par[1] = false;
            return par;
        }
    }
}
