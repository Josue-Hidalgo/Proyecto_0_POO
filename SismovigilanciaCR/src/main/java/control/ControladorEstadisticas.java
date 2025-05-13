/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Sismo;
import modelo.Provincia;
import modelo.TipoOrigen;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import java.awt.Color;
import java.util.Random;
import modelo.ClasificacionSismo;
/**
 *
 * @author javie
 */
public class ControladorEstadisticas {
    public ControladorEstadisticas() {}
    
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
    

    public ChartPanel crearBarraTablaSismosMes(ArrayList<Sismo> sismos) {
        DefaultCategoryDataset datasetBarras = new DefaultCategoryDataset();
        int[] cuentaPorMes = new int[12]; // Enero = 0, Diciembre = 11

        for (Sismo sismo : sismos) {
            String fechaStr = sismo.getFecha(); // Ejemplo: "12/05/2023"
            String[] partes = fechaStr.split("/"); // Separar por "/"
            int mes = Integer.parseInt(partes[1]); // El mes está en la posición 1
            cuentaPorMes[mes - 1]++; // Restamos 1 porque los arrays empiezan en 0
        }

        String[] nombresMeses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

        for (int i = 0; i < 12; i++) {
            datasetBarras.addValue(cuentaPorMes[i], "", nombresMeses[i]);
        }

        // Crear el gráfico de barras
        JFreeChart chartBarras = ChartFactory.createBarChart(
            "Cantidad de sismos por mes", 
            "Meses", 
            "Cantidad", 
            datasetBarras
        );

        // Obtener el plot del gráfico
        CategoryPlot plot = (CategoryPlot) chartBarras.getPlot();

        // Establecer el color en azul
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);

        // Para color aleatorio
        Random rand = new Random();
        int r = rand.nextInt(256); // Valor aleatorio para el rojo (0-255)
        int g = rand.nextInt(256); // Valor aleatorio para el verde (0-255)
        int b = rand.nextInt(256); // Valor aleatorio para el azul (0-255)
        Color randomColor = new Color(r, g, b);

        // Establecer color aleatorio
        plot.getRenderer().setSeriesPaint(0, randomColor);

        // Crear el panel del gráfico
        ChartPanel panelBarras = new ChartPanel(chartBarras);
        panelBarras.setBounds(0, 250, 500, 250);

        return panelBarras;
    }

    public JPanel crearTablaSismoMagnitud(ArrayList<Sismo> sismos) {
        // Crear columnas
        String[] columnas = {"Clasificación", "Magnitud"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        // Llenar filas con datos
        for (Sismo sismo : sismos) {
            String clasificacion = sismo.getClasificacionSismo().toString();
            double magnitud = sismo.getMagnitud();
            Object[] fila = {clasificacion, magnitud};
            modeloTabla.addRow(fila);
        }

        // Crear la tabla con el modelo
        JTable tabla = new JTable(modeloTabla);
        tabla.setFillsViewportHeight(true); // Ajusta el tamaño al scrollPane

        // Scroll con tamaño definido
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(450, 150)); // Tamaño deseado

        // Crear panel con layout y coordenadas
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBounds(0, 400, 500, 180); // Coordenadas y tamaño del panel
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        return panelTabla;
    }

}
