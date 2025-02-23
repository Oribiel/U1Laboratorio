/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author oribi
 */
public class ParametrosEntidad {

    private int idParametroEvaluacion;
    private String nombre;
    private String rango;
    private int idPruebaAnalisis;

    public ParametrosEntidad() {
    }

    public ParametrosEntidad(int idParametroEvaluacion, String nombre, String rango, int idPruebaAnalisis) {
        this.idParametroEvaluacion = idParametroEvaluacion;
        this.nombre = nombre;
        this.rango = rango;
        this.idPruebaAnalisis = idPruebaAnalisis;
    }

   
    public int getIdParametroEvaluacion() {
        return idParametroEvaluacion;
    }

    public void setIdParametroEvaluacion(int idParametroEvaluacion) {
        this.idParametroEvaluacion = idParametroEvaluacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
    
    public int getIdPruebaAnalisis() {
        return idPruebaAnalisis;
    }

    public void setIdPruebaAnalisis(int idPruebaAnalisis) {
        this.idPruebaAnalisis = idPruebaAnalisis;
    }
    
    
}
