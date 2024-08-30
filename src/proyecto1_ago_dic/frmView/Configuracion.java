/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1_ago_dic.frmView;

import BDMG.ConexionBD;
import javax.swing.JOptionPane;

/**
 *
 * @author soule
 */
public class Configuracion extends javax.swing.JDialog {

    /**
     * Creates new form Configuracion
     */
    public Configuracion(javax.swing.JFrame Padre) {
        super(Padre, "Configuración", true);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            JpLogin =(javax.swing.JPanel)java.beans.Beans.instantiate(getClass().getClassLoader(), "proyecto1_ago_dic/frmView.Configuracion_JpLogin");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        txtIP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDBName = new javax.swing.JTextField();
        lbDBName = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lbUsuario = new javax.swing.JLabel();
        lbPasword = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jpPaswordBD = new javax.swing.JPasswordField();
        btnSave = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnTest = new javax.swing.JButton();

        txtIP.setText("localhost");

        jLabel1.setText("Ip base de datos");

        lbDBName.setText("Nombre de la BD");

        lbUsuario.setText("Usuario");

        lbPasword.setText("Contraseña");

        jCheckBox1.setText("Windows certification");

        btnSave.setText("Guardar");

        btnCancelar.setText("Cancelar");

        btnTest.setText("Test");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpLoginLayout = new javax.swing.GroupLayout(JpLogin);
        JpLogin.setLayout(JpLoginLayout);
        JpLoginLayout.setHorizontalGroup(
            JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(JpLoginLayout.createSequentialGroup()
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(JpLoginLayout.createSequentialGroup()
                            .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbDBName)
                                .addComponent(lbUsuario)
                                .addComponent(jLabel1)
                                .addComponent(lbPasword))
                            .addGap(18, 18, 18)
                            .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtIP)
                                .addComponent(txtDBName, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addComponent(jpPaswordBD))))
                    .addComponent(btnTest))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        JpLoginLayout.setVerticalGroup(
            JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDBName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPasword)
                    .addComponent(jpPaswordBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addGroup(JpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTest, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        ConexionBD conexion = new ConexionBD(txtIP.getText(), txtDBName.getText(), txtUser.getText(),jpPaswordBD.getText(), false);
        
        if(conexion.MakeConexion()){
            JOptionPane.showMessageDialog(this, "Conexion existosa");
        } else {
               JOptionPane.showMessageDialog(this, "Conexion fallida");
        }
    }//GEN-LAST:event_btnTestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpLogin;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTest;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jpPaswordBD;
    private javax.swing.JLabel lbDBName;
    private javax.swing.JLabel lbPasword;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JTextField txtDBName;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
