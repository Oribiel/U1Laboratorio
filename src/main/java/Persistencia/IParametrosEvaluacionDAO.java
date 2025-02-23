/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOS.ParametrosDTO;
import Entidades.ParametrosEntidad;
import java.util.List;

/**
 *
 * @author oribi
 */
public interface IParametrosEvaluacionDAO {
    // se registra un parametro en alguna prueba
    ParametrosEntidad registrarParametro(ParametrosDTO parametro)throws PersistenciaException;
    //Se elimina un parametro de alguna prueba
    ParametrosEntidad eliminarParametroEnPrueba(int idParam, int idPrueba) throws PersistenciaException;
    //Obtiene Lista de los parametros que esten en una prueba
    List<ParametrosEntidad> parametrosEnUnaPrueba(int idPrueba);
}

