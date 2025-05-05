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
    
    // Setters
    //==========================================================================
    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public void setTipoOrigen(TipoOrigen tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public void setEscalaMagnitud(EscalaMagnitud escalaMagnitud) {
        this.escalaMagnitud = escalaMagnitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public void setEsMaritimo(boolean esMaritimo) {
        this.esMaritimo = esMaritimo;
    }
    //==========================================================================
    
    // Getters
    //==========================================================================
    public Calendar getFecha() {
        return fecha;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public TipoOrigen getTipoOrigen() {
        return tipoOrigen;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public EscalaMagnitud getEscalaMagnitud() {
        return escalaMagnitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public boolean isEsMaritimo() {
        return esMaritimo;
    }
    //==========================================================================
}
