package me.mati.EJ.UT9_TA4_EJ2;

import me.mati.Sorting.GeneradorDatosGenericos;
import me.mati.Sorting.TClasificador;

import java.util.Arrays;

public class TA4 {
    public static int[] datosDes30000;
    public static int[] datosOrd30000;
    public static int[] datosAle30000;

    public static void generateData() {
        GeneradorDatosGenericos generador = new GeneradorDatosGenericos();
        datosDes30000 = generador.generarDatosDescendentes(30000);
        datosOrd30000 = generador.generarDatosAscendentes(30000);
        datosAle30000 = generador.generarDatosAleatorios(30000);
    }
    public static void main(String[] args) {
        TClasificador clasificador = new TClasificador();

        /**
         * Ejecutar los métodos de ordenación Burbuja, Quicksort, Selección Directa y Heapsort, para conjuntos de 300,
         * 3000 y 30000 elementos, para conjuntos ordenados ascendentemente, ordenados descendentemente y
         * aleatoriamente desordenados.
         * Medir los tiempos de ejecución en cada caso y registrar todos los valores obtenidos en una tabla.
         */

        generateData();

        System.out.println("Burbuja --------------------------------");

        long tiempoInicio = System.currentTimeMillis();
        int[] ord1 = clasificador.clasificar(datosOrd30000, TClasificador.METODO_CLASIFICACION_BURBUJA);
        long totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del ordenado: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord1));
        //System.out.println("Ordenado: " + Arrays.toString(ord1));

        tiempoInicio = System.currentTimeMillis();
        ord1 = clasificador.clasificar(datosDes30000, TClasificador.METODO_CLASIFICACION_BURBUJA);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del desordenado: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord1));
        //System.out.println("Desordenado: " + Arrays.toString(ord1));

        tiempoInicio = System.currentTimeMillis();
        ord1 = clasificador.clasificar(datosAle30000, TClasificador.METODO_CLASIFICACION_BURBUJA);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del aleatorio: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord1));
        //System.out.println("Aleatorio: " + Arrays.toString(ord1));

        System.out.println("Insercion --------------------------------");
        generateData();

        tiempoInicio = System.currentTimeMillis();
        int[] ord2 = clasificador.clasificar(datosOrd30000, TClasificador.METODO_CLASIFICACION_INSERCION);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del ordenado: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord2));
        //System.out.println("Ordenado: " + Arrays.toString(ord2));

        tiempoInicio = System.currentTimeMillis();
        ord2 = clasificador.clasificar(datosDes30000, TClasificador.METODO_CLASIFICACION_INSERCION);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del desordenado: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord2));
        //System.out.println("Desordenado: " + Arrays.toString(ord2));

        tiempoInicio = System.currentTimeMillis();
        ord2 = clasificador.clasificar(datosAle30000, TClasificador.METODO_CLASIFICACION_INSERCION);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del aleatorio: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord2));
        //System.out.println("Aleatorio: " + Arrays.toString(ord2));

        System.out.println("Quick --------------------------------");
        generateData();

        tiempoInicio = System.currentTimeMillis();
        int[] ord4 = clasificador.clasificar(datosOrd30000, TClasificador.METODO_CLASIFICACION_QUICKSORT);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del ordenado: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord4));
        //System.out.println("Ordenado: " + Arrays.toString(ord4));

        tiempoInicio = System.currentTimeMillis();
        ord4 = clasificador.clasificar(datosDes30000, TClasificador.METODO_CLASIFICACION_QUICKSORT);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del desordenado: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord4));
        //System.out.println("Desordenado: " + Arrays.toString(ord4));

        tiempoInicio = System.currentTimeMillis();
        ord4 = clasificador.clasificar(datosAle30000, TClasificador.METODO_CLASIFICACION_QUICKSORT);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del aleatorio: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord4));
        //System.out.println("Aleatorio: " + Arrays.toString(ord4));

        System.out.println("Heap --------------------------------");
        generateData();

        tiempoInicio = System.currentTimeMillis();
        int[] ord6 = clasificador.clasificar(datosOrd30000, TClasificador.METODO_CLASIFICACION_HEAPINVERSO);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del ordenado: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord6));
        //System.out.println("Ordenado: " + Arrays.toString(ord6));

        tiempoInicio = System.currentTimeMillis();
        ord6 = clasificador.clasificar(datosDes30000, TClasificador.METODO_CLASIFICACION_HEAPINVERSO);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del desordenado: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord6));
        //System.out.println("Desordenado: " + Arrays.toString(ord6));

        tiempoInicio = System.currentTimeMillis();
        ord6 = clasificador.clasificar(datosAle30000, TClasificador.METODO_CLASIFICACION_HEAPINVERSO);
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del aleatorio: " + totalTiempo + "ms, Esta ordenado: " + estaOrdenado(ord6));
        //System.out.println("Aleatorio: " + Arrays.toString(ord6));

        System.out.println("--------------------------------");
    }

    public static boolean estaOrdenado(int[] datos) {
        for (int i = 0; i < datos.length - 1; i++) {
            if (datos[i] > datos[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
