/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOS.ClienteDTO;
import DTOS.EditarClienteDTO;
import DTOS.GuardarClienteDTO;
import DTOS.TablaClienteDTO;
import Entidades.ClienteEntidad;
import Persistencia.IClienteDAO;
import Persistencia.PersistenciaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class ClienteNegocio implements IClienteNegocio {
    private IClienteDAO clienteDAO;
    ClienteDTO clienteDTO;

    public ClienteNegocio(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    

    @Override
    public List<TablaClienteDTO> buscarClientesPorLaboratorio(int id) throws NegocioException {
        try {
            //ReglasDeNegocioParaBuscarAlumnos("Hola"); //1
            List<ClienteEntidad> ClientesEntidadLista = clienteDAO.buscarClientesPorLaboratorio(id); //2
            return convertirTablaClientesDTO(ClientesEntidadLista);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }

    }

    private List<TablaClienteDTO> convertirTablaClientesDTO(List<ClienteEntidad> clientes) {
        if (clientes == null) {
            return null;
        }
        List<TablaClienteDTO> clientesDTO = new ArrayList<>();
        for (ClienteEntidad item : clientes) {
            TablaClienteDTO dato = new TablaClienteDTO(
                    String.valueOf(item.getId()),
                    item.getNombres(),
                    item.getApellidoPaterno(),
                    item.getApellidoMaterno(),
                    String.valueOf(item.getFechaNacimiento())
            );
            clientesDTO.add(dato);
        }

        return clientesDTO;
    }
    
    private void validarInformacionGuardarCliente(String nombres, String apellidoP, String apellidoM) throws NegocioException {
        // expresiones regulares
        String validaXNombres = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{2,49}(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{2,49})*$";
        String validaApellidos = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,29}(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{1,29})*$";

        // Validar cada campo
        //.trim para quitar los espacios vacios y no se ejecute la excepcion
        validarCampo(nombres.trim(), validaXNombres, "Nombre inválido");
        validarCampo(apellidoP.trim(), validaApellidos, "Apellido Paterno inválido");
        validarCampo(apellidoM.trim(), validaApellidos, "Apellido Materno inválido");
        // Validar que no contengan palabras prohibidas
        validarSinGroserias(nombres, "Nombre contiene palabras prohibidas");
        validarSinGroserias(apellidoP, "Apellido Paterno contiene palabras prohibidas");
        validarSinGroserias(apellidoM, "Apellido Materno contiene palabras prohibidas");
    }
    
    private void validarCampo(String campo, String regex, String mensajeError) throws NegocioException {
        if (campo == null || !Pattern.compile(regex).matcher(campo).matches()) {
            throw new NegocioException(mensajeError);
        }
    }
    // validar si tiene grocerias
    private void validarSinGroserias(String campo, String mensajeError) throws NegocioException {
        String grocerias = "(?i).*\\b(puto|puta|joto|cabrón|pendejo|chingado|mierda|maldito|culero)\\b.*";
        if (campo != null && Pattern.compile(grocerias).matcher(campo).matches()) {
            throw new NegocioException(mensajeError);
        }
    }
    
    private void reglasDeNegocioGuardarCliente(GuardarClienteDTO cliente) throws NegocioException {
        if (cliente == null) {
            throw new NegocioException("El cliente no puede ser nulo.");
        }

        // Validar que los campos obligatorios no estén vacíos
        if (cliente.getNombres() == null || cliente.getNombres().trim().isEmpty()) {
            throw new NegocioException("El nombre del cliente es obligatorio.");
        }
        if (cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno es obligatorio.");
        }
        if (cliente.getApellidoMaterno() == null || cliente.getApellidoMaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido materno es obligatorio.");
        }
        if (cliente.getFechaNacimiento() == null || cliente.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento excede al dia de hoy");
        }
    }
    
    public ClienteDTO convertirClienteDTO(ClienteEntidad cliente) {
        if(cliente == null){
            return null;
        }
        String id = String.valueOf(cliente.getId());
        String nombres = cliente.getNombres();
        String apellidoP = cliente.getApellidoPaterno();
        String apellidoM = cliente.getApellidoMaterno();
        String fechaNacimiento = String.valueOf(cliente.getFechaNacimiento());
        ClienteDTO clienteDTO = new ClienteDTO(id, nombres, apellidoP, apellidoM,fechaNacimiento);
        return clienteDTO;
        
    }
    
    @Override
    public ClienteDTO guardar(GuardarClienteDTO cliente) throws NegocioException {
         try {
            validarInformacionGuardarCliente(cliente.getNombres(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno());
            reglasDeNegocioGuardarCliente(cliente);
            ClienteEntidad clienteEntidad = clienteDAO.guardar(cliente);
            // pasar entidad a un clienteDTO
            return convertirClienteDTO(clienteEntidad);
               
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }

        
    }
    private void reglasDeNegocioEditarCliente(EditarClienteDTO cliente) throws NegocioException{
        if (cliente == null) {
            throw new NegocioException("El cliente no puede ser nulo.");
        }

        // Validar que los campos obligatorios no estén vacíos
        if (cliente.getNombres() == null || cliente.getNombres().trim().isEmpty()) {
            throw new NegocioException("El nombre del cliente es obligatorio.");
        }
        if (cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno es obligatorio.");
        }
        if (cliente.getApellidoMaterno() == null || cliente.getApellidoMaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido materno es obligatorio.");
        }
        if (cliente.getFechaNacimiento() == null || cliente.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento excede al dia de hoy");
        }

    }

    @Override
    public ClienteDTO editar(EditarClienteDTO cliente) throws NegocioException {
        try {
            validarInformacionGuardarCliente(cliente.getNombres(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno());
            // reglas de negocio editar cliente
            reglasDeNegocioEditarCliente(cliente);
            ClienteEntidad clienteEntidad = clienteDAO.editar(cliente);
            // pasar entidad a un DTO
            return convertirClienteDTO(clienteEntidad);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo editar Cliente" + ex.getMessage());
        }

    }

    @Override
    public ClienteDTO eliminar(int id) throws NegocioException {
                try {
            // Intentar eliminar el cliente
            ClienteEntidad clienteEntidad = clienteDAO.eliminar(id);

            // Si el cliente no existe, retornar null
            if (clienteEntidad == null) {
                return null;
            }

            // Convertir a DTO antes de retornar
            return convertirClienteDTO(clienteEntidad);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Cliente no se eliminó: " + ex.getMessage());
        }
        
    }

    @Override
    public ClienteDTO buscarPorId(int id) throws NegocioException {
         try {
            ClienteEntidad clienteEntidad = clienteDAO.buscarPorId(id);
            if(clienteEntidad == null){
                throw new NegocioException("Id no se encuentra en la base de datos");
            }
            return convertirClienteDTO(clienteEntidad);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Cliente no se encontro");
        }
    }
    
    
}
