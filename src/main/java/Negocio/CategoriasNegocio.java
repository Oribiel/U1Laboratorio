/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOS.CategoriaDTO;
import Entidades.CategoriaEntidad;
import Negocio.NegocioException;
import Persistencia.ICategoriasDAO;
import Persistencia.PersistenciaException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CategoriasNegocio implements ICategoriasNegocio {
      private final ICategoriasDAO categoriasDAO;

    public CategoriasNegocio(ICategoriasDAO categoriasDAO) {
        this.categoriasDAO = categoriasDAO;
    }

    @Override
    public List<CategoriaDTO> obtenerCategorias(List<CategoriaEntidad> categorias) throws NegocioException {
        if (categorias == null) {
            return null;
        }
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();
        for (CategoriaEntidad item : categorias) {
            CategoriaDTO dato = new CategoriaDTO(
                    item.getIdCategoria(),
                    item.getNombre()
            );
            categoriasDTO.add(dato);
        }

        return categoriasDTO;
    }


@Override
    public CategoriaDTO obtenerCategoriaPorId(int idCategoria) throws NegocioException {
        CategoriaDTO categoriaDTO = null;
        try {
            CategoriaEntidad categoria = categoriasDAO.obtenerCategoriaPorId(idCategoria);
            if (categoria == null) {
                throw new NegocioException("No se encontró la categoría con ID: " + idCategoria);
            }
            return categoriaDTO = convertirCategoriaDTO(categoria);
        } catch (SQLException ex) {
              Logger.getLogger(CategoriasNegocio.class.getName()).log(Level.SEVERE, null, ex);
          }
          return categoriaDTO;
    }
    
    public CategoriaDTO convertirCategoriaDTO(CategoriaEntidad categoria) {
        if (categoria == null) {
            return null;
        }
        int id = categoria.getIdCategoria();
        String nombre = categoria.getNombre();
        CategoriaDTO categoriaDTO = new CategoriaDTO(id, nombre);
        return categoriaDTO;
    
    }
}
