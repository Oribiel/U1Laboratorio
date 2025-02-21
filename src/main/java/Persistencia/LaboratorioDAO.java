/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.LaboratorioEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class LaboratorioDAO implements ILaboratorioDAO {
    private IConexionBD conexionBD;

    public LaboratorioDAO(IConexionBD conexion) {
        this.conexionBD = conexion;
    }

    @Override
    public LaboratorioEntidad buscarLaboratorioPorid(int id) throws SQLException {
        try {
            LaboratorioEntidad lab = null;
            Connection conexion = this.conexionBD.crearConexion();

            String codigoSQL = """
                               SELECT
                                    idLaboratorio,
                                    nombre,
                                    direccion
                               FROM Laboratorios
                               WHERE idLaboratorio = ?
                               """;

            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);

            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                lab = this.convertirLaboratorioEntidad(resultado);
            }

            resultado.close();
            preparedStatement.close();
            conexion.close();

            return lab;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("No se encuentra ese laboratorio en la BD");
        }
        return null;

    }
    //CONVERTIR LAB A ENTIDAD 
    private LaboratorioEntidad convertirLaboratorioEntidad(ResultSet resultado) throws SQLException {
        int idLaboratorio = resultado.getInt("idLaboratorio");
        String nombre = resultado.getString("nombre");
        String direccion = resultado.getString("direccion");
        return new LaboratorioEntidad(idLaboratorio, nombre,direccion);
        
    }
    
}
