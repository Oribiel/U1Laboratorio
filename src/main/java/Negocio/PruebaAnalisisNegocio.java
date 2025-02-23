/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author oribi
 */
import DTOS.PruebaAnalisisDTO;
import Entidades.PruebaAnalisis;
import Persistencia.IConexionBD;
import Persistencia.IPruebaAnalisisDAO;
import Persistencia.PruebaAnalisisDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PruebaAnalisisNegocio implements IPruebaAnalisisNegocio {
    private IPruebaAnalisisDAO pruebaDAO;
    private IConexionBD conexionBD;

    public PruebaAnalisisNegocio(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
        this.pruebaDAO = new PruebaAnalisisDAO(conexionBD); // Pasamos IConexionBD al DAO
    }

    @Override
    public void registrarPrueba(PruebaAnalisisDTO pruebaDTO) {
        try (Connection conexion = conexionBD.crearConexion()) {  // Obtenemos la conexión
            PruebaAnalisis prueba = new PruebaAnalisis(
                0, 
                pruebaDTO.getNombre(),
                pruebaDTO.getIdCategoria(),
                pruebaDTO.getIdLaboratorio()
            );
            pruebaDAO.registrar(prueba, conexion);  // Pasamos la conexión al DAO
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PruebaAnalisisDTO> listarPruebas() {
        try (Connection conexion = conexionBD.crearConexion()) {  // Obtenemos la conexión
            List<PruebaAnalisis> pruebas = pruebaDAO.listar(conexion);  // Pasamos la conexión
            List<PruebaAnalisisDTO> dtos = new ArrayList<>();
            for (PruebaAnalisis p : pruebas) {
                dtos.add(new PruebaAnalisisDTO(
                    p.getIdPruebaAnalisis(),
                    p.getNombre(),
                    p.getIdCategoria(),
                    p.getIdLaboratorio()
                ));
            }
            return dtos;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public PruebaAnalisisDTO obtenerPrueba(int id) {
        try (Connection conexion = conexionBD.crearConexion()) {  // Obtenemos la conexión
            PruebaAnalisis p = pruebaDAO.buscarPorId(id, conexion);  // Pasamos la conexión
            if (p == null) return null;

            return new PruebaAnalisisDTO(
                p.getIdPruebaAnalisis(),
                p.getNombre(),
                p.getIdCategoria(),
                p.getIdLaboratorio()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}




