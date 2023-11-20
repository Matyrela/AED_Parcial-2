package me.mati;

import jdk.jshell.execution.Util;
import me.mati.GrafosDirigidos.*;
import me.mati.GrafosNoDirigidos.TGrafoNoDirigido;
import me.mati.Util.ManejadorArchivosGenerico;
import me.mati.Util.UtilGrafos;

import java.util.HashMap;
import java.util.LinkedList;

public class main {
    public static void main(String[] args) {
        TGrafoNoDirigido grafo = UtilGrafos.cargarGrafo("src/main/java/me/mati/EJ/UT7_TA6/tareas.txt", "src/main/java/me/mati/EJ/UT7_TA6/precedencias.txt", false, TGrafoNoDirigido.class);


        UtilGrafos.ImprimirTodosLosCostosConsola(grafo.todosLosCaminosConCosto("A", "E"));
        UtilGrafos.imprimirMatrizMejorado(grafo.floyd(), grafo.getVertices(), "gran tula");
    }
}
