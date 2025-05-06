/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author javie
 */
public class Excel {
    private String direccionArchivo;
    private int numeroFilas;
    private Map<String, ArrayList<Object>> datos = new TreeMap<String, ArrayList<Object>>();
    private static final String[] indiceEstandar = {"Fecha", "Hora", "Origen", "Magnitud", "Latitud", "Longitud", "Ubicacion", "Provincia", "Maritimo"};
    
    public Excel() {
    }
    
    public Excel(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }
    
    // Setters
    //==========================================================================
    public void setDireccionArchivo(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }

    public void setNumeroFilas(int numeroFilas) {
        this.numeroFilas = numeroFilas;
    }

    public void setDatos(Map<String, ArrayList<Object>> datos) {
        this.datos = datos;
    }
    //==========================================================================
    
    // Getters
    //==========================================================================
    public String getDireccionArchivo() {
        return direccionArchivo;
    }

    public int getNumeroFilas() {
        return numeroFilas;
    }

    public Map<String, ArrayList<Object>> getDatos() {
        return datos;
    }
    //==========================================================================

    /**
     * Obtiene los datos del achivo .xslx y los guarda en el atributo "dato"
     * creada en la clase
     */
    public void obtenerDatos() {
        try {
            FileInputStream archivo = new FileInputStream(new File(direccionArchivo));
            XSSFWorkbook libreta = new XSSFWorkbook(archivo);
            XSSFSheet hoja = libreta.getSheetAt(0);
            Iterator<Row> iteradorFila = hoja.iterator();
            
            while (iteradorFila.hasNext()) {
                numeroFilas++;
                Row fila = iteradorFila.next();
                Iterator<Cell> iteradorCelda = fila.cellIterator();
                ArrayList<Object> celdas = new ArrayList<Object>();
                while (iteradorCelda.hasNext()) {
                    Cell celda = iteradorCelda.next();
                    switch (celda.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC: 
                        celdas.add(celda.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING: 
                        celdas.add(celda.getStringCellValue()); 
                        break;
                    } 
                }
                datos.put(Integer.toString(fila.getRowNum()), celdas);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Reescribe todo el archivo .xslx según lo que se haya cambiado en "datos"
     */
    public void reescribirDatos() {
        XSSFWorkbook libreta = new XSSFWorkbook(); 
        XSSFSheet hoja = libreta.createSheet("Hoja"); 
        Set<String> llaves = datos.keySet();
        int numeroFila = 0;
        for (String llave : llaves) {
            // Creating a new row in the sheet 
            Row fila = hoja.createRow(numeroFila);
            numeroFila++;
            ArrayList<Object> celdas = datos.get(llave);
            int numeroCelda = 0;
            for (Object objeto : celdas) { 
                Cell celda = fila.createCell(numeroCelda); 
                numeroCelda++;
                if (objeto instanceof String) {
                    celda.setCellValue((String)objeto); 
                }
                else if (objeto instanceof Integer) {
                    celda.setCellValue((Integer)objeto); 
                }
                else if (objeto instanceof Float) {
                    celda.setCellValue((Float)objeto);
                }
            } 
        }
        // Try block to check for exceptions 
        try {
            // Writing the workbook 
            FileOutputStream out = new FileOutputStream(new File(direccionArchivo)); 
            libreta.write(out);
            // Closing file output connections 
            out.close();  
        }
        // Catch block to handle exceptions 
        catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
    
    /**
     * Verifica si la primera de fila de celdas tienen el nombre  y el número
     * de los atributos adecuados
     * @return 
     */
    public boolean esIndiceCorrecto() {
        obtenerDatos();
        ArrayList<Object> celdas = datos.get("0");
        String buffer;
        for (int i = 0; i < 9; i++) {
            buffer = (String)celdas.get(i);
            if (!indiceEstandar[i].equalsIgnoreCase(buffer)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Obtine el dato que se encuentra en una celda del archivo .xslx que se 
     * guardo en "datos"
     * @param fila Entero que indica el número de fila de la celda
     * @param columna Entero que indica el número de columna de la celda
     * @return Instancia Object que representa el dato de la celda especificada
     */
    public Object obtenerDatoCelda(int fila, int columna) {
        if (fila <= 0 || columna <= 0) {
            return null;
        }
        ArrayList<Object> celdas = datos.get(Integer.toString(fila));
        if (columna >= celdas.size()) {
            return null;
        }
        return celdas.get(columna);
    }
    
    /**
     * Obtiene todos los sismos dentro del atributo "datos"
     * @return Array list con todos los sismos obtenidos
     */
    public ArrayList<Sismo> obtenerSismos() {
        obtenerDatos();
        ArrayList<Sismo> sismos = new ArrayList<Sismo>();
        for (int i = 1; i <= numeroFilas; i++) {
            ArrayList<Object> celdas = datos.get(Integer.toString(i));
            Sismo sismo = new Sismo();
            sismo.setFecha((String)celdas.get(0));
            sismo.setHora((String)celdas.get(1));
            sismo.setProfundidad((double)celdas.get(2));
            sismo.setTipoOrigen(TipoOrigen.valueOf((String)celdas.get(3)));
            sismo.setLatitud((double)celdas.get(4));
            sismo.setLongitud((double)celdas.get(5));
            sismo.setDescripcionUbicacion((String)celdas.get(6));
            sismo.setProvincia(Provincia.valueOf((String)celdas.get(7)));
            sismo.setEsMaritimo(((boolean)celdas.get(8)));
            sismos.add(sismo);
        }
        return sismos;
    }

    public void guardarSismos(ArrayList<Sismo> sismos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
