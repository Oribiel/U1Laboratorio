/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOS.AnalisisClienteDTO;
import Entidades.AnalisisClienteEntidad;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IAnalisisClientes {
   public AnalisisClienteEntidad generarUnAnalisisCliente(AnalisisClienteDTO analisisCliente)throws PersistenciaException;
   List <AnalisisClienteEntidad> listarAnalisisPorCliente(int idCliente)throws PersistenciaException;
    
}
