/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import DTOS.PruebaAnalisisDTO;
import Negocio.CategoriasNegocio;
import Negocio.ICategoriasNegocio;
import Negocio.IParametrosEvaluacionNegocio;
import Negocio.IPruebaAnalisisNegocio;
import Negocio.ParametrosEvaluacionNegocio;
import Negocio.PruebaAnalisisNegocio;
import Persistencia.CategoriasDAO;
import Persistencia.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author oribi
 */
public class frmAdministrarPruebas extends javax.swing.JFrame {

    private IParametrosEvaluacionNegocio parametrosNegocio;
    private ICategoriasNegocio categoriasNegocio;
    private IPruebaAnalisisNegocio pruebaAnalisisNegocio;

    public frmAdministrarPruebas() {
        initComponents();
        parametrosNegocio = new ParametrosEvaluacionNegocio();
        categoriasNegocio = new CategoriasNegocio(new CategoriasDAO(new ConexionBD()));  
        pruebaAnalisisNegocio = new PruebaAnalisisNegocio((Connection) new ConexionBD());  // Inicia la capa de negocio de pruebas
        cargarCategorias();
    }

    private void cargarCategorias() {
        try {

            List<String> categorias = categoriasNegocio.obtenerCategorias();

            ComboBoxCategoria.removeAllItems();

            for (String categoria : categorias) {
                ComboBoxCategoria.addItem(categoria);
            }

        } catch (SQLException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las categorías: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        RegistrarPrueba = new javax.swing.JLabel();
        Categoria = new javax.swing.JLabel();
        ComboBoxCategoria = new javax.swing.JComboBox<>();
        NombrePrueba = new javax.swing.JLabel();
        texfieldNombrePrueba = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnAgregarParametros = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        RegistrarPrueba.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        RegistrarPrueba.setText("Registrar Prueba ");

        Categoria.setText("Categoria:");

        ComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCategoriaActionPerformed(evt);
            }
        });

        NombrePrueba.setText("Nombre de la prueba");

        texfieldNombrePrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texfieldNombrePruebaActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAgregarParametros.setText("Agregar parametros");
        btnAgregarParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarParametrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(137, 137, 137)
                            .addComponent(RegistrarPrueba))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(Categoria)
                            .addGap(32, 32, 32)
                            .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(NombrePrueba)
                            .addGap(18, 18, 18)
                            .addComponent(texfieldNombrePrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RegistrarPrueba)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Categoria)
                    .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texfieldNombrePrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombrePrueba))
                .addGap(32, 32, 32)
                .addComponent(btnAgregarParametros, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnGuardar))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCategoriaActionPerformed

    }//GEN-LAST:event_ComboBoxCategoriaActionPerformed

    private void texfieldNombrePruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texfieldNombrePruebaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texfieldNombrePruebaActionPerformed

    private void btnAgregarParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarParametrosActionPerformed

    }//GEN-LAST:event_btnAgregarParametrosActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    try {
            // Obtener idCategoria desde el ComboBox
             categoriaSeleccionada = (String) ComboBoxCategoria.getSelectedItem();
            int idCategoria = categoriasNegocio.obtenerCategoriaPorId(categoriaSeleccionada);

            // Crear y registrar la prueba de análisis
            PruebaAnalisisDTO pruebaDTO = new PruebaAnalisisDTO();
            pruebaDTO.setNombre(txtNombrePrueba.getText());  // Asumimos que tienes un campo de texto para nombre de prueba
            pruebaDTO.setIdCategoria(idCategoria);
            

            pruebaAnalisisNegocio.registrarPrueba(pruebaDTO);
            
            // Guardar parámetros asociados a la prueba
            for (ParametroDTO parametro : listaParametros) {
                parametrosNegocio.registrarParametro(parametro.getNombre(), parametro.getRango(), pruebaDTO.getNombre());
            }

            JOptionPane.showMessageDialog(this, "Prueba y parámetros guardados correctamente.");
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la prueba: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmAdministrarPruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAdministrarPruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAdministrarPruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAdministrarPruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAdministrarPruebas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Categoria;
    private javax.swing.JComboBox<String> ComboBoxCategoria;
    private javax.swing.JLabel NombrePrueba;
    private javax.swing.JLabel RegistrarPrueba;
    private javax.swing.JButton btnAgregarParametros;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField texfieldNombrePrueba;
    // End of variables declaration//GEN-END:variables
}
