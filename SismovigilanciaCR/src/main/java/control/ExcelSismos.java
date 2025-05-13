/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

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
import modelo.Sismo;

/**
 *
 * @author javie
 */
public class ExcelSismos {
    private String direccionArchivo;
    private XSSFWorkbook libreta;
    private ArrayList<Sismo> sismos = new ArrayList<>();
    public static final int columnas = 10;
    
    public ExcelSismos() {
    }
    
    public ExcelSismos(String direccionArchivo) {
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
    
    public void setSismos(ArrayList<Sismo> sismos) {
        this.sismos = sismos;
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
    
    /**
     * 
     * @return 
     */
    public String[][] getTablaAtributos() {
        String[][] tabla = new String[sismos.size()][columnas];
        for (int i = 0; i < sismos.size(); i++) {
            tabla[i] = sismos.get(i).getAtributos();
        }
        return tabla;
    }
    //==========================================================================

    /**
     * Obtiene los datos del achivo .xslx y los guarda en el atributo "dato"
     * creada en la clase.
     */
    public void extraerSismosDeArchivo() {
        try {
            // Incialización para leer el archivo .xlsx
            FileInputStream archivo = new FileInputStream(new File(direccionArchivo));
            libreta = new XSSFWorkbook(archivo);
            XSSFSheet hoja = libreta.getSheetAt(0);
            Iterator<Row> iteradorFila = hoja.iterator();
            sismos = new ArrayList<>();
            String[] argumentos = new String[columnas];
            Row fila;
            Cell celda;
            
            // Iterar por las filas
            while (iteradorFila.hasNext()) {
                fila = iteradorFila.next();
                // Iterar por las columnas
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
                // Agregar el sismo al ArrayList de sismos
                sismos.add(new Sismo(argumentos));
            }
            // Cerrar el archivo
            archivo.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param indiceFila
     * @param indiceColumna
     * @param dato 
     */
    public void modificarSismo(int indiceFila, int indiceColumna, String dato) {
        // Realizar el set en el ArrayList de sismos
        Sismo sismo = sismos.get(indiceFila);
        sismo.set(indiceColumna, dato);
        
        // Realizar el cambio en el archivo .xlsx
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.getRow(indiceFila);
        Cell celda = fila.getCell(indiceColumna);
        Object[] par = ValidadorDatos.stringToDouble(dato);
        if ((boolean) par[1] && celda != null) {
            celda.setCellValue((double) par[0]);
        }
        celda.setCellValue(dato);
    }
    
    /**
     * 
     * @param nuevoSismo 
     */
    public void agregarSismo(Sismo nuevoSismo) {
        // Obtener el areglo de atributos que tiene el nuevo sismo
        String[] argumentos = nuevoSismo.getAtributos();
        
        // Escrbir el arreglo de atributos en el archivo .xlsx
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.createRow(sismos.size());
        Cell celda;
        for (int i = 0; i < columnas; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(argumentos[i]);
        }
        
        // Agregar el sismo en el ArrayList de sismos
        sismos.add(nuevoSismo);
    }
    
    /**
     * 
     * @param argumentos 
     */
    public void agregarSismo(String[] argumentos) {
        // Crear la instancia del nuevo sismo
        Sismo nuevoSismo = new Sismo(argumentos);
        
        // Obtener el areglo de atributos que tiene el nuevo sismo
        argumentos = nuevoSismo.getAtributos();
        
        // Escrbir el arreglo de atributos en el archivo .xlsx
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.createRow(sismos.size());
        Cell celda;
        for (int i = 0; i < columnas; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(argumentos[i]);
        }
        
        // Agregar el sismo en el ArrayList de sismos
        sismos.add(nuevoSismo);
        
        // Guardar los cambios
        this.guardarDatos();
    }
    
    /**
     * 
     * @param indiceFila 
     */
    public void eliminarSismo(int indiceFila) {
        // Remover el sismo del ArrayList en el índice indicado
        sismos.remove(indiceFila);
        
        // Escrbir el arreglo de atributos en el archivo .xlsx
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.getRow(indiceFila);
        hoja.removeRow(fila);
        hoja.shiftRows(indiceFila + 1, sismos.size() + 1, -1);
        
        // Guardar los cambios
        this.guardarDatos();
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
