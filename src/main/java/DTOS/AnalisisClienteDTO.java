/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class AnalisisClienteDTO {
    private int ididAnalisisCliente;
    private int folio;
    private LocalDateTime fechaYhoraCaptura;
    private int idCliente;

    //Constructor Para Guardar analisisCliente a la BD
    public AnalisisClienteDTO(int folio, LocalDateTime fechaYhoraCaptura, int idCliente) {
        this.folio = folio;
        this.fechaYhoraCaptura = fechaYhoraCaptura;
        this.idCliente = idCliente;
    }

    public AnalisisClienteDTO(int ididAnalisisCliente, int folio, LocalDateTime fechaYhoraCaptura, int idCliente) {
        this.ididAnalisisCliente = ididAnalisisCliente;
        this.folio = folio;
        this.fechaYhoraCaptura = fechaYhoraCaptura;
        this.idCliente = idCliente;
    }

    public int getIdidAnalisisCliente() {
        return ididAnalisisCliente;
    }

    public void setIdidAnalisisCliente(int ididAnalisisCliente) {
        this.ididAnalisisCliente = ididAnalisisCliente;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public LocalDateTime getFechaYhoraCaptura() {
        return fechaYhoraCaptura;
    }

    public void setFechaYhoraCaptura(LocalDateTime fechaYhoraCaptura) {
        this.fechaYhoraCaptura = fechaYhoraCaptura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
