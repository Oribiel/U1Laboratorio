/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oribi
 */
public interface ICategoriasNegocio {
    List<String> obtenerCategorias() throws SQLException;
    String obtenerCategoriaPorId(int idCategoria) throws SQLException;
}

