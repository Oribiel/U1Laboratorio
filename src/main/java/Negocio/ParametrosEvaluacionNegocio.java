/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.IParametrosEvaluacionDAO;
import Persistencia.ParametrosEvaluacionDAO;

/**
 *
 * @author oribi
 */
public class ParametrosEvaluacionNegocio implements IParametrosEvaluacionNegocio {

    private IParametrosEvaluacionDAO parametrosEvaluacionDAO;

    public ParametrosEvaluacionNegocio() {
        parametrosEvaluacionDAO = new ParametrosEvaluacionDAO(); 
    }

    @Override
    public void registrarParametro(String nombre, String rango, String nombrePrueba) {
        // Validaciones adicionales o lógica de negocio (si es necesario)
        if (nombre != null && !nombre.isEmpty() && rango != null && !rango.isEmpty()) {
            parametrosEvaluacionDAO.registrarParametro(nombre, rango, nombrePrueba);
        } else {
            System.out.println("Nombre o rango no válido");
        }
    }
}

