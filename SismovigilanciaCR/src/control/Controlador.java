/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import modelo.ClasificacionSismo;
import modelo.Excel;
import modelo.Notificacion;
import modelo.Provincia;
import modelo.Sismo;
import modelo.Suscriptor;
import modelo.TipoOrigen;

/**
 *
 * @author javie
 */
public class Controlador {
    private GestorSismos gestorSismos;
    private ControladorEstadisticas controladorEstadisticas;
    private ControladorMapa controladorMapa;
    private ValidadorDatos validador;
    private Notificacion notificacion;
    private String rutaArchivoSismos;
    private String rutaArchivoSuscriptores;
    
    public Controlador() {
        gestorSismos = new GestorSismos();
        controladorEstadisticas = new ControladorEstadisticas();
        controladorMapa = new ControladorMapa();
        validador = new ValidadorDatos();
        notificacion = new Notificacion();
    }
    
    public void inicializar(String rutaArchivoSismos, String rutaArchivoSuscriptores) {
        this.rutaArchivoSismos = rutaArchivoSismos;
        this.rutaArchivoSuscriptores = rutaArchivoSuscriptores;
        
        // datos de sismos
        Excel manejadorExcel = new Excel(rutaArchivoSismos);
        gestorSismos.setManejadorExcel(manejadorExcel);
        cargarSismos();
        
        // datos de suscriptores
        notificacion.cargarSuscriptores(rutaArchivoSuscriptores);
    }
    
    public void cargarSismos() {
        gestorSismos.obtenerSismos();
    }
    
    public List<Sismo> obtenerSismos() {
        return gestorSismos.getSismos();
    }
    
    public boolean agregarSismo(String fecha, String hora, double profundidad, 
            TipoOrigen tipoOrigen, double magnitud, double latitud, double longitud,
            String descripcionUbicacion, Provincia provincia, boolean esMaritimo) {
        
        // Validar datos
        if (!validador.validarFecha(fecha) || !validador.validarHora(hora)) {
            return false;
        }
        
        // Crear nuevo sismo
        Sismo nuevoSismo = new Sismo();
        nuevoSismo.setFecha(fecha);
        nuevoSismo.setHora(hora);
        nuevoSismo.setProfundidad(profundidad);
        nuevoSismo.setTipoOrigen(tipoOrigen);
        nuevoSismo.setMagnitud(magnitud);
        nuevoSismo.setLatitud(latitud);
        nuevoSismo.setLongitud(longitud);
        nuevoSismo.setDescripcionUbicacion(descripcionUbicacion);
        nuevoSismo.setProvincia(provincia);
        nuevoSismo.setEsMaritimo(esMaritimo);
        
        // Agregar sismo al gestor
        gestorSismos.agregarSismo(nuevoSismo);
        
        // Guardar en archivo Excel
        gestorSismos.guardarSismos();
        
        // notificar
//        notificarSuscriptores(nuevoSismo);
        
        return true;
    }
    
    public boolean agregarSismoAhora(double profundidad, TipoOrigen tipoOrigen, 
            double magnitud, double latitud, double longitud, 
            String descripcionUbicacion, Provincia provincia, boolean esMaritimo) {
        
        // crear nuevo sismo
        Sismo nuevoSismo = new Sismo();
        
        // fecha & hora
        Calendar calendario = Calendar.getInstance();
        nuevoSismo.setFecha(calendario);
        nuevoSismo.setHora(calendario);
        
        // datos
        nuevoSismo.setProfundidad(profundidad);
        nuevoSismo.setTipoOrigen(tipoOrigen);
        nuevoSismo.setMagnitud(magnitud);
        nuevoSismo.setLatitud(latitud);
        nuevoSismo.setLongitud(longitud);
        nuevoSismo.setDescripcionUbicacion(descripcionUbicacion);
        nuevoSismo.setProvincia(provincia);
        nuevoSismo.setEsMaritimo(esMaritimo);
        
        // agregar
        gestorSismos.agregarSismo(nuevoSismo);
        
        // guardar
        gestorSismos.guardarSismos();
        
        // Notificar a suscriptores interesados
//        notificarSuscriptores(nuevoSismo);
        
        return true;
    }
    
    public boolean modificarSismo(int indice, String fecha, String hora, double profundidad, 
            TipoOrigen tipoOrigen, double magnitud, double latitud, double longitud,
            String descripcionUbicacion, Provincia provincia, boolean esMaritimo) {
        
        // validaciones
        if (!validador.validarFecha(fecha) || !validador.validarHora(hora)) {
            return false;
        }
        
        List<Sismo> sismos = gestorSismos.getSismos();
        if (indice < 0 || indice >= sismos.size()) {
            return false;
        }
        
        // sismo a modificar
        Sismo sismo = sismos.get(indice);
        
        // actualizar
        sismo.setFecha(fecha);
        sismo.setHora(hora);
        sismo.setProfundidad(profundidad);
        sismo.setTipoOrigen(tipoOrigen);
        sismo.setMagnitud(magnitud);
        sismo.setLatitud(latitud);
        sismo.setLongitud(longitud);
        sismo.setDescripcionUbicacion(descripcionUbicacion);
        sismo.setProvincia(provincia);
        sismo.setEsMaritimo(esMaritimo);
        
        gestorSismos.guardarSismos();
        
        return true;
    }
    
//    private void notificarSuscriptores(Sismo sismo) {
//        notificacion.notificarSismo(sismo);
//    }
    
    public HashMap<Provincia, Integer> obtenerEstadisticasPorProvincia() {
        return controladorEstadisticas.generarEstadisticasPorProvincia(gestorSismos.getSismos());
    }
    
    public HashMap<TipoOrigen, Integer> obtenerEstadisticasPorTipoOrigen() {
        return controladorEstadisticas.generarEstadisticasPorTipoOrigen(gestorSismos.getSismos());
    }
    
    public List<Sismo> obtenerSismosPorRangoFechas(String fechaInicio, String fechaFin) {
        return controladorEstadisticas.filtrarPorRangoFechas(gestorSismos.getSismos(), fechaInicio, fechaFin);
    }
    
    public HashMap<Integer, Integer> obtenerEstadisticasPorMes(int año) {
        return controladorEstadisticas.generarEstadisticasPorMes(gestorSismos.getSismos(), año);
    }
    
    public HashMap<ClasificacionSismo, Integer> obtenerEstadisticasPorClasificacion() {
        return controladorEstadisticas.generarEstadisticasPorClasificacion(gestorSismos.getSismos());
    }
    
    
    public boolean agregarSuscriptor(String nombre, String correo, 
            String telefono, ArrayList<Provincia> provinciasInteres) {
        
        // correo o telefono
        if ((correo == null || correo.isEmpty()) && (telefono == null || telefono.isEmpty())) {
            return false;
        }
        
        // al menos una provincia de interes
        if (provinciasInteres == null || provinciasInteres.isEmpty()) {
            return false;
        }
        
        // crea y agrega el suscriptor
        return notificacion.agregarSuscriptor(nombre, correo, telefono, provinciasInteres);
    }
    
//    public List<Suscriptor> obtenerSuscriptores() {
//        return notificacion.getSuscriptores();
//    }
}
