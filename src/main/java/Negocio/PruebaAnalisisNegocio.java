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
import Persistencia.IPruebaAnalisisDAO;
import Persistencia.PruebaAnalisisDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PruebaAnalisisNegocio implements IPruebaAnalisisNegocio {
    private IPruebaAnalisisDAO pruebaDAO;

    public PruebaAnalisisNegocio(Connection conexion) {
        this.pruebaDAO = new PruebaAnalisisDAO(conexion);
    }

    @Override
    public void registrarPrueba(PruebaAnalisisDTO pruebaDTO) {
        PruebaAnalisis prueba = new PruebaAnalisis(
            0,  // ID será generado automáticamente por la base de datos
            pruebaDTO.getNombre(),
            pruebaDTO.getIdCategoria(),
            pruebaDTO.getIdLaboratorio()
        );
        pruebaDAO.registrar(prueba);
    }

    @Override
    public List<PruebaAnalisisDTO> listarPruebas() {
        List<PruebaAnalisis> pruebas = pruebaDAO.listar();
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
    }

    @Override
    public PruebaAnalisisDTO obtenerPrueba(int id) {
        PruebaAnalisis p = pruebaDAO.buscarPorId(id);
        if (p == null) return null;

        return new PruebaAnalisisDTO(
            p.getIdPruebaAnalisis(),
            p.getNombre(),
            p.getIdCategoria(),
            p.getIdLaboratorio()
        );
    }
}


