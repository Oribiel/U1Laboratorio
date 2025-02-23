/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOS.ClienteDTO;
import DTOS.EditarClienteDTO;
import DTOS.GuardarClienteDTO;
import DTOS.TablaClienteDTO;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IClienteNegocio {
    List<TablaClienteDTO> buscarClientesPorLaboratorio(int id) throws NegocioException;

    //validar Cliente y guardarlo 
    ClienteDTO guardar(GuardarClienteDTO cliente) throws NegocioException;

    // validarCliente
    ClienteDTO editar(EditarClienteDTO cliente) throws NegocioException;

    ClienteDTO eliminar(int id) throws NegocioException;

    ClienteDTO buscarPorId(int id) throws NegocioException;

    
}
