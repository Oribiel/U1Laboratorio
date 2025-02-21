/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import DTOS.PruebaAnalisisDTO;
import Negocio.IPruebaAnalisisNegocio;
import Negocio.PruebaAnalisisNegocio;
import Persistencia.ConexionBD;
import Persistencia.IConexionBD;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author oribi
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MainPruebas {
    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();
        Connection conexion = null;

        try {
            conexion = conexionBD.crearConexion();
            IPruebaAnalisisNegocio negocio = new PruebaAnalisisNegocio(conexion);

            // Registrar una nueva prueba
            PruebaAnalisisDTO nuevaPrueba = new PruebaAnalisisDTO(0, "Prueba de Sangre", 1, 1);
            negocio.registrarPrueba(nuevaPrueba);
            System.out.println("Prueba registrada correctamente.");

            // Listar pruebas
            List<PruebaAnalisisDTO> pruebas = negocio.listarPruebas();
            for (PruebaAnalisisDTO p : pruebas) {
                System.out.println("ID: " + p.getIdPruebaAnalisis() + ", Nombre: " + p.getNombre());
            }

            // Buscar prueba por ID
            int idBuscado = 1;
            PruebaAnalisisDTO prueba = negocio.obtenerPrueba(idBuscado);
            if (prueba != null) {
                System.out.println("Prueba encontrada: " + prueba.getNombre());
            } else {
                System.out.println("Prueba con ID " + idBuscado + " no encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                    System.out.println("Conexion cerrada correctamente.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


