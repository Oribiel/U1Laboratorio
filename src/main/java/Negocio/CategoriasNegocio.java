/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Persistencia.ICategoriasDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oribi
 */
public class CategoriasNegocio implements ICategoriasNegocio {
    private ICategoriasDAO categoriasDAO;

    public CategoriasNegocio(ICategoriasDAO categoriasDAO) {
        this.categoriasDAO = categoriasDAO;
    }

    @Override
    public List<String> obtenerCategorias() throws SQLException {
        return categoriasDAO.obtenerCategorias();
    }

    @Override
    public String obtenerCategoriaPorId(int idCategoria) throws SQLException {
        return categoriasDAO.obtenerCategoriaPorId(idCategoria);
    }
}

