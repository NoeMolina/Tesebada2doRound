/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bddback;

/**
 *
 * @author soule
 */
import Rutinas.MensajesPantalla;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class DistribuidorDeConsultas {

    private String url;
    private String usuario;
    private String contraseña;
    private String consultaCorregida;
    private List<Fragmentos> fragmentos;

    public DistribuidorDeConsultas() {
    }

    // Método para analizar la consulta y determinar los fragmentos que deben crearse
    public void obtenerFragmentos(String consulta) {
        String Zona;
        String Estado;
        String Mayconsulta = consulta.toUpperCase();
        consultaCorregida = Mayconsulta;

        if (Mayconsulta.contains("SELECT")) {
            if (Mayconsulta.contains("WHERE ZONA") || Mayconsulta.contains("WHERE ESTADO")) {
                // Encontrar la posición del "WHERE"
                int inicioWhere = consulta.toLowerCase().indexOf("where");

                // Encontrar la posición del paréntesis de cierre ')'
                int cierreParentesis = consulta.indexOf(')', inicioWhere);
                if (inicioWhere != -1 && cierreParentesis != -1) {
                    consultaCorregida = consulta.substring(0, inicioWhere) + consulta.substring(cierreParentesis + 1);
                }
                //consulta select con zona
                if (Mayconsulta.contains("ZONA NOT IN")) {
                    int inicio = consulta.indexOf('(');
                    int fin = consulta.indexOf(')', inicio);

                    if (inicio != -1 && fin != -1) {
                        Zona = consulta.substring(inicio + 1, fin);
                        fragmentos = ConfigBDD.obtenerFragmentosZona(Zona, true);

                    } else {
                        MensajesPantalla.TareaconFallo("Error de sintaxis");
                    }
                }
                if (Mayconsulta.contains("ZONA IN")) {
                    int inicio = consulta.indexOf('(');
                    int fin = consulta.indexOf(')', inicio);

                    if (inicio != -1 && fin != -1) {
                        Zona = consulta.substring(inicio + 1, fin);
                        fragmentos = ConfigBDD.obtenerFragmentosZona(Zona, false);
                    } else {
                        MensajesPantalla.TareaconFallo("Error de sintaxis");
                    }
                }
                if (Mayconsulta.contains("ESTADO =")) {
                    int inicio = consulta.indexOf("'");
                    int fin = consulta.indexOf("'", inicio + 1);
                    Estado = consulta.substring(inicio + 1, fin);
                    System.out.println(Estado);

                    fragmentos = ConfigBDD.obtenerFragmentosEstado(Estado);
                    System.out.println(consultaCorregida);
                } else {
                    MensajesPantalla.TareaconFallo("Error de sintaxis");
                }
            } else {
                fragmentos = ConfigBDD.obtenerFragmentos();
            }
        }
        if (Mayconsulta.contains("INSERT")) {

            // Separar los valores usando la cadena 'VALUES'
            String[] parts = Mayconsulta.split("VALUES");
            // Obtener la parte que contiene los valores
            String valuesPart = parts[1].trim();

            // Eliminar los paréntesis y dividir por comas
            String[] values = valuesPart.replaceAll("[()]", "").split(",");

            // Obtener el estado, que está en la posición 2 (índice 2)
            String estado = values[2].trim().replace("'", ""); // Quitar espacios y comillas
            System.out.println("Estado: " + estado);

            fragmentos = ConfigBDD.obtenerFragmentosEstado(estado);
            ejecutarTran(consulta);
        }
        //ejecuta una transaccion
        if (Mayconsulta.contains("UPDATE") || Mayconsulta.contains("DELETE")) {
            if (Mayconsulta.contains("WHERE ZONA") || Mayconsulta.contains("WHERE ESTADO")) {
                // Encontrar la posición del "WHERE"
                int inicioWhere = consulta.toLowerCase().indexOf("where");

                // Encontrar la posición del paréntesis de cierre ')'
                int cierreParentesis = consulta.indexOf(')', inicioWhere);
                if (inicioWhere != -1 && cierreParentesis != -1) {
                    consultaCorregida = consulta.substring(0, inicioWhere) + consulta.substring(cierreParentesis + 1);
                }
                //consulta select con zona
                if (Mayconsulta.contains("ZONA NOT IN")) {
                    int inicio = consulta.indexOf('(');
                    int fin = consulta.indexOf(')', inicio);

                    if (inicio != -1 && fin != -1) {
                        Zona = consulta.substring(inicio + 1, fin);
                        fragmentos = ConfigBDD.obtenerFragmentosZona(Zona, true);

                    } else {
                        MensajesPantalla.TareaconFallo("Error de sintaxis");
                    }
                }
                if (Mayconsulta.contains("ZONA IN")) {
                    int inicio = consulta.indexOf('(');
                    int fin = consulta.indexOf(')', inicio);

                    if (inicio != -1 && fin != -1) {
                        Zona = consulta.substring(inicio + 1, fin);
                        fragmentos = ConfigBDD.obtenerFragmentosZona(Zona, false);
                    } else {
                        MensajesPantalla.TareaconFallo("Error de sintaxis");
                    }
                }
                if (Mayconsulta.contains("ESTADO =")) {
                    int inicio = consulta.indexOf("'");
                    int fin = consulta.indexOf("'", inicio + 1);
                    Estado = consulta.substring(inicio + 1, fin);
                    System.out.println(Estado);

                    fragmentos = ConfigBDD.obtenerFragmentosEstado(Estado);
                    System.out.println(consultaCorregida);

                } else {
                    MensajesPantalla.TareaconFallo("Error de sintaxis");
                }
            } else {
                fragmentos = ConfigBDD.obtenerFragmentos();
            }
            ejecutarTran(consultaCorregida);
        }
    }

    // Método para ejecutar la consulta en cada fragmento obtenido
    public DefaultTableModel ejecutarSelect(DefaultTableModel tableModel) {
        for (Fragmentos fragmento : fragmentos) {
            // Aquí pasamos el modelo de tabla y la consulta a cada fragmento
            tableModel = fragmento.select(consultaCorregida, tableModel);
        }
        return tableModel;
    }

    public boolean ejecutarTran(String transaccion) {
        boolean ready = false;
        for (Fragmentos fragmento : fragmentos) {
            if (fragmento.ejecutaTransaccion(transaccion)) {
                ready = true;
            } else {
                ready = false;
                break;
            }
        }
        if (ready) {
            commit();
        } else {
            rollback();
        }
        return ready;
    }

    private void rollback() {
        System.out.println("bddback.DistribuidorDeConsultas.rollback() fue rollback");
        for (Fragmentos fragmento : fragmentos) {
            fragmento.rollback();
        }
    }

    private void commit() {
        System.out.println("bddback.DistribuidorDeConsultas.commit()");
        for (Fragmentos fragmento : fragmentos) {
            fragmento.commit();
        }
    }

    public void imprimirFragmentos() {
        for (Fragmentos fragmento : fragmentos) {
            System.out.println(fragmento.toString());
        }
    }
}
