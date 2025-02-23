/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class ResultadoEntidad {
    private int idResultado;
    private String resultadoParametro;  
    private int idParametroEvaluacion; 
    private int idAnalisisCliente;
    
    // constructor h¿guardar sin id

    public ResultadoEntidad(String resultadoParametro, int idParametroEvaluacion, int idAnalisisCliente) {
        this.resultadoParametro = resultadoParametro;
        this.idParametroEvaluacion = idParametroEvaluacion;
        this.idAnalisisCliente = idAnalisisCliente;
    }
    
    
    //constructor para recibir datos

    public ResultadoEntidad(int idResultado, String resultadoParametro, int idParametroEvaluacion, int idAnalisisCliente) {
        this.idResultado = idResultado;
        this.resultadoParametro = resultadoParametro;
        this.idParametroEvaluacion = idParametroEvaluacion;
        this.idAnalisisCliente = idAnalisisCliente;
    }

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

    public int getIdParametroEvaluacion() {
        return idParametroEvaluacion;
    }

    public void setIdParametroEvaluacion(int idParametroEvaluacion) {
        this.idParametroEvaluacion = idParametroEvaluacion;
    }

    public int getIdAnalisisCliente() {
        return idAnalisisCliente;
    }

    public void setIdAnalisisCliente(int idAnalisisCliente) {
        this.idAnalisisCliente = idAnalisisCliente;
    }
    
    
}
