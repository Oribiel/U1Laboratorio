/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class LaboratorioEntidad {
    private int idLaboratorio;
    private String nombre;
    private String direccion;

    public LaboratorioEntidad(String nombre) {
        this.nombre = nombre;
    }
    
    

    public LaboratorioEntidad(int idLaboratorio, String nombre, String direccion) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public LaboratorioEntidad(int idLaboratorio, String nombre) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    @Override
    public String toString() {
        return "LaboratorioEntidad{" + "idLaboratorio=" + idLaboratorio + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
    
    
    
}
