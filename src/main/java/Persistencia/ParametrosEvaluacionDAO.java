/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOS.ParametrosDTO;
import Entidades.ParametrosEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public ParametrosEntidad registrarParametro(ParametrosDTO parametro) {
        String sql = "INSERT INTO parametrosevaluacion (nombre, rango, idPruebaAnalisis) VALUES (?, ?, ?)";
        try {
            Connection conexion = this.conexionBD.crearConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, parametro.getNombre());
            stmt.setString(2, parametro.getRango());
            stmt.setInt(3, parametro.getIdPruebaAnalisis());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se pudo insertar el parametro");
            }
            //obtener id generado de parametroEvaluacion
            int idPA = parametro.getIdPruebaAnalisis();
            ResultSet resultado = stmt.getGeneratedKeys();
            if (resultado.next()) {
                int idParametroE = resultado.getInt(1);
                resultado.close();
                stmt.close();
                return obtenerPruebaAnalisis(idParametroE, idPA);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistenciaException ex) {
            Logger.getLogger(PruebaAnalisisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private ParametrosEntidad obtenerPruebaAnalisis(int idParametro, int idPruebaAnalisis) throws PersistenciaException {
        
        ParametrosEntidad paramEncontrado = null;
        try {
            String sql = "SELECT * FROM ParametrosEvaluacion WHERE idParametroEvaluacion = ? AND idPruebaAnalisis= ?";
            Connection connection = conexionBD.crearConexion();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idParametro);
            stmt.setInt(2, idPruebaAnalisis);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // convertir parametro en entidad
                    return paramEncontrado = convertirParamEntidad(rs);
                }
                connection.close();
                stmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("No se encontro la PruebaAnalisis en la BD");
        }
        return paramEncontrado;
    }

    @Override
    public ParametrosEntidad eliminarParametroEnPrueba(int idParam, int idPrueba) throws PersistenciaException {
         try (Connection conexion = this.conexionBD.crearConexion()) {
            String consultaSQL = """
                             DELETE FROM ParametrosEvaluacion WHERE idParametroEvaluacion = ? AND idPruebaAnalisis= ?
                             """;
            try (PreparedStatement prepararConsulta = conexion.prepareStatement(consultaSQL)) {
                prepararConsulta.setInt(1, idParam);
                prepararConsulta.setInt(2, idPrueba);
                ParametrosEntidad eliminado= obtenerPruebaAnalisis(idParam,idPrueba);
                int filasAfectadas = prepararConsulta.executeUpdate(); // Se usa executeUpdate()

                if (filasAfectadas == 0) {
                    throw new PersistenciaException("No se encontró un parametro Evaluacion en la BD.");
                }
                return eliminado; // retorna el paramEliminado  en la BD
            }
            

        } catch (SQLException e) {
            throw new PersistenciaException("Error al eliminar el parametro de la prueba: " + e.getMessage());
        }
        
        
    }
    
    private ParametrosEntidad convertirParamEntidad(ResultSet resultado) throws SQLException{
       int id = resultado.getInt("idParametroEvaluacion");
       String nombre = resultado.getString("nombre");
       String rango = resultado.getString("rango");
       int idPrueba = resultado.getInt("idPruebaAnalisis");
       return new ParametrosEntidad(id, nombre, rango, idPrueba);
        
    }

    @Override
    public List<ParametrosEntidad> parametrosEnUnaPrueba(int idPruebaAnalisis) {
        try{
        String sql = "SELECT * FROM ParametrosEvaluacion WHERE idPruebaAnalisis = ?";
        
        Connection conexion = this.conexionBD.crearConexion();
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setInt(1, idPruebaAnalisis);
        ResultSet rs = pstmt.executeQuery();
        
        List<ParametrosEntidad> listaParametros = null;
            while (rs.next()) {
                if(listaParametros==null){
                    listaParametros = new ArrayList<>();
                }
                listaParametros.add(convertirParamEntidad(rs));
            }
            rs.close();
            conexion.close();
            pstmt.close();
            return listaParametros;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
