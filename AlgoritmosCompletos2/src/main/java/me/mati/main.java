package me.mati;

import me.mati.GrafosDirigidos.*;
import me.mati.Util.ManejadorArchivosGenerico;
import me.mati.Util.UtilGrafos;

import java.util.LinkedList;

public class main {
    public static void main(String[] args) {
        TGrafoDirigido grafo = UtilGrafos.cargarGrafo("src/main/java/me/mati/EJ/UT7_TA6/tareas.txt", "src/main/java/me/mati/EJ/UT7_TA6/precedencias.txt", false, TGrafoDirigido.class);



    }
}
