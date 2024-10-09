/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rutinas;

import javax.swing.JOptionPane;

/**
 *
 * @author soule
 */
public class MensajesPantalla {
    
    public static void TareaconExito(String TipoTarea){
                JOptionPane.showMessageDialog(null, TipoTarea, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
        public static void TareaconFallo(String TipoTarea){
                JOptionPane.showMessageDialog(null, TipoTarea, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
