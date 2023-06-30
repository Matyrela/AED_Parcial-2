package GrafosNoDirigidos;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        String[] vertices = ManejadorArchivosGenerico.leerArchivo("TA3-alumnos (3)/TA3-alumnos/src/verticesBEA.txt", false);
        String[] aristas = ManejadorArchivosGenerico.leerArchivo("TA3-alumnos (3)/TA3-alumnos/src/aristasBEA.txt", false);

        Collection<TVertice> v = new ArrayList<>();

        for(String vert : vertices){
            v.add(new TVertice<>(vert));
        }

        Collection<TArista> a = new ArrayList<>();

        for(String aris : aristas){
            String[] linea = aris.split(",");
            if(linea.length == 3){
                a.add(new TArista(linea[0], linea[1], Integer.parseInt(linea[2])));
            }
        }

        TGrafoNoDirigido gd = new TGrafoNoDirigido(v ,a);
    }
}
