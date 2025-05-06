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
    private String fecha;
    private String hora;
    private double profundidad;
    private TipoOrigen tipoOrigen;
    private double magnitud;
    private double latitud;
    private double longitud;
    private String descripcionUbicacion;
    private Provincia provincia;
    private boolean esMaritimo;
    
    public Sismo() {
    }
    
    // Setters
    //==========================================================================
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public void setFecha(Calendar fecha) {
        int dia, mes, annio;
        dia = fecha.get(Calendar.DAY_OF_MONTH);
        mes = fecha.get(Calendar.MONTH) + 1;
        annio = fecha.get(Calendar.YEAR);
        this.fecha = Integer.toString(dia) + '-' + Integer.toString(mes) + '-' + Integer.toString(annio);
    }
    
    public void setHora(String hora) {
        this.fecha = hora;
    }
    
    public void setHora(Calendar hora) {
        int horas, minutos, segundos;
        horas = hora.get(Calendar.HOUR_OF_DAY);
        minutos = hora.get(Calendar.MINUTE);
        segundos = hora.get(Calendar.SECOND);
        this.hora = Integer.toString(horas) + ':' + Integer.toString(minutos) + ':' + Integer.toString(segundos);
        
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
    public String getFecha() {
        return fecha;
    }
    
    public String getHora() {
        return hora;
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
        if (magnitud >= 2.0D && magnitud < 6.9D) {
            return EscalaMagnitud.RITCHER_ML;
        }
        else if (magnitud >= 6.9D) {
            return EscalaMagnitud.MOMENT_MW;
        }
        else {
           return EscalaMagnitud.INDEFINIDO; 
        }
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
    
    public ClasificacionSismo getClasificacionSismo() {
        if (magnitud < 2.0D) {
            return ClasificacionSismo.MICRO;
        }
        else if (magnitud >= 2.0D && magnitud < 4.0D) {
            return ClasificacionSismo.MENOR;
        }
        else if (magnitud >= 4.0D && magnitud < 5.0D) {
            return ClasificacionSismo.LIGERO;
        }
        else if (magnitud >= 5.0D && magnitud < 6.0D) {
            return ClasificacionSismo.MODERADO;
        }
        else if (magnitud >= 6.0D && magnitud < 7.0D) {
            return ClasificacionSismo.FUERTE;
        }
        else if (magnitud >= 7.0D && magnitud < 8.0D) {
            return ClasificacionSismo.MAYOR;
        }
        else if (magnitud >= 8.0D && magnitud < 10.0D) {
            return ClasificacionSismo.GRAN;
        }
        else {
           return ClasificacionSismo.EPICO; 
        }
    }
    //==========================================================================
}
