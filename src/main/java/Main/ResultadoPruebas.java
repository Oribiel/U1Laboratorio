/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import DTOS.AnalisisClienteDTO;
import DTOS.CategoriaDTO;
import DTOS.ClienteDTO;
import DTOS.GuardarClienteDTO;
import DTOS.ParametrosDTO;
import DTOS.PruebaAnalisisDTO;
import DTOS.ResultadoDTO;
import Entidades.AnalisisClienteEntidad;
import Entidades.CategoriaEntidad;
import Entidades.ClienteEntidad;
import Entidades.LaboratorioEntidad;
import Entidades.ParametrosEntidad;
import Entidades.PruebaAnalisis;
import Entidades.ResultadoEntidad;
import Entidades.ResultadoPorCliente;
import Negocio.ClienteNegocio;
import Negocio.NegocioException;
import Persistencia.AnalisisClienteDAO;
import Persistencia.CategoriasDAO;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.LaboratorioDAO;
import Persistencia.ParametrosEvaluacionDAO;
import Persistencia.PersistenciaException;
import Persistencia.PruebaAnalisisDAO;
import Persistencia.ResultadoDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ResultadoPruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, NegocioException, PersistenciaException {
        // Crear la conexión a la base de datos
        ConexionBD conexion = new ConexionBD();

// Insertar un Laboratorio
        LaboratorioDAO labDAO = new LaboratorioDAO(conexion);
        LaboratorioEntidad lab = labDAO.buscarLaboratorioPorid(3);
        System.out.println("Laboratorio en BD: " + lab.toString());

           // GUARDAR CLIENTE EN LA BD
            ClienteDAO clienteDAO = new ClienteDAO(conexion);
//            LocalDate fechaNacimiento = LocalDate.of(2005, Month.DECEMBER, 9);
//            GuardarClienteDTO guardar = new GuardarClienteDTO("Pepito", "Murrieta", "Torres",fechaNacimiento, lab.getIdLaboratorio());
            ClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);
            ClienteDTO clienteDTO= clienteNegocio.buscarPorId(15);
            System.out.println("Cliente guardado en la BD: "+ clienteDTO.toString());

// Obtener una Categoría en la BD
        CategoriasDAO categoriaDAO = new CategoriasDAO(conexion);
        CategoriaEntidad categoria = categoriaDAO.obtenerCategoriaPorId(4);
        System.out.println("Categoria Obtenida: " + categoria.toString());

// Insertar una Prueba de Análisis
        PruebaAnalisisDAO pruebaDAO = new PruebaAnalisisDAO(conexion);
        PruebaAnalisisDTO pruebaDTO = new PruebaAnalisisDTO("Glucosa en sangre", categoria.getIdCategoria(), lab.getIdLaboratorio());
        PruebaAnalisis pruebaEntidad = pruebaDAO.registrar(pruebaDTO);
        System.out.println("Prueba de análisis registrada: " + pruebaEntidad.toString());

// Insertar un Parámetro de Evaluación
        ParametrosEvaluacionDAO paramDAO = new ParametrosEvaluacionDAO();
        ParametrosDTO paramDTO = new ParametrosDTO("Glucosa", "70-100", pruebaEntidad.getIdPruebaAnalisis());
        ParametrosEntidad paramEntidad = paramDAO.registrarParametro(paramDTO);
        System.out.println("Parámetro registrado: " + paramEntidad.toString());

// Insertar un Análisis Cliente
        AnalisisClienteDAO analisisDAO = new AnalisisClienteDAO(conexion);
        int idCliente = Integer.parseInt(clienteDTO.getId());
        AnalisisClienteDTO analisisDTO = new AnalisisClienteDTO(12345, LocalDateTime.now(),idCliente);
        AnalisisClienteEntidad analisisEntidad = analisisDAO.generarUnAnalisisCliente(analisisDTO);
        System.out.println("Análisis de cliente registrado: " + analisisEntidad.toString());

// Insertar un Resultado
        ResultadoDAO resultadoDAO = new ResultadoDAO(conexion);
        ResultadoDTO resultadoDTO = new ResultadoDTO("85", paramEntidad.getIdParametroEvaluacion(), analisisEntidad.getIdidAnalisisCliente());
        ResultadoEntidad resultadoEntidad = resultadoDAO.crearResultado(resultadoDTO);
        System.out.println("Resultado registrado: " + resultadoEntidad.toString());

        //
        List<ResultadoPorCliente> resultados = resultadoDAO.obtenerResultadosPorCliente(15);

        if (resultados.isEmpty()) {
            System.out.println("No hay resultados para mostrar.");
        } else {
            System.out.println("\n--- RESULTADOS POR CLIENTE ---");
            for (ResultadoPorCliente resultado : resultados) {
                System.out.println(resultado);
            }
        }
    }
}

