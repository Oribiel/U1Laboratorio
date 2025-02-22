/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import DTOS.GuardarClienteDTO;
import Entidades.ClienteEntidad;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.IConexionBD;
import Persistencia.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class pruebasCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //CREAR CONEXION
// Crear conexión a la BD
        IConexionBD conexion = new ConexionBD();
        Connection conn = null;

        try {
            conn = conexion.crearConexion();
            JOptionPane.showMessageDialog(null, "✅ ¡Conexión exitosa a la base de datos!");

            // Instanciar ClienteDAO
            ClienteDAO clienteDAO = new ClienteDAO(conexion);
            
            //GUARDAR CLIENTE EN LA BD
            LocalDate fechaNacimiento = LocalDate.of(2005, Month.DECEMBER, 9);
            GuardarClienteDTO guardar = new GuardarClienteDTO("Jack Tadeo", "Murrieta", "Torres",fechaNacimiento, 1);
            
            ClienteEntidad clienteE= clienteDAO.guardar(guardar);
            System.out.println(clienteE.toString());
            

//            // Buscar clientes por laboratorio con ID = 1
//            int idLaboratorio = 1;
//            List<ClienteEntidad> clientes = clienteDAO.buscarClientesPorLaboratorio(idLaboratorio);
//
//            // Imprimir resultados
//            if (clientes != null && !clientes.isEmpty()) {
//                System.out.println("📋 Lista de clientes en el laboratorio ID " + idLaboratorio + ":");
//                for (ClienteEntidad cliente : clientes) {
//                    System.out.println(cliente);
//                }
//            } else {
//                System.out.println("⚠ No se encontraron clientes en el laboratorio ID " + idLaboratorio);
//            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "❌ Error al conectarse a la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        } catch (PersistenciaException ex) {
            Logger.getLogger(pruebasCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar la conexión si se abrió correctamente
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("🔒 Conexión cerrada correctamente.");
                } catch (SQLException ex) {
                    System.out.println("⚠ Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }
    }

}
