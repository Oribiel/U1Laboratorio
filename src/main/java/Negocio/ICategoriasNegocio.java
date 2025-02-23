/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOS.CategoriaDTO;
import Entidades.CategoriaEntidad;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oribi
 */
public interface ICategoriasNegocio {

    public List<CategoriaDTO> obtenerCategorias(List<CategoriaEntidad> categorias) throws NegocioException;

    public CategoriaDTO obtenerCategoriaPorId(int idCategoria) throws NegocioException;

}

