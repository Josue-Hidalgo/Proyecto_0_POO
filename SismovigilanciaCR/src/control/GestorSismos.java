/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import modelo.Excel;
import java.util.ArrayList;
import java.util.List;
import modelo.Sismo;

/**
 *
 * @author javie
 */
public class GestorSismos {
    ArrayList<Sismo> sismos = new ArrayList<Sismo>();
    Excel manejadorExcel = new Excel();
    
    public GestorSismos() {
    }
    
    // Setters
    //==========================================================================
    public void setManejadorExcel(Excel manejadorExcel) {
        this.manejadorExcel = manejadorExcel;
    }
    //==========================================================================
    
    // Getters
    //==========================================================================
    public Excel getManejadorExcel() {
        return manejadorExcel;
    }
    //==========================================================================
    
    /**
     * Agregar un nuevo sismo al archivo .xslx que se hay especificado
     * @param sismo 
     */
    public void agregarSismo(Sismo sismo) {
        sismos.add(sismo);
    }
    
    public ArrayList<Sismo> getSismos() {
        return sismos;
    }

    // Método para guardar sismos en Excel
    public void guardarSismos() {
        manejadorExcel.guardarSismos(sismos); // Asume que Excel tiene este método
    }
    public void obtenerSismos() {
        manejadorExcel.obtenerSismos();
    }

}
