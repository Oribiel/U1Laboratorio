/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author oribi
 */
public class ParametrosDTO {
    private int idParametroEvaluacion;
    private String nombre;
    private String rango;
    private int idPruebaAnalisis;

    //Constructor para guardar el param

    public ParametrosDTO(String nombre, String rango, int idPruebaAnalisis) {
        this.nombre = nombre;
        this.rango = rango;
        this.idPruebaAnalisis = idPruebaAnalisis;
    }
    

    public ParametrosDTO(int idParametroEvaluacion, String nombre, String rango, int idPruebaAnalisis) {
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

    @Override
    public String toString() {
        return "ParametrosDTO{" + "idParametroEvaluacion=" + idParametroEvaluacion + ", nombre=" + nombre + ", rango=" + rango + ", idPruebaAnalisis=" + idPruebaAnalisis + '}';
    }
    
    
}
