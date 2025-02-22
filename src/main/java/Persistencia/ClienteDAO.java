/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOS.EditarClienteDTO;
import DTOS.GuardarClienteDTO;
import Entidades.ClienteEntidad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ClienteDAO implements IClienteDAO {

    private IConexionBD conexionBD;

    public ClienteDAO(IConexionBD conexion) {
        this.conexionBD = conexion;
    }

    @Override
    public List<ClienteEntidad> buscarClientesPorLaboratorio(int id) throws PersistenciaException {
        try {
            String consultaSQL = """
                                 SELECT
                                 	idCliente,
                                 	nombres,
                                 	apellidoPaterno,
                                 	apellidoMaterno, 
                                        fechaNacimiento
                                 FROM Clientes
                                 WHERE idLaboratorio = ?;                                 
                                 """;
            Connection conexion = this.conexionBD.crearConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            List<ClienteEntidad> ClientesEntidadLista = null;
            while (resultado.next()) {
                if (ClientesEntidadLista == null) {
                    ClientesEntidadLista = new ArrayList<>();
                }

                ClientesEntidadLista.add(this.convertirClienteEntidad(resultado));
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return ClientesEntidadLista;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PersistenciaException(ex.getMessage());
        }

    }

    //CONVERTIR CLIENTE A ENTIDAD 
    private ClienteEntidad convertirClienteEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("idCliente");
        String nombres = resultado.getString("nombres");
        String apellidoPaterno = resultado.getString("apellidoPaterno");
        String apellidoMaterno = resultado.getString("apellidoMaterno");
        // pasar la fecha a localDate
        Date sqlDate = resultado.getDate("fechaNacimiento");
        LocalDate fechaNacimiento = sqlDate.toLocalDate();
        int idLaboratorio = resultado.getInt("idLaboratorio");
        return new ClienteEntidad(id, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, idLaboratorio);
    }

    @Override
    public ClienteEntidad guardar(GuardarClienteDTO cliente) throws PersistenciaException {
        try {
            ClienteEntidad clienteEntidad;
            Connection conexion = this.conexionBD.crearConexion();
            String insertCliente = """
                                    INSERT INTO `Clientes` (`nombres`,
                                                           `apellidoPaterno`,
                                                           `apellidoMaterno`,
                                                           `fechaNacimiento`, 
                                                            'idLaboratorio')
                                                   VALUES (?, ? , ? , ?,?);
                                    """;

            PreparedStatement preparedStatement = conexion.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getNombres());
            preparedStatement.setString(2, cliente.getApellidoPaterno());
            preparedStatement.setString(3, cliente.getApellidoMaterno());
            preparedStatement.setDate(4, Date.valueOf(cliente.getFechaNacimiento()));
            //El laboratorio se introducire en DTO donde sera LabEntidad quien lo tendra
            preparedStatement.setInt(5,cliente.getIdLaboratorio());


            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La inserción del cliente falló, no se pudo insertar el registro.");
            }

            // obtener id generado
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                int idCliente = (resultado.getInt(1));

                resultado.close();
                preparedStatement.close();
                conexion.close();
                return buscarPorId(idCliente);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo y si el error persiste comuníquese con el encargado del sistema.");
        }
        // regresa un nulo

        return null;
    }

    @Override
    public ClienteEntidad guardarConTransaccion(GuardarClienteDTO cliente) throws PersistenciaException {
        return null;
    }

    @Override
    public ClienteEntidad editar(EditarClienteDTO cliente) throws PersistenciaException {
        try {
            ClienteEntidad clienteEntidad;
;            String updateSQL = """
                             UPDATE alumnos
                             SET nombres = ?,
                             apellidoPaterno = ?,
                             apellidoMaterno = ?,
                             fechaNacimiento=?
                             WHERE idAlumno = ?;
                             """;
            PreparedStatement prepararConsulta = this.conexionBD.crearConexion().prepareStatement(updateSQL);
            prepararConsulta.setString(1, cliente.getNombres());
            prepararConsulta.setString(2, cliente.getApellidoPaterno());
            prepararConsulta.setString(3, cliente.getApellidoMaterno());
            //getFecha = LocalDate
            prepararConsulta.setDate(4, Date.valueOf(cliente.getFechaNacimiento()));
            prepararConsulta.setInt(5, cliente.getId());

            int filasAfectadas = prepararConsulta.executeUpdate();
            prepararConsulta.close();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se pudo actualizar alumno");
            }

            return buscarPorId(cliente.getId());

        } catch (SQLException ex) {
            throw new PersistenciaException("Error : update no se pudo hacer intente de nuevo" + ex.getMessage());
        }

        
    }

    @Override
    public ClienteEntidad eliminar(int id) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.crearConexion()) {
            String consultaSQL = """
                             DELETE FROM Clientes
                             WHERE idCliente=?;
                             """;
            try (PreparedStatement prepararConsulta = conexion.prepareStatement(consultaSQL)) {
                prepararConsulta.setInt(1, id);
                int filasAfectadas = prepararConsulta.executeUpdate(); // Se usa executeUpdate()

                if (filasAfectadas == 0) {
                    throw new PersistenciaException("No se encontró un cliente con el ID proporcionado.");
                }
                prepararConsulta.close();
            }

            // Buscar el alumno actualizado después de marcarlo como eliminado
            return buscarPorId(id);

        } catch (SQLException e) {
            throw new PersistenciaException("Error al eliminar el cliente: " + e.getMessage());
        }
        
    }

    @Override
    public ClienteEntidad buscarPorId(int id) throws PersistenciaException {

        try {
            ClienteEntidad clienteEncontrado = null;
            String consultaSQL = """
                               SELECT * FROM Clientes WHERE idAlumno =?;
                               """;
            Connection conexion = this.conexionBD.crearConexion();
            PreparedStatement prepararConsulta = conexion.prepareStatement(consultaSQL);
            prepararConsulta.setInt(1, id);
            ResultSet resultado = prepararConsulta.executeQuery();

            if (resultado.next()) {
                // convertir cliente en entidad
                return clienteEncontrado = convertirClienteEntidad(resultado);

            }
            resultado.close();
            prepararConsulta.close();
            conexion.close();

        } catch (SQLException ex) {
            throw new PersistenciaException(ex.getMessage());
        }
        return null;

    }
    
}
