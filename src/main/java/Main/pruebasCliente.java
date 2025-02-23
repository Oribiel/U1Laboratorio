/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import DTOS.ClienteDTO;
import DTOS.EditarClienteDTO;
import DTOS.GuardarClienteDTO;
import DTOS.TablaClienteDTO;
import Entidades.ClienteEntidad;
import Entidades.LaboratorioEntidad;
import Negocio.ClienteNegocio;
import Negocio.NegocioException;
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
            ClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);
            
           // GUARDAR CLIENTE EN LA BD
            LocalDate fechaNacimiento = LocalDate.of(2005, Month.DECEMBER, 9);
            GuardarClienteDTO guardar = new GuardarClienteDTO("Pepito", "Murrieta", "Torres",fechaNacimiento, lab.getIdLaboratorio());
            
            ClienteDTO clienteDTO= clienteNegocio.guardar(guardar);
            System.out.println("Cliente guardado en la BD: "+ clienteDTO.toString());
//            
//            //EDITAR CLIENTE EN LA BD
            LocalDate fechaNacimiento2 = LocalDate.of(2005, Month.OCTOBER, 12);
            int idGuardar= Integer.parseInt(clienteDTO.getId());
            EditarClienteDTO editCliente = new EditarClienteDTO(idGuardar, "Jack", "Torres", "Murrieta", fechaNacimiento2);
            ClienteDTO clienteDto1 =  clienteNegocio.editar(editCliente);
            System.out.println("Cliente editado en la BD: "+clienteDto1);

//            //ELIMINAR CLIENTE
              ClienteDTO eliminado = clienteNegocio.eliminar(idGuardar);
              System.out.println("Cliente eliminado en la BD: "+eliminado.toString());
            
//            //CATALAGO DE CLIENTES
            
            List<TablaClienteDTO> clientes = clienteNegocio.buscarClientesPorLaboratorio(1);
            System.out.println(clientes.toString());
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "❌ Error al conectarse a la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        } catch (NegocioException ex) {
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
