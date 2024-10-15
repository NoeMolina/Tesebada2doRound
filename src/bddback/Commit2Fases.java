/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bddback;

import Rutinas.MensajesPantalla;
import java.util.List;

/**
 *
 * @author soule
 */
public class Commit2Fases extends Thread {

    String transaccion;
    List<Fragmentos> fragmentos;

    public Commit2Fases(String transaccion, List<Fragmentos> fragmentos) {
        this.fragmentos = fragmentos;
        this.transaccion = transaccion;
    }

    @Override
    public void run() {
        for (Fragmentos fragmento : fragmentos) {
            fragmento.setQuery(transaccion);
            fragmento.start();
        }
        try {
            // Duerme el hilo por 10000 milisegundos (10 segundos)
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido.");
        }
        for (Fragmentos fragmento : fragmentos) {
            if (fragmento.queryStatus() == 2 || fragmento.queryStatus() == 0 ) {
                MensajesPantalla.TareaconFallo("Error al completar la transaccion verifique la consulta");
                hazRollback();
                return;
            }
        }
        hazCommit();
        MensajesPantalla.TareaconExito("Tranzaccion completada con exito");

    }

    private void hazRollback() {
        for (Fragmentos fragmento : fragmentos) {
            fragmento.rollback();
        }
    }

    private void hazCommit() {
        for (Fragmentos fragmento : fragmentos) {
            fragmento.commit();
        }
    }

}
