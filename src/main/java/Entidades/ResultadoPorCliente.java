/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ResultadoPorCliente {
     private String nombrePrueba;
    private int folioAnalisis;
    private String nombreCliente;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private List<ResultadoPorCliente> resultados;

    private int idResultado;
    private String resultadoParametro;
    private String parametro;
    private String rango;

    // Constructor para la prueba con lista de resultados
    public ResultadoPorCliente(String nombrePrueba, int folioAnalisis, String nombreCliente, 
                               String apellidoPaterno, String apellidoMaterno) {
        this.nombrePrueba = nombrePrueba;
        this.folioAnalisis = folioAnalisis;
        this.nombreCliente = nombreCliente;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.resultados = new ArrayList<>();
    }

    // Constructor para cada resultado individual
    public ResultadoPorCliente(int idResultado, String resultadoParametro, String parametro, String rango, 
                               int folioAnalisis, String nombreCliente, String apellidoPaterno, String apellidoMaterno) {
        this.idResultado = idResultado;
        this.resultadoParametro = resultadoParametro;
        this.parametro = parametro;
        this.rango = rango;
        this.folioAnalisis = folioAnalisis;
        this.nombreCliente = nombreCliente;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    // Método para agregar resultados a la lista
    public void agregarResultado(ResultadoPorCliente resultado) {
        this.resultados.add(resultado);
    }

    // Getters
    public String getNombrePrueba() {
        return nombrePrueba;
    }

    public int getFolioAnalisis() {
        return folioAnalisis;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public List<ResultadoPorCliente> getResultados() {
        return resultados;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public String getResultadoParametro() {
        return resultadoParametro;
    }

    public String getParametro() {
        return parametro;
    }

    public String getRango() {
        return rango;
    }

    @Override
    public String toString() {
        return "ResultadoPorCliente{" +
                "nombrePrueba='" + nombrePrueba + '\'' +
                ", folioAnalisis=" + folioAnalisis +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", idResultado=" + idResultado +
                ", resultadoParametro='" + resultadoParametro + '\'' +
                ", parametro='" + parametro + '\'' +
                ", rango='" + rango + '\'' +
                ", resultados=" + resultados +
                '}';
    }
}
