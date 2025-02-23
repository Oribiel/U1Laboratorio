/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class ResultadoPorCliente {
      private int idResultado;
    private String resultadoParametro;
    private String parametro;
    private String rango;
    private int folioAnalisis;
    private String nombreCliente;
    private String apellidoPaterno;
    private String apellidoMaterno;

    // Constructor
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

    // Getters y Setters
    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public String getResultadoParametro() {
        return resultadoParametro;
    }

    public void setResultadoParametro(String resultadoParametro) {
        this.resultadoParametro = resultadoParametro;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public int getFolioAnalisis() {
        return folioAnalisis;
    }

    public void setFolioAnalisis(int folioAnalisis) {
        this.folioAnalisis = folioAnalisis;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    // Método toString para imprimir los datos
    @Override
    public String toString() {
        return "ResultadoPorCliente{" +
                "idResultado=" + idResultado +
                ", resultadoParametro='" + resultadoParametro + '\'' +
                ", parametro='" + parametro + '\'' +
                ", rango='" + rango + '\'' +
                ", folioAnalisis=" + folioAnalisis +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                '}';
    }
        
}
