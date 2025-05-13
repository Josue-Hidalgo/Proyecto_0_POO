/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.Calendar;

/**
 *
 * @author javie
 */
public class ValidadorDatos {
    public static Object[] stringToDouble(String string) {
        Object[] par = new Object[2];
        try {
             par[0] = Double.parseDouble(string);
             par[1] = true;
             return par;
        }
        catch (NumberFormatException | NullPointerException e) {
            par[0] = 0.0D;
            par[1] = false;
            return par;
        }
    }
    
    public static String calendarToString(Calendar calendar) {
        int dia, mes, annio;
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH) + 1;
        annio = calendar.get(Calendar.YEAR);
        return Integer.toString(dia) + '/' + Integer.toString(mes) + '/' + Integer.toString(annio);
    }
    
    public static String stringToDateFormatString(String string) {
        Calendar fechaCreada = Calendar.getInstance();
        Object[] par = stringToDouble(string);
        if ((boolean) par[1]) {
            long cantidadDias = (long) ((double) par[0]);
            long cantidadMilis = 86_400_000L * (cantidadDias - 25_568L);
            fechaCreada.setTimeInMillis(cantidadMilis);
        }
        return calendarToString(fechaCreada);
    }
}
