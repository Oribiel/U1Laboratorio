/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author oribi
 */
public class PruebaAnalisis {
    private int idPruebaAnalisis;
    private String nombre;
    private int idCategoria;
    private int idLaboratorio;


    public PruebaAnalisis() {}

    public PruebaAnalisis(int idPruebaAnalisis, String nombre, int idCategoria, int idLaboratorio) {
        this.idPruebaAnalisis = idPruebaAnalisis;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.idLaboratorio = idLaboratorio;
    }

   

    public int getIdPruebaAnalisis() {
        return idPruebaAnalisis;
    }

    public void setIdPruebaAnalisis(int idPruebaAnalisis) {
        this.idPruebaAnalisis = idPruebaAnalisis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    @Override
    public String toString() {
        return "PruebaAnalisis{" + "idPruebaAnalisis=" + idPruebaAnalisis + ", nombre=" + nombre + ", idCategoria=" + idCategoria + ", idLaboratorio=" + idLaboratorio + '}';
    }
    
}
