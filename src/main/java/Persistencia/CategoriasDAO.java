/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author oribi
 */
import Entidades.CategoriaEntidad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriasDAO implements ICategoriasDAO {

    private IConexionBD conexionBD;

   
    public CategoriasDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

  @Override
    public List<CategoriaEntidad> obtenerCategorias() {
        List<CategoriaEntidad> categorias = new ArrayList<>();
        String query = "SELECT * FROM Categorias";

      
        try (Connection connection = conexionBD.crearConexion();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                categorias.add(convertirCategoriaEntidad(resultSet));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return categorias;
    }

 
    @Override
    public CategoriaEntidad obtenerCategoriaPorId(int idCategoria) {
        CategoriaEntidad categoria = null;
        String query = "SELECT * FROM Categorias WHERE idCategoria = ?";

      
        try (Connection connection = conexionBD.crearConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCategoria);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    categoria = convertirCategoriaEntidad(resultSet);
                }
                resultSet.close();
                
            }
            connection.close();
            preparedStatement.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categoria;
    }
    
    private CategoriaEntidad convertirCategoriaEntidad(ResultSet resultado) throws SQLException{
        int id= resultado.getInt("idCategoria");
        String nombre= resultado.getString("nombre");
        return new CategoriaEntidad(id, nombre);
    
    }
    
        

}

