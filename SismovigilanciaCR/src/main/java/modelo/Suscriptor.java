/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Set;

/**
 *
 * @author javie
 */
public class Suscriptor {
    private String nombre;
    private String correo;
    private String telefono;
    private Set<Provincia> provinciasInteres;
    
    public Suscriptor() {
    }
    
    public Suscriptor(String nombre) {
        this.nombre = nombre;
    }
    
    // Setters
    //==========================================================================
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setProvinciasInteres(Set<Provincia> provinciasInteres) {
        this.provinciasInteres = provinciasInteres;
    }
    //==========================================================================
    
    // Getters
    //==========================================================================
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Set<Provincia> getProvinciasInteres() {
        return provinciasInteres;
    }
    //==========================================================================
    
    /**
     * Agrega una nueva provincia de interés al suscriptor.
     * @param provincia Enumerado de Provincia
     * @return Booleano indicando si se logró agregar la provincia. Es true si
     * agregó y es false si no se agregó o si ya existía un elemento igual
     */
    boolean agregarProvinciaInteres(Provincia provincia) {
        return provinciasInteres.add(provincia);
    }
    
    /**
     * Envía un mensaje al suscriptor dependiendo de las características de un 
     * sismo.
     * @param sismo Instancia de Sismo que contiene información de un sismo
     * @return Booleano indicando si se logró enviar el mensaje al suscriptor
     */
    boolean notificar(Sismo sismo) {
        return true;
    }
    
}
