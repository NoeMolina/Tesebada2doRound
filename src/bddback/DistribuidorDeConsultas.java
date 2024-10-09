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

        if (Mayconsulta.contains("SELECT")) {
            if (Mayconsulta.contains("WHERE")) {
                // Encontrar la posición del "WHERE"
                int inicioWhere = consulta.toLowerCase().indexOf("where");

                // Encontrar la posición del paréntesis de cierre ')'
                int cierreParentesis = consulta.indexOf(')', inicioWhere);
                if (inicioWhere != -1 && cierreParentesis != -1){
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

                    String regex = "ESTADO\\s*=\\s*([A-Za-z]+)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(consulta);

                    if (matcher.find()) {
                        // matcher.group(1) devuelve la primera coincidencia capturada por los paréntesis en la regex
                        Estado = matcher.group(1);
                        System.out.println(Estado);
                        fragmentos = ConfigBDD.obtenerFragmentosEstado(Estado);

                    } else {
                        MensajesPantalla.TareaconFallo("Error de query");
                    }
                }
            } else {
                fragmentos = ConfigBDD.obtenerFragmentos();
            }
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

    public void imprimirFragmentos() {
        for (Fragmentos fragmento : fragmentos) {
            System.out.println(fragmento.toString());
        }
    }
}
