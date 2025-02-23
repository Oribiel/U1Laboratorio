/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import DTOS.ParametrosDTO;
import DTOS.PruebaAnalisisDTO;
import Entidades.CategoriaEntidad;
import Entidades.LaboratorioEntidad;
import Entidades.ParametrosEntidad;
import Entidades.PruebaAnalisis;
import Negocio.IPruebaAnalisisNegocio;
import Negocio.NegocioException;
import Persistencia.CategoriasDAO;
import Persistencia.ConexionBD;
import Persistencia.IConexionBD;
import Persistencia.IPruebaAnalisisDAO;
import Persistencia.LaboratorioDAO;
import Persistencia.ParametrosEvaluacionDAO;
import Persistencia.PersistenciaException;
import Persistencia.PruebaAnalisisDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author oribi
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class MainPruebas {
    public static void main(String[] args) {
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
            
            //Obtener Las Categorias
            CategoriasDAO categoriaDAO = new CategoriasDAO(conexion);
            List<CategoriaEntidad> categorias = categoriaDAO.obtenerCategorias();
            
            System.out.println("Categorias en la BD: "+ categorias.toString());
            
            // obtener una categoria 
            CategoriaEntidad categoria = categoriaDAO.obtenerCategoriaPorId(2);
            System.out.println("Categoria Obtenida: "+categoria.toString());
            
            // Guardar Una pruebaAnalisis
            PruebaAnalisisDAO pruebaDAO = new PruebaAnalisisDAO(conexion);
            PruebaAnalisisDTO pruebaDTO = new PruebaAnalisisDTO("nuevaPrueba",categoria.getIdCategoria(),lab.getIdLaboratorio());
            PruebaAnalisis pruebaEntidad = pruebaDAO.registrar(pruebaDTO);
            System.out.println("La prueba registrada es: "+pruebaEntidad.toString());
            
            //Registrar PARAMETROS EN UNA PRUEBA
            ParametrosEvaluacionDAO paramDAO = new ParametrosEvaluacionDAO();
            ParametrosDTO paramDTO = new ParametrosDTO("ParametroPrueba1", "ml",pruebaEntidad.getIdPruebaAnalisis() );
            ParametrosEntidad paramEntidad = paramDAO.registrarParametro(paramDTO);
            System.out.println("Parametro anadido: "+ paramEntidad.toString());
            
            //ELIMINAR PARAM EN UNA PRUEBA
//            ParametrosEntidad eliminado = paramDAO.eliminarParametroEnPrueba(paramEntidad.getIdParametroEvaluacion(),2);
//            System.out.println("param eliminado: "+eliminado.toString());
            
            //LISTAR PARAMETROS QUE ESTAN EN UNA PRUEBA
            List<ParametrosEntidad> paramsEntidad = paramDAO.parametrosEnUnaPrueba(2);
            System.out.println("Parametros en una prueba: "+paramsEntidad);
//            //Listar Pruebas
//            List<PruebaAnalisis> pruebasExistentes = pruebaDAO.listarPruebasAnalisis(lab.getIdLaboratorio());
//            System.out.println("Pruebas en la BD: "+pruebasExistentes.toString());
//            
//            //BuscarPrueba
//            PruebaAnalisis pruebaEncontrada = pruebaDAO.buscarPorId(2, lab.getIdLaboratorio());
//            System.out.println("PruebaEncontrada: "+pruebaEncontrada);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "❌ Error al conectarse a la base de datos: " + ex.getMessage());
            ex.printStackTrace();
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




