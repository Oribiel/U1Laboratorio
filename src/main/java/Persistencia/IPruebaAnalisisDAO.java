/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

/**
 *
 * @author oribi
 */
import DTOS.PruebaAnalisisDTO;
import Entidades.PruebaAnalisis;
import java.sql.Connection;
import java.util.List;

public interface IPruebaAnalisisDAO {
<<<<<<< HEAD
    void registrar(PruebaAnalisis prueba, Connection conexion);  // Modificado para recibir Connection
    List<PruebaAnalisis> listar(Connection conexion);  // Modificado para recibir Connection
    PruebaAnalisis buscarPorId(int id, Connection conexion);  // Modificado para recibir Connection
=======
    PruebaAnalisis registrar(PruebaAnalisisDTO prueba);
    
    List<PruebaAnalisis> listarPruebasAnalisis(int idLaboratorio)throws PersistenciaException;
    
    PruebaAnalisis buscarPorId(int idPrueba,int idLaboratorio)throws PersistenciaException;
>>>>>>> 72b37ca97ee56eefd0701df47724cd721c006842
}


