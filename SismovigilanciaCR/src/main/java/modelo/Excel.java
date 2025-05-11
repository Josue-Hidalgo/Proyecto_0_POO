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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import control.ValidadorDatos;
import java.io.IOException;

/**
 *
 * @author javie
 */
public class Excel {
    private String direccionArchivo;
    private XSSFWorkbook libreta;
    private ArrayList<Sismo> sismos = new ArrayList<>();
    public static final int columnas = 10;
    
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

    public void setLibreta(XSSFWorkbook libreta) {
        this.libreta = libreta;
    }
    //==========================================================================
    
    // Getters
    //==========================================================================
    public String getDireccionArchivo() {
        return direccionArchivo;
    }

    public XSSFWorkbook getLibreta() {
        return libreta;
    }
    
    public ArrayList<Sismo> getSismos() {
        return sismos;
    }
    //==========================================================================

    /**
     * Obtiene los datos del achivo .xslx y los guarda en el atributo "dato"
     * creada en la clase.
     */
    public void getListaSismos() {
        try {
            FileInputStream archivo = new FileInputStream(new File(direccionArchivo));
            libreta = new XSSFWorkbook(archivo);
            XSSFSheet hoja = libreta.getSheetAt(0);
            Iterator<Row> iteradorFila = hoja.iterator();
            sismos = new ArrayList<>();
            String[] argumentos = new String[columnas];
            Row fila;
            Cell celda;
            
            while (iteradorFila.hasNext()) {
                fila = iteradorFila.next();
                for (int i = 0; i < columnas; i++) {
                    celda = fila.getCell(i);
                    if (celda != null) {
                        switch (celda.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC -> argumentos[i] = Double.toString(celda.getNumericCellValue());
                        case Cell.CELL_TYPE_STRING -> argumentos[i] = celda.getStringCellValue();
                        case Cell.CELL_TYPE_FORMULA -> argumentos[i] = celda.getCellFormula();
                        case Cell.CELL_TYPE_BOOLEAN -> argumentos[i] = celda.getBooleanCellValue() ? "Si" : "No";
                        case Cell.CELL_TYPE_BLANK -> argumentos[i] = "";
                        }
                    }
                }
                sismos.add(new Sismo(argumentos));
            }
            archivo.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setCelda(int indiceFila, int indiceColumna, String dato) {
        Sismo sismo = sismos.get(indiceFila);
        sismo.set(indiceColumna, dato);
        
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.getRow(indiceFila);
        Cell celda = fila.getCell(indiceColumna);
        Object[] par = ValidadorDatos.stringToDouble(dato);
        if ((boolean) par[1] && celda != null) {
            celda.setCellValue((double) par[0]);
        }
        celda.setCellValue(dato);
        guardarDatos();
    }
    
    public void agregarSismo(String[] argumentos) {
        Sismo nuevoSismo = new Sismo(argumentos);
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.createRow(sismos.size());
        Cell celda;
        for (int i = 0; i < columnas; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(argumentos[i]);
        }
        sismos.add(nuevoSismo);
    }
    
    /**
     * Reescribe todo el archivo .xslx según lo que se haya cambiado en "datos"
     */
    public void guardarDatos() {
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
}
