/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import DTOS.PruebaAnalisisDTO;
import Entidades.PruebaAnalisis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PruebaAnalisisDAO implements IPruebaAnalisisDAO {
    
    private IConexionBD conexion;

    public PruebaAnalisisDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

   @Override
    public PruebaAnalisis registrar(PruebaAnalisisDTO prueba) {
        
        String sql = "INSERT INTO PruebasAnalisis (nombre, idCategoria, idLaboratorio) VALUES (?, ?, ?)";
        try {
            Connection conexion = this.conexion.crearConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, prueba.getNombre());
            stmt.setInt(2, prueba.getIdCategoria());
            stmt.setInt(3, prueba.getIdLaboratorio());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se pudo insertar la Prueba Analisis");
            }
            //obtener id generado de pruebaAnalisis
            ResultSet resultado = stmt.getGeneratedKeys();
            if (resultado.next()) {
                int idPrueba = resultado.getInt(1);
                int idLab = resultado.getInt(4);
                resultado.close();
                stmt.close();
                return buscarPorId(idPrueba, idLab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistenciaException ex) {
            Logger.getLogger(PruebaAnalisisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    @Override
    public List<PruebaAnalisis> listarPruebasAnalisis(int idLaboratorio) throws PersistenciaException {
        try{
        String sql = "SELECT * FROM PruebasAnalisis WHERE idLaboratorio = ?";
        
        Connection conexion = this.conexion.crearConexion();
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setInt(1, idLaboratorio);
        ResultSet rs = pstmt.executeQuery();
        
        List<PruebaAnalisis> listaPruebas = null;
            while (rs.next()) {
                if(listaPruebas==null){
                    listaPruebas = new ArrayList<>();
                }
                listaPruebas.add(convertirPruebaAnalisisEntidad(rs));
            }
            rs.close();
            conexion.close();
            pstmt.close();
            return listaPruebas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

 @Override
    public PruebaAnalisis buscarPorId(int idPrueba,int idLaboratorio) throws PersistenciaException {

        PruebaAnalisis pruebaEncontrada = null;
        try {
            String sql = "SELECT * FROM PruebasAnalisis WHERE idPruebaAnalisis = ? AND idLaboratorio= ?";
            Connection connection = conexion.crearConexion();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idPrueba);
            stmt.setInt(2, idLaboratorio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // convertir Prueba en entidad
                    return pruebaEncontrada = convertirPruebaAnalisisEntidad(rs);
                }
                connection.close();
                stmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("No se encontro la PruebaAnalisis en la BD");
        }
        return pruebaEncontrada;
    }
   
   private PruebaAnalisis convertirPruebaAnalisisEntidad(ResultSet resultado) throws SQLException{
       int id = resultado.getInt("idPruebaAnalisis");
       String nombre = resultado.getString("nombre");
       int idCategoria = resultado.getInt("idCategoria");
       int idLaboratorio = resultado.getInt("idLaboratorio");
       return new PruebaAnalisis(id, nombre, idCategoria, idLaboratorio);

   } 
}
