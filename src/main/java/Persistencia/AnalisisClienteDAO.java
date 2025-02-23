/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOS.AnalisisClienteDTO;
import Entidades.AnalisisClienteEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AnalisisClienteDAO implements IAnalisisClientes{
    private IConexionBD conexion;

    public AnalisisClienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public AnalisisClienteEntidad generarUnAnalisisCliente(AnalisisClienteDTO analisisCliente) throws PersistenciaException {
         
        String sql = "INSERT INTO AnalisisClientes (nombre, folio, fechaYhoraCaptura,idCliente) VALUES (?, ?, ?,?)";
        try {
            Connection conexion = this.conexion.crearConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, analisisCliente.getIdidAnalisisCliente());
            stmt.setInt(2, analisisCliente.getFolio());
            stmt.setTimestamp(3, Timestamp.valueOf(analisisCliente.getFechaYhoraCaptura()));
            stmt.setInt(4, analisisCliente.getIdCliente());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se pudo insertar el analisisCliente en BD");
            }
            //obtener id generado de analisisCliente
            int idCliente = analisisCliente.getIdCliente();
            ResultSet resultado = stmt.getGeneratedKeys();
            if (resultado.next()) {
                int idAnalisisCliente = resultado.getInt(1);
                resultado.close();
                stmt.close();
                return buscarAnalisisClienteID(idAnalisisCliente, idCliente);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistenciaException ex) {
            Logger.getLogger(PruebaAnalisisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    
    
    private AnalisisClienteEntidad convertirAnalisisCliente(ResultSet resultado) throws SQLException{
       int id = resultado.getInt("idAnalisisCliente");
       int folio = resultado.getInt("folio");
        // Obtener el Timestamp desde el ResultSet
        Timestamp timestamp = resultado.getTimestamp("fechaYHoraCaptura");
        // Convertir a LocalDateTime
        LocalDateTime fechaYhoraCaptura = timestamp.toLocalDateTime();
       int idCliente = resultado.getInt("idLaboratorio");
       return new AnalisisClienteEntidad(id, folio, fechaYhoraCaptura, idCliente);    
    }
    public AnalisisClienteEntidad buscarAnalisisClienteID(int idAnalisisCliente, int idCliente) throws PersistenciaException{
         AnalisisClienteEntidad analisisEncontrado = null;
        try {
            String sql = "SELECT * FROM AnalisisClientes WHERE idAnalisisCliente = ? AND idCliente= ?";
            Connection connection = conexion.crearConexion();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAnalisisCliente);
            stmt.setInt(2, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // convertir Analisis en entidad
                    return analisisEncontrado = convertirAnalisisCliente(rs);
                }
                connection.close();
                stmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("No se encontro el analisis del cliente en la BD");
        }
        return analisisEncontrado;
        
    }
    
}
