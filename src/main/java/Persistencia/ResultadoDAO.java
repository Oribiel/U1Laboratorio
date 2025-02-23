/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOS.AnalisisClienteDTO;
import DTOS.ResultadoDTO;
import Entidades.ResultadoEntidad;
import Entidades.ResultadoPorCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ResultadoDAO implements IResultados {
        private IConexionBD conexionBD;
    
    public ResultadoDAO(ConexionBD conexion) {
        this.conexionBD = new ConexionBD(); 
    }

    @Override
    public ResultadoEntidad crearResultado(ResultadoDTO resultado) throws PersistenciaException {
        String sql = "INSERT INTO Resultados (resultadoParametro, idParametroEvaluacion, idAnalisisCliente) VALUES (?, ?, ?)";
        try {
            Connection conexion = this.conexionBD.crearConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, resultado.getResultadoParametro());
            stmt.setInt(2, resultado.getIdParametroEvaluacion());
            stmt.setInt(3, resultado.getIdAnalisisCliente());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se pudo insertar el Resultado");
            }
            //obtener id generado de Resultado
            int idParam = resultado.getIdParametroEvaluacion();
            int idAC = resultado.getIdAnalisisCliente();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                int idResultado = resultSet.getInt(1);
                resultSet.close();
                stmt.close();
                return buscarResultadoEntidad(idResultado, idParam, idAC);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistenciaException ex) {
            Logger.getLogger(PruebaAnalisisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public List<ResultadoPorCliente> obtenerResultadosPorCliente(int idCliente) throws PersistenciaException {
        List<ResultadoPorCliente> listaResultados = new ArrayList<>();

        String sql = """
                 SELECT 
                     pa.nombre AS nombrePrueba,
                     r.idResultado, 
                     r.resultadoParametro, 
                     p.nombre AS parametro, 
                     p.rango,
                     ac.folio AS folioAnalisis,
                     c.nombres AS nombreCliente, 
                     c.apellidoPaterno, 
                     c.apellidoMaterno
                 FROM Resultados r
                 JOIN ParametrosEvaluacion p ON r.idParametroEvaluacion = p.idParametroEvaluacion
                 JOIN PruebasAnalisis pa ON p.idPruebaAnalisis = pa.idPruebaAnalisis
                 JOIN AnalisisClientes ac ON r.idAnalisisCliente = ac.idAnalisisCliente
                 JOIN Clientes c ON ac.idCliente = c.idCliente
                 WHERE c.idCliente = ?;
                 """;

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();

            Map<String, ResultadoPorCliente> resultadosMap = new HashMap<>();

            while (rs.next()) {
                String nombrePrueba = rs.getString("nombrePrueba");

                // Si la prueba no está en el mapa, la agregamos
                if (!resultadosMap.containsKey(nombrePrueba)) {
                    ResultadoPorCliente resultadoPrueba = new ResultadoPorCliente(
                            nombrePrueba,
                            rs.getInt("folioAnalisis"),
                            rs.getString("nombreCliente"),
                            rs.getString("apellidoPaterno"),
                            rs.getString("apellidoMaterno")
                    );
                    resultadosMap.put(nombrePrueba, resultadoPrueba);
                }

                // Agregamos el resultado a la prueba correspondiente
                ResultadoPorCliente resultado = new ResultadoPorCliente(
                        rs.getInt("idResultado"),
                        rs.getString("resultadoParametro"),
                        rs.getString("parametro"),
                        rs.getString("rango"),
                        rs.getInt("folioAnalisis"),
                        rs.getString("nombreCliente"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno")
                );

                resultadosMap.get(nombrePrueba).agregarResultado(resultado);
            }

            listaResultados.addAll(resultadosMap.values());

        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener resultados por cliente");
        }

        return listaResultados;
    }
    
    
    private ResultadoEntidad convertirResultadoEntidad(ResultSet resultado) throws SQLException{
        int id = resultado.getInt("idResultado");
        String resultadoParametro = resultado.getString("resultadoParametro");
        int idParametroEvaluacion = resultado.getInt("idParametroEvaluacion");
        int idAnalisisCliente = resultado.getInt("idAnalisisCliente");
        return new ResultadoEntidad(id, resultadoParametro, idParametroEvaluacion, idAnalisisCliente);

    }
    
    private ResultadoEntidad buscarResultadoEntidad(int idResultado, int idParam,int idAnalisisCliente) throws PersistenciaException{
                
        ResultadoEntidad paramEncontrado = null;
        try {
            String sql = "SELECT * FROM Resultados WHERE idResultado = ? AND idParametroEvaluacion = ? AND idAnalisisCliente = ?";
            Connection connection = conexionBD.crearConexion();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idResultado);
            stmt.setInt(2, idParam);
            stmt.setInt(3, idAnalisisCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // convertir Resultado en entidad
                    return paramEncontrado = convertirResultadoEntidad(rs);
                }
                connection.close();
                stmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("No se encontro el Resultado en la BD");
        }
        return paramEncontrado;
    }
    
    
    private ResultadoPorCliente convertirResultadoPorCliente(ResultSet rs) throws SQLException {
    return new ResultadoPorCliente(
        rs.getInt("idResultado"),
        rs.getString("resultadoParametro"),
        rs.getString("parametro"),
        rs.getString("rango"),
        rs.getInt("folioAnalisis"),
        rs.getString("nombreCliente"),
        rs.getString("apellidoPaterno"),
        rs.getString("apellidoMaterno")
    );
}

    @Override
    public List<ResultadoPorCliente> ResultadosPorCliente(int idAnalisisCliente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
    
    

