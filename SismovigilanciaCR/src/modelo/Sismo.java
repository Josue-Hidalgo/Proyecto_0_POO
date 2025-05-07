/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Calendar;
import control.ValidadorDatos;

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
    
    public Sismo(String[] argumentos) {
        this.setFecha(argumentos[0]);
        this.setHora(argumentos[1]);
        this.setProfundidad(argumentos[2]);
        this.setTipoOrigen(argumentos[3]);
        this.setMagnitud(argumentos[4]);
        this.setLatitud(argumentos[5]);
        this.setLongitud(argumentos[6]);
        this.setDescripcionUbicacion(argumentos[7]);
        this.setProvincia(argumentos[8]);
        this.setEsMaritimo(argumentos[9]);
    }
    
    // Setters
    //==========================================================================
    public void set(int indiceArgumento, String dato) {
        switch (indiceArgumento) {
            case 0 -> setFecha(dato);
            case 1 -> setHora(dato);
            case 2 -> setProfundidad(dato);
            case 3 -> setTipoOrigen(dato);
            case 4 -> setMagnitud(dato);
            case 5 -> setLatitud(dato);
            case 6 -> setLongitud(dato);
        }
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Ajusta la fecha de ocurrencia del sismo.
     * @param fecha Instancia de tipo Calendar con la información de una fecha.
     */
    public void setFecha(Calendar fecha) {
        int dia, mes, annio;
        dia = fecha.get(Calendar.DAY_OF_MONTH);
        mes = fecha.get(Calendar.MONTH) + 1;
        annio = fecha.get(Calendar.YEAR);
        this.fecha = Integer.toString(dia) + '-' + Integer.toString(mes) + '-' + Integer.toString(annio);
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    /**
     * Ajusta la hora de ocurrencia del sismo.
     * @param hora Insntancia de tipo Calendar con la información de la hora.
     */
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
    
    /**
     * Ajusta la profundidad del en que ocurrió el sismo.
     * @param profundidad Hilera con el valor, en kilómetros, de la profundidad.
     */
    public void setProfundidad(String profundidad) {
        this.profundidad = (double) ValidadorDatos.stringToDouble(profundidad)[0];
    }
    
    public void setTipoOrigen(TipoOrigen tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }
    
    /**
     * Ajusta el tipo de origen del sismo.
     * @param tipoOrigen Hilera con la descripción del origen del sismo.
     */
    public void setTipoOrigen(String tipoOrigen) {
        tipoOrigen = tipoOrigen.toUpperCase();
        try {
            this.tipoOrigen = TipoOrigen.valueOf(tipoOrigen);
        }
        catch (Exception e) {
            this.tipoOrigen = TipoOrigen.INDEFINIDO;
        }
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }
    
    /**
     * Ajusta la magnitud con que ocurrió el sismo.
     * @param magnitud Hilera con el valor de la magnitud del sismo.
     */
    public void setMagnitud(String magnitud) {
        this.magnitud = (double) ValidadorDatos.stringToDouble(magnitud)[0];
    }
    
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    
    /**
     * Ajusta la latitud en donde ocurrió el sismo.
     * @param latitud Hilera con el valor de la latitud en donde ocurrió el
     * sismo.
     */
    public void setLatitud(String latitud) {
        this.latitud = (double) ValidadorDatos.stringToDouble(latitud)[0];
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    
    /**
     * Ajusta la longitud en donde ocurrió el sismo.
     * @param longitud Hilera con el valor de la latitud en donde ocurrió el
     * sismo.
     */
    public void setLongitud(String longitud) {
        this.longitud = (double) ValidadorDatos.stringToDouble(longitud)[0];
    }
    
    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
    /**
     * Ajusta la provincia en donde ocurrió el sismo.
     * @param provincia Hilera con el nombre de la provincia en donde ocurrió 
     */
    public void setProvincia(String provincia) {
        provincia = provincia.toUpperCase();
        try {
            this.provincia = Provincia.valueOf(provincia);
        }
        catch (Exception e) {
            this.provincia = Provincia.SIN_ASIGNAR;
        }
    }

    public void setEsMaritimo(boolean esMaritimo) {
        this.esMaritimo = esMaritimo;
    }
    
    public void setEsMaritimo(String esMaritimo) {
        esMaritimo = esMaritimo.toUpperCase();
        if (esMaritimo.equals("SI") || esMaritimo.equals("SÍ")) {
            this.esMaritimo = true;
        }
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

    @Override
    public String toString() {
        return "Sismo{" + "fecha=" + fecha + ", hora=" + hora + ", profundidad=" + profundidad + ", tipoOrigen=" + tipoOrigen + ", magnitud=" + magnitud + ", latitud=" + latitud + ", longitud=" + longitud + ", descripcionUbicacion=" + descripcionUbicacion + ", provincia=" + provincia + ", esMaritimo=" + esMaritimo + "}\n";
    }
    
    
}
