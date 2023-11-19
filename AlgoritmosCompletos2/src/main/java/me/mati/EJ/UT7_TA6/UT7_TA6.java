package me.mati.EJ.UT7_TA6;


import jdk.jshell.execution.Util;
import me.mati.GrafosDirigidos.TArista;
import me.mati.GrafosDirigidos.TCaminos;
import me.mati.GrafosDirigidos.TGrafoDirigido;
import me.mati.GrafosDirigidos.TVertice;
import me.mati.Util.ManejadorArchivosGenerico;
import me.mati.Util.UtilGrafos;

public class UT7_TA6 {
    public static void main(String[] args) {

        TGrafoDirigido grafo = UtilGrafos.cargarGrafo("src/main/java/me/mati/EJ/UT7_TA6/tareas.txt", "src/main/java/me/mati/EJ/UT7_TA6/precedencias.txt", false, TGrafoDirigido.class);

        System.out.println("Grafo cargado desde archivos");




    }
}
