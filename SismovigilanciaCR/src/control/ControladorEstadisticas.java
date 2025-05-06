/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import modelo.ClasificacionSismo;
import modelo.EscalaMagnitud;
import modelo.Provincia;
import modelo.Sismo;
import modelo.TipoOrigen;

/**
 *
 * @author javie, Emilio
 */
public class ControladorEstadisticas {
    public ControladorEstadisticas() {
    }
    
    public HashMap<Provincia, Integer> generarEstadisticasPorProvincia(List<Sismo> sismos) {
        HashMap<Provincia, Integer> estadisticas = new HashMap<>();
        
        // inicializar
        for (Provincia provincia : Provincia.values()) {
            estadisticas.put(provincia, 0);
        }
        
        // contar sismos por provincia
        for (Sismo sismo : sismos) {
            Provincia provincia = sismo.getProvincia();
            estadisticas.put(provincia, estadisticas.get(provincia) + 1);
        }
        
        return estadisticas;
    }
    
    public HashMap<TipoOrigen, Integer> generarEstadisticasPorTipoOrigen(List<Sismo> sismos) {
        HashMap<TipoOrigen, Integer> estadisticas = new HashMap<>();
        
        // inicializar
        for (TipoOrigen tipo : TipoOrigen.values()) {
            estadisticas.put(tipo, 0);
        }
        
        // contar sismos por origen
        for (Sismo sismo : sismos) {
            TipoOrigen tipo = sismo.getTipoOrigen();
            estadisticas.put(tipo, estadisticas.get(tipo) + 1);
        }
        
        return estadisticas;
    }
    
    public List<Sismo> filtrarPorRangoFechas(List<Sismo> sismos, String fechaInicio, String fechaFin) {
        List<Sismo> resultado = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            Date inicio = sdf.parse(fechaInicio);
            Date fin = sdf.parse(fechaFin);
            
            for (Sismo sismo : sismos) {
                Date fechaSismo = sdf.parse(sismo.getFecha());
                
                // verificar fecha del sismo
                if (!fechaSismo.before(inicio) && !fechaSismo.after(fin)) {
                    resultado.add(sismo);
                }
            }
        } catch (ParseException e) {}
        
        return resultado;
    }
    
    public HashMap<Integer, Integer> generarEstadisticasPorMes(List<Sismo> sismos, int año) {
        HashMap<Integer, Integer> estadisticas = new HashMap<>();
        
        // inicializar
        for (int i = 1; i <= 12; i++) {
            estadisticas.put(i, 0);
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        
        for (Sismo sismo : sismos) {
            try {
                Date fechaSismo = sdf.parse(sismo.getFecha());
                cal.setTime(fechaSismo);
                
                // verificar annio
                if (cal.get(Calendar.YEAR) == año) {
                    int mes = cal.get(Calendar.MONTH) + 1;
                    estadisticas.put(mes, estadisticas.get(mes) + 1);
                }
            } catch (ParseException e) {}
        }
        
        return estadisticas;
    }
    
    public HashMap<ClasificacionSismo, Integer> generarEstadisticasPorClasificacion(List<Sismo> sismos) {
        HashMap<ClasificacionSismo, Integer> estadisticas = new HashMap<>();
        
        // inicializar
        for (ClasificacionSismo clasificacion : ClasificacionSismo.values()) {
            estadisticas.put(clasificacion, 0);
        }
        
        // contar sismos por clasificacion
        for (Sismo sismo : sismos) {
            ClasificacionSismo clasificacion = sismo.getClasificacionSismo();
            estadisticas.put(clasificacion, estadisticas.get(clasificacion) + 1);
        }
        
        return estadisticas;
    }
    
    public HashMap<EscalaMagnitud, Integer> generarEstadisticasPorEscala(List<Sismo> sismos) {
        HashMap<EscalaMagnitud, Integer> estadisticas = new HashMap<>();
        
        // inicializar
        for (EscalaMagnitud escala : EscalaMagnitud.values()) {
            estadisticas.put(escala, 0);
        }
        
        // contar por escala
        for (Sismo sismo : sismos) {
            EscalaMagnitud escala = sismo.getEscalaMagnitud();
            estadisticas.put(escala, estadisticas.get(escala) + 1);
        }
        
        return estadisticas;
    }
    
    public double calcularMagnitudPromedio(List<Sismo> sismos) {
        if (sismos.isEmpty()) {
            return 0.0;
        }
        
        double sumaTotal = 0.0;
        for (Sismo sismo : sismos) {
            sumaTotal += sismo.getMagnitud();
        }
        
        return sumaTotal / sismos.size();
    }
    
    public Sismo encontrarSismoMayorMagnitud(List<Sismo> sismos) {
        if (sismos.isEmpty()) {
            return null;
        }
        
        Sismo sismoMayor = sismos.get(0);
        for (Sismo sismo : sismos) {
            if (sismo.getMagnitud() > sismoMayor.getMagnitud()) {
                sismoMayor = sismo;
            }
        }
        
        return sismoMayor;
    }
}
