package org.example;

import java.util.ArrayList;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] actores = ManejadorArchivosGenerico.leerArchivo("src/main/java/org/example/actores.csv", false);
        String[] peliculas = ManejadorArchivosGenerico.leerArchivo("src/main/java/org/example/en_pelicula.csv", false);

        ArrayList<TVertice> vertices = new ArrayList<>();
        ArrayList<TArista> aristas = new ArrayList<>();
        for (String x : actores){
            vertices.add(new TVertice<>(x));
        }
        for (String x : peliculas){
            String[] y = x.split(",");
            aristas.add(new TArista(y[0],y[1],Double.parseDouble(y[2])));
        }
        // cargar grafo con actores y relaciones
        TGrafoNoDirigido grafoNoDirigido = new TGrafoNoDirigido(vertices,aristas);

        System.out.println(grafoNoDirigido.numBacon("John_Goodman"));
        // invocar a numBacon como indica la letra y mostrar en consola el resultado
        
        

    }

}
