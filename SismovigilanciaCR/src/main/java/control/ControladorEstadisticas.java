/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import modelo.Sismo;
import modelo.Provincia;
import modelo.TipoOrigen;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author javie
 */
public class ControladorEstadisticas {
    public ControladorEstadisticas() {
    }
    
    /**
     * 
     * @param sismos
     * @return 
     */
    private int[] contarSismosPorProvincia(ArrayList<Sismo> sismos) {
        int[] cuentaPorProvincia = new int[8];
        for (int i = 0; i < sismos.size(); i++) {
            switch (sismos.get(i).getProvincia()) {
                case Provincia.SAN_JOSE -> cuentaPorProvincia[0]++;
                case Provincia.ALAJUELA -> cuentaPorProvincia[1]++;
                case Provincia.CARTAGO -> cuentaPorProvincia[2]++;
                case Provincia.HEREDIA -> cuentaPorProvincia[3]++;
                case Provincia.GUANACASTE -> cuentaPorProvincia[4]++;
                case Provincia.PUNTARENAS -> cuentaPorProvincia[5]++;
                case Provincia.LIMON -> cuentaPorProvincia[6]++;
                case Provincia.SIN_ASIGNAR -> cuentaPorProvincia[7]++;
            }
        }
        return cuentaPorProvincia;
    }
    
    /**
     * 
     * @param sismos
     * @return 
     */
    private int[] contarSismosPorOrigen(ArrayList<Sismo> sismos) {
        int[] cuentaPorOrigen = new int[6];
        for (int i = 0; i < sismos.size(); i++) {
            switch (sismos.get(i).getTipoOrigen()) {
                case TipoOrigen.SUBDUCCION -> cuentaPorOrigen[0]++;
                case TipoOrigen.CHOQUE_PLACAS -> cuentaPorOrigen[1]++;
                case TipoOrigen.TECTONICO_FALLA_LOCAL -> cuentaPorOrigen[2]++;
                case TipoOrigen.INTRA_PLACA -> cuentaPorOrigen[3]++;
                case TipoOrigen.DEFORMACION_INTERNA -> cuentaPorOrigen[4]++;
                case TipoOrigen.INDEFINIDO -> cuentaPorOrigen[5]++;
            }
        }
        return cuentaPorOrigen;
    }
    
    /**
     * 
     * @param sismos
     * @return 
     */
    public ChartPanel crearBarrasPorProvincia(ArrayList<Sismo> sismos) {
        DefaultCategoryDataset datasetBarras = new DefaultCategoryDataset();
        int[] cuentaPorProvincia = contarSismosPorProvincia(sismos);
        datasetBarras.addValue(cuentaPorProvincia[0], "", "SJO");
        datasetBarras.addValue(cuentaPorProvincia[1], "", "ALJ");
        datasetBarras.addValue(cuentaPorProvincia[2], "", "CTG");
        datasetBarras.addValue(cuentaPorProvincia[3], "", "HRD");
        datasetBarras.addValue(cuentaPorProvincia[4], "", "GNC");
        datasetBarras.addValue(cuentaPorProvincia[5], "", "PTN");
        datasetBarras.addValue(cuentaPorProvincia[6], "", "LMN");
        datasetBarras.addValue(cuentaPorProvincia[7], "", "SA");
        JFreeChart chartBarras = ChartFactory.createBarChart("Cantidad de sismos por provincia", "Provincias", "Cantidad", datasetBarras);
        ChartPanel panelBarras = new ChartPanel(chartBarras);
        panelBarras.setBounds(0, 0, 500, 250);
        return panelBarras;
    }
    
    /**
     * 
     * @param sismos
     * @return 
     */
    public ChartPanel crearPastelPorOrigen(ArrayList<Sismo> sismos) {
        DefaultPieDataset datasetPastel = new DefaultPieDataset();
        int[] cuentaPorOrigen = contarSismosPorOrigen(sismos);
        datasetPastel.setValue("Subducción", cuentaPorOrigen[0]);
        datasetPastel.setValue("Choque de placas", cuentaPorOrigen[1]);
        datasetPastel.setValue("Tectónico falla local", cuentaPorOrigen[2]);
        datasetPastel.setValue("Intra placa", cuentaPorOrigen[3]);
        datasetPastel.setValue("Deformación interna", cuentaPorOrigen[4]);
        datasetPastel.setValue("Indefinido", cuentaPorOrigen[5]);
        JFreeChart chartPastel = ChartFactory.createPieChart("Proporción de sismos por origen", datasetPastel, true, true, false);
        ChartPanel panelPastel = new ChartPanel(chartPastel);
        panelPastel.setBounds(500, 0, 500, 500);
        return panelPastel;
    }
}
