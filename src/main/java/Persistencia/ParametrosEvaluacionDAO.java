/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author oribi
 */
public class ParametrosEvaluacionDAO implements IParametrosEvaluacionDAO {

    private IConexionBD conexionBD;
    
    public ParametrosEvaluacionDAO() {
        this.conexionBD = new ConexionBD(); 
    }
    
    @Override
    public void registrarParametro(String nombre, String rango, String nombrePrueba) {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            // Establecer conexión a la base de datos
            connection = conexionBD.crearConexion();

            // Obtener el idPruebaAnalisis usando el nombre de la prueba
            int idPruebaAnalisis = obtenerIdPruebaAnalisis(nombrePrueba);
            
            if (idPruebaAnalisis == 0) {
                System.out.println("Error: No se encontró el idPruebaAnalisis para el nombre de prueba: " + nombrePrueba);
                return;  // Evitar la inserción si no se encontró el idPruebaAnalisis
            }

            // Insertar el parámetro de evaluación
            String sql = "INSERT INTO ParametrosEvaluacion (nombre, rango, idPruebaAnalisis) VALUES (?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, rango);
            stmt.setInt(3, idPruebaAnalisis);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private int obtenerIdPruebaAnalisis(String nombrePrueba) {
        int idPrueba = 0;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establecer conexión a la base de datos
            connection = conexionBD.crearConexion();
            String sql = "SELECT idPruebaAnalisis FROM PruebasAnalisis WHERE nombre = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nombrePrueba);
            rs = stmt.executeQuery();
            if (rs.next()) {
                idPrueba = rs.getInt("idPruebaAnalisis");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return idPrueba;
    }
}
