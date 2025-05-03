/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Calendar;

/**
 *
 * @author javie
 */
public class Sismo {
    private Calendar fecha;
    private double profundidad;
    private TipoOrigen tipoOrigen;
    private double magnitud;
    private EscalaMagnitud escalaMagnitud;
    private double latitud;
    private double longitud;
    private String descripcionUbicacion;
    private Provincia provincia;
    private boolean esMaritimo;
    
    public Sismo() {
        
    }
}
