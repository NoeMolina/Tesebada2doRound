/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bdd.front;

import bddback.ConfigBDD;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author soule
 */
public class FrmConfigTabla extends javax.swing.JFrame {

    /**
     * Creates new form frmConfiguracion
     */
    public FrmConfigTabla() {
        initComponents();
        setBoxTipodato(tblAtributos, tblAtributos.getColumnModel().getColumn(1));
        ConfigBDD.llenarComboTabla(cbxTabla);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNomTabla = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAtributos = new javax.swing.JTable();
        btnCLOSE = new javax.swing.JButton();
        btnSAVE = new javax.swing.JButton();
        cbxTabla = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNomTabla.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNomTabla.setText("Nom.Tabla distribuida");

        tblAtributos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblAtributos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Atributos", "Tipo Dato"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAtributos);

        btnCLOSE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCLOSE.setText("CERRAR");
        btnCLOSE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCLOSEActionPerformed(evt);
            }
        });

        btnSAVE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSAVE.setText("GUARDAR");
        btnSAVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSAVEActionPerformed(evt);
            }
        });

        cbxTabla.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNomTabla)
                        .addGap(18, 18, 18)
                        .addComponent(cbxTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnSAVE, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomTabla)
                    .addComponent(cbxTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSAVE, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCLOSEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCLOSEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCLOSEActionPerformed

    private void btnSAVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSAVEActionPerformed
        // TODO add your handling code here:
        if (ConfigBDD.connectBDD()) {
            System.out.println("bdd.front.frmConfiguracion.btnSAVEActionPerformed()");
        }
    }//GEN-LAST:event_btnSAVEActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCLOSE;
    private javax.swing.JButton btnSAVE;
    private javax.swing.JComboBox<String> cbxTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNomTabla;
    private javax.swing.JTable tblAtributos;
    // End of variables declaration//GEN-END:variables
    public void setBoxTipodato(JTable tblAtributos, TableColumn columna){
        JComboBox c = new JComboBox();
        c.addItem("INT");
        c.addItem("MONEY");
        c.addItem("VARCHAR");
        
        columna.setCellEditor(new DefaultCellEditor(c));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Seleccionar Dato");
        columna.setCellRenderer(renderer);
    }
}
