/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author oribi
 */
public class ConexionBD implements IConexionBD {

   
    private final String USUARIO = "root";
    private final String CONTRASEÑA = "1234";
    //NECESARIOS PARA LA CONEXION
    private final String SERVIDOR = "127.0.0.1";//localhost
    private final String BASE_DE_DATOS = "LaboratoriosBD";
    private final String URL = "jdbc:mysql://" + SERVIDOR + "/" + BASE_DE_DATOS;

   
    public Connection crearConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }

}