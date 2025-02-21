/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Entidades.PruebaAnalisis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PruebaAnalisisDAO implements IPruebaAnalisisDAO {
    private Connection conexion;

    public PruebaAnalisisDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void registrar(PruebaAnalisis prueba) {
        String sql = "INSERT INTO PruebasAnalisis (nombre, idCategoria, idLaboratorio) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, prueba.getNombre());
            stmt.setInt(2, prueba.getIdCategoria());
            stmt.setInt(3, prueba.getIdLaboratorio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PruebaAnalisis> listar() {
        List<PruebaAnalisis> lista = new ArrayList<>();
        String sql = "SELECT * FROM PruebasAnalisis";

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new PruebaAnalisis(
                    rs.getInt("idPruebaAnalisis"),
                    rs.getString("nombre"),
                    rs.getInt("idCategoria"),
                    rs.getInt("idLaboratorio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public PruebaAnalisis buscarPorId(int id) {
        String sql = "SELECT * FROM PruebasAnalisis WHERE idPruebaAnalisis = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PruebaAnalisis(
                        rs.getInt("idPruebaAnalisis"),
                        rs.getString("nombre"),
                        rs.getInt("idCategoria"),
                        rs.getInt("idLaboratorio")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
