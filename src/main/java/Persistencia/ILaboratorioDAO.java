/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.LaboratorioEntidad;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public interface ILaboratorioDAO {
    
    //obtener Laboratorio entidad
    LaboratorioEntidad buscarLaboratorioPorid(int id) throws SQLException;
    
}
