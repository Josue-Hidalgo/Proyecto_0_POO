/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author javie, Emilio
 */
public class ValidadorDatos {
    public ValidadorDatos(){}
    
    public boolean validarDatosSismo(double profundidad, double magnitud, double latitud, double longitud, String descripcionUbicacion){
        if (profundidad < 0)
            return false;
        if (magnitud <= 0)
            return false;
        if (latitud < -90 || latitud > 90 || longitud < -180 || longitud > 180)
            return false;
        if (descripcionUbicacion == null || descripcionUbicacion.trim().isEmpty())
            return false;
        
        return true;
    }
    
    public boolean validarCorreo(String correo){
        if (correo == null || correo.trim().isEmpty())
            return false;
        
        // un @ que no este ni al inicio ni al final
        int arrobaIndex = correo.indexOf('@');
        if (arrobaIndex <= 0 || arrobaIndex == correo.length() - 1)
            return false;
        
        // dominio tiene al menos un punto que no este ni al inicio ni al final
        String dominio = correo.substring(arrobaIndex + 1);
        if (dominio.indexOf('.') == -1 || dominio.startsWith(".") || dominio.endsWith("."))
            return false;
        
        // caracteres ilegales 
        String local = correo.substring(0, arrobaIndex);
        if (local.contains(" ") || local.contains(".."))
            return false;
        
        return true;
    }
    
    public boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty())
            return false;
        
        for (char c : telefono.toCharArray()) {
            if (!(Character.isDigit(c) || c == '+' || c == '-' || c == ' ')) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean validarFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty())
            return false;
        
        String[] partes = fecha.split("-");
        if (partes.length != 3)
            return false;
        
        try {
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt(partes[2]);
            
            if (dia < 1 || dia > 31)
                return false;
            if (mes < 1 || mes > 12)
                return false;
            if (anio < 1900)
                return false;
           
        // en caso de que parseInt falle
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public boolean validarHora(String hora) {
        if (hora == null || hora.trim().isEmpty())
            return false;
        
        String[] partes = hora.split(":");
        if (partes.length != 3)
            return false;
        
        try {
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);
            
            if (horas < 0 || horas > 23)
                return false;
            if (minutos < 0 || minutos > 59)
                return false;
            if (segundos < 0 || segundos > 59)
                return false;
            
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
