/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import DTOS.EditarClienteDTO;
import DTOS.GuardarClienteDTO;
import Entidades.ClienteEntidad;
import Entidades.LaboratorioEntidad;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.IConexionBD;
import Persistencia.LaboratorioDAO;
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
            //Buscar el laboratorio en la base de datos
            
            LaboratorioDAO labDAO = new LaboratorioDAO(conexion);
            LaboratorioEntidad lab = labDAO.buscarLaboratorioPorid(1);
            System.out.println(lab.toString());
            
//             Instanciar ClienteDAO
            ClienteDAO clienteDAO = new ClienteDAO(conexion);
            
           // GUARDAR CLIENTE EN LA BD
            LocalDate fechaNacimiento = LocalDate.of(2005, Month.DECEMBER, 9);
            GuardarClienteDTO guardar = new GuardarClienteDTO("Jack Tadeo", "Murrieta", "Torres",fechaNacimiento, lab.getIdLaboratorio());
            
            ClienteEntidad clienteE= clienteDAO.guardar(guardar);
            System.out.println(clienteE.toString());
            
            //EDITAR CLIENTE EN LA BD
            LocalDate fechaNacimiento2 = LocalDate.of(2005, Month.OCTOBER, 12);
            EditarClienteDTO editCliente = new EditarClienteDTO(clienteE.getId(), "Jack", "Torres", "Murrieta", fechaNacimiento2);
            
            int idEditCliente = editCliente.getId();
            ClienteEntidad editar = clienteDAO.editar(editCliente);
            System.out.println(editar.toString());
            
            //ELIMINAR CLIENTE
            boolean eliminado = clienteDAO.eliminar(editar.getId());
            System.out.println("Cliente eliminado es: "+eliminado);
            
            //CATALAGO DE CLIENTES
            
            List<ClienteEntidad> clientes = clienteDAO.buscarClientesPorLaboratorio(1);
            System.out.println(clientes.toString());
            

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
