package GrafosDirigidos;

public class Main {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src/main/java/org/example/aeropuertos_2.txt","src/main/java/org/example/conexiones_2.txt",
                false, TGrafoDirigido.class);

        TCaminos caminos = gd.todosLosCaminos("Asuncion", "Montevideo");
        caminos.imprimirCaminosConsola();

    }
}

