/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import modelo.Excel;
import java.util.ArrayList;
import modelo.Sismo;

/**
 *
 * @author javie
 */
public class GestorSismos {
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
        
    }
    
    public void getListaSismos() {
        manejadorExcel.getSismos();
    }
}
