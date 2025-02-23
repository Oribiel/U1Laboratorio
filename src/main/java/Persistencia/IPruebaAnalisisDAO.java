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
import java.util.List;

public interface IPruebaAnalisisDAO {
    PruebaAnalisis registrar(PruebaAnalisisDTO prueba);
    
    List<PruebaAnalisis> listarPruebasAnalisis(int idLaboratorio)throws PersistenciaException;
    
    PruebaAnalisis buscarPorId(int idPrueba,int idLaboratorio)throws PersistenciaException;
}

