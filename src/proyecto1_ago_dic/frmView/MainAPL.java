/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1_ago_dic.frmView;

/**
 *
 * @author soule
 */
public class MainAPL extends javax.swing.JFrame {

    /**
     * Creates new form MainAPL
     */
    public MainAPL() {
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

        jMenuBar1 = new javax.swing.JMenuBar();
        MenuItemProcesar = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuConfig = new javax.swing.JMenu();
        MenuItemBD = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MenuItemProcesar.setText("Opciones");
        MenuItemProcesar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                MenuItemProcesarMouseDragged(evt);
            }
        });

        jMenuItem1.setText("jMenuItem1");
        MenuItemProcesar.add(jMenuItem1);

        jMenuBar1.add(MenuItemProcesar);

        jMenuConfig.setText("Configuracion");
        jMenuConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuConfigMouseEntered(evt);
            }
        });

        MenuItemBD.setText("Base de Datos");
        MenuItemBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemBDActionPerformed(evt);
            }
        });
        jMenuConfig.add(MenuItemBD);

        jMenuBar1.add(jMenuConfig);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItemBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemBDActionPerformed
        // TODO add your handling code here:
        System.out.println("WOlaaaaa");
        
        Configuracion config = new Configuracion(this);
        config.setVisible(true);
    }//GEN-LAST:event_MenuItemBDActionPerformed

    private void jMenuConfigMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuConfigMouseEntered
        // TODO add your handling code here:
        jMenuConfig.doClick();
    }//GEN-LAST:event_jMenuConfigMouseEntered

    private void MenuItemProcesarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuItemProcesarMouseDragged
        // TODO add your handling code here:
        MenuItemProcesar.doClick();
    }//GEN-LAST:event_MenuItemProcesarMouseDragged

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemBD;
    private javax.swing.JMenu MenuItemProcesar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuConfig;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
