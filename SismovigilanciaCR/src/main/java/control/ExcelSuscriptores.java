/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import control.ValidadorDatos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import modelo.Suscriptor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author javie
 */
public class ExcelSuscriptores {
    private String direccionArchivo;
    private XSSFWorkbook libreta;
    private ArrayList<Suscriptor> suscriptores = new ArrayList<>();
    public static final int columnas = 4;
    
    public ExcelSuscriptores() {
    }
    
    public ExcelSuscriptores(String direccionArchivo) {
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
    
    public void setSuscriptores(ArrayList<Suscriptor> suscriptores) {
        this.suscriptores = suscriptores;
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
    
    public ArrayList<Suscriptor> getSuscriptores() {
        return suscriptores;
    }
    
    /**
     * 
     * @return 
     */
    public String[][] getTablaAtributos() {
        String[][] tabla = new String[suscriptores.size()][columnas];
        for (int i = 0; i < suscriptores.size(); i++) {
            tabla[i] = suscriptores.get(i).getAtributos();
        }
        return tabla;
    }
    //==========================================================================

    /**
     * Obtiene los datos del achivo .xslx y los guarda en el atributo "dato"
     * creada en la clase.
     */
    public void extraerSuscriptoresDeArchivo() {
        try {
            // Incialización para leer el archivo .xlsx
            FileInputStream archivo = new FileInputStream(new File(direccionArchivo));
            libreta = new XSSFWorkbook(archivo);
            XSSFSheet hoja = libreta.getSheetAt(0);
            Iterator<Row> iteradorFila = hoja.iterator();
            suscriptores = new ArrayList<>();
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
                // Agregar el sismo al ArrayList de suscriptores
                suscriptores.add(new Suscriptor(argumentos));
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
    public void modificarSuscriptor(int indiceFila, int indiceColumna, String dato) {
        // Realizar el set en el ArrayList de suscriptores
        Suscriptor sismo = suscriptores.get(indiceFila);
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
     * @param nuevoSuscriptor 
     */
    public void agregarSuscriptor(Suscriptor nuevoSuscriptor) {
        // Obtener el areglo de atributos que tiene el nuevo sismo
        String[] argumentos = nuevoSuscriptor.getAtributos();
        
        // Escrbir el arreglo de atributos en el archivo .xlsx
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.createRow(suscriptores.size());
        Cell celda;
        for (int i = 0; i < columnas; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(argumentos[i]);
        }
        
        // Agregar el sismo en el ArrayList de suscriptores
        suscriptores.add(nuevoSuscriptor);
    }
    
    /**
     * 
     * @param argumentos 
     */
    public void agregarSuscriptor(String[] argumentos) {
        // Crear la instancia del nuevo sismo
        Suscriptor nuevoSuscriptor = new Suscriptor(argumentos);
        
        // Obtener el areglo de atributos que tiene el nuevo sismo
        argumentos = nuevoSuscriptor.getAtributos();
        
        // Escrbir el arreglo de atributos en el archivo .xlsx
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.createRow(suscriptores.size());
        Cell celda;
        for (int i = 0; i < columnas; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(argumentos[i]);
        }
        
        // Agregar el sismo en el ArrayList de suscriptores
        suscriptores.add(nuevoSuscriptor);
        
        // Guardar los cambios
        this.guardarDatos();
    }
    
    /**
     * 
     * @param indiceFila 
     */
    public void eliminarSuscriptor(int indiceFila) {
        // Remover el sismo del ArrayList en el índice indicado
        suscriptores.remove(indiceFila);
        
        // Escrbir el arreglo de atributos en el archivo .xlsx
        XSSFSheet hoja = libreta.getSheetAt(0);
        Row fila = hoja.getRow(indiceFila);
        hoja.removeRow(fila);
        hoja.shiftRows(indiceFila + 1, suscriptores.size() + 1, -1);
        
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
