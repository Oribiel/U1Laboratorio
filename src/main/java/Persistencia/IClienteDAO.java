/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import DTOS.EditarClienteDTO;
import DTOS.GuardarClienteDTO;
import Entidades.ClienteEntidad;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IClienteDAO {
    
    List<ClienteEntidad> buscarClientesPorLaboratorio(int id) throws PersistenciaException;

    ClienteEntidad guardar(GuardarClienteDTO cliente) throws PersistenciaException;

    ClienteEntidad guardarConTransaccion(GuardarClienteDTO cliente) throws PersistenciaException;

    ClienteEntidad editar(EditarClienteDTO cliente) throws PersistenciaException;

    ClienteEntidad eliminar(int id) throws PersistenciaException;

    ClienteEntidad buscarPorId(int id) throws PersistenciaException;
    
}
