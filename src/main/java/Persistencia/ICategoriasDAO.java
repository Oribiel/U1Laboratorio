/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.CategoriaEntidad;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oribi
 */
public interface ICategoriasDAO {
    
    List<CategoriaEntidad> obtenerCategorias() throws SQLException;
    CategoriaEntidad obtenerCategoriaPorId(int idCategoria) throws SQLException;
}
