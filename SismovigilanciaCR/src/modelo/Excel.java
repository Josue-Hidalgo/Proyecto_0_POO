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
    private Map<String, ArrayList<Object>> datos = new TreeMap<String, ArrayList<Object>>();
    
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

    public void setDatos(Map<String, ArrayList<Object>> datos) {
        this.datos = datos;
    }
    //==========================================================================
    
    // Getters
    //==========================================================================
    public String getDireccionArchivo() {
        return direccionArchivo;
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
    
    public void obtenerSismos() {
        obtenerDatos();
        Sismo sismo;
    }
}
