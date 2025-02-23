/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author oribi
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO implements ICategoriasDAO {

    private IConexionBD conexionBD;

   
    public CategoriasDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

  
    public List<String> obtenerCategorias() throws SQLException {
        List<String> categorias = new ArrayList<>();
        String query = "SELECT nombre FROM Categorias";

      
        try (Connection connection = conexionBD.crearConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                categorias.add(resultSet.getString("nombre"));
            }
        }

        return categorias;
    }

 
    public String obtenerCategoriaPorId(int idCategoria) throws SQLException {
        String categoria = null;
        String query = "SELECT nombre FROM Categorias WHERE idCategoria = ?";

      
        try (Connection connection = conexionBD.crearConexion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCategoria);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    categoria = resultSet.getString("nombre");
                }
            }
        }

        return categoria;
    }
        

}

