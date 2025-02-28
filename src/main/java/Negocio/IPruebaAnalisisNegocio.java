/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

/**
 *
 * @author oribi
 */
import DTOS.PruebaAnalisisDTO;
import java.util.List;

public interface IPruebaAnalisisNegocio {
    void registrarPrueba(PruebaAnalisisDTO pruebaDTO);  // Métodos relacionados con las pruebas
    List<PruebaAnalisisDTO> listarPruebas();  // Listar pruebas
    PruebaAnalisisDTO obtenerPrueba(int id);  // Obtener prueba por ID
}
