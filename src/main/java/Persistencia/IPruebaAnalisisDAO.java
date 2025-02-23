/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

/**
 *
 * @author oribi
 */
import Entidades.PruebaAnalisis;
import java.sql.Connection;
import java.util.List;

public interface IPruebaAnalisisDAO {
    void registrar(PruebaAnalisis prueba, Connection conexion);  // Modificado para recibir Connection
    List<PruebaAnalisis> listar(Connection conexion);  // Modificado para recibir Connection
    PruebaAnalisis buscarPorId(int id, Connection conexion);  // Modificado para recibir Connection
}


