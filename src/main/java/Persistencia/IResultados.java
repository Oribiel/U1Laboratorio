/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOS.AnalisisClienteDTO;
import DTOS.ResultadoDTO;
import Entidades.ResultadoEntidad;
import Entidades.ResultadoPorCliente;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IResultados {
    public ResultadoEntidad crearResultado(ResultadoDTO resultado)throws PersistenciaException;
    public List<ResultadoPorCliente> ResultadosPorCliente(int idAnalisisCliente) throws PersistenciaException;
    
    
    
}
