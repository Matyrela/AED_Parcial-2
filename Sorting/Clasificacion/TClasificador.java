package Clasificacion;


public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;

    /*
     * Punto de entrada al clasificador
     *
     * @param metodoClasificacion
     * @param orden
     * @param tamanioVector
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION:
                return ordenarPorInsercion(datosParaClasificar);
            case METODO_CLASIFICACION_SHELL:
                return ordenarPorShell(datosParaClasificar);
            case METODO_CLASIFICACION_BURBUJA:
                return ordenarPorBurbuja(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORT:
                return ordenarPorQuickSort(datosParaClasificar);
            case METODO_CLASIFICACION_HEAPSORT:
                return ordenarPorHeapSort(datosParaClasificar);
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{10, 3, 1};

        for (int posIncrementoActual = 1; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                        }
                        j -= inc; // Se decrementa el valor de j en cada iteración
                    }
                }
            }
        }
        return datosParaClasificar;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int j = i - 1;
                while ((j >= 0) && (datosParaClasificar[j + 1] > datosParaClasificar[j])) {
                    intercambiar(datosParaClasificar, j, j + 1);
                    j--;
                }
            }
            return datosParaClasificar;
        }
        return null;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private void quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            int pivote = entrada[posicionPivote];
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }

                while ((pivote <= entrada[derecha]) && (derecha > i)) {
                    derecha--;
                }

                if (izquierda < derecha) {
                    intercambiar(entrada, izquierda, derecha);
                    izquierda++;
                    derecha--;
                }
            }

            if (i < derecha) {
                quicksort(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksort(entrada, izquierda, j);
            }
        }
    }

    private int encuentraPivote(int izq, int der, int[] entry) {
        if (entry.length > 1) {
            if (entry[izq] > entry[izq + 1]) {
                return entry[izq];
            } else {
                return entry[izq + 1];
            }
        } else {
            return -1;
        }
    }

    protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 1) / 2 - 1; i >= 0; i--) {
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }
        for (int i = datosParaClasificar.length - 1; i >= 1; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

    private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        int r = primero;
        while (r < ultimo / 2) {
            int hijoIzquierdo = 2 * r + 1;
            int hijoDerecho = 2 * r + 2;
            int posicionIntercambio;

            if (hijoDerecho <= ultimo && datosParaClasificar[hijoDerecho] > datosParaClasificar[hijoIzquierdo]) {
                posicionIntercambio = hijoDerecho;
            } else {
                posicionIntercambio = hijoIzquierdo;
            }

            if (datosParaClasificar[r] < datosParaClasificar[posicionIntercambio]) {
                intercambiar(datosParaClasificar, r, posicionIntercambio);
                r = posicionIntercambio;
            } else {
                break;
            }
        }
    }

    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] vectorAleatorio = gdg.generarDatosAleatorios();
        int[] vectorAscendente = gdg.generarDatosAscendentes();
        int[] vectorDescendente = gdg.generarDatosDescendentes();

        System.out.println("Heap --------------------------------");

        int[] ord = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_HEAPSORT);
        for(int i : ord){
            System.out.println(i);
        }

        System.out.println("Burbuja --------------------------------");

        int[] ord2 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_BURBUJA);
        for(int i : ord){
            System.out.println(i);
        }

        System.out.println("Insercion --------------------------------");

        int[] ord3 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_INSERCION);
        for(int i : ord){
            System.out.println(i);
        }

        System.out.println("Shell --------------------------------");

        int[] ord4 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_SHELL);
        for(int i : ord){
            System.out.println(i);
        }

        System.out.println("Quick --------------------------------");

        int[] ord5 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_QUICKSORT);
        for(int i : ord){
            System.out.println(i);
        }

        /*
        long tiempo = System.nanoTime();

        int[] resAleatorio = clasif.clasificar(vectorAleatorio,
                METODO_CLASIFICACION_HEAPSORT);
        System.out.println(System.nanoTime()-tiempo);
        tiempo = System.nanoTime();
        
        int[] resAscendente = clasif.clasificar(vectorAscendente,
                METODO_CLASIFICACION_HEAPSORT);
        System.out.println(System.nanoTime()-tiempo);
        tiempo = System.nanoTime();
        int[] resDescendente = clasif.clasificar(vectorDescendente,
                METODO_CLASIFICACION_HEAPSORT);
        System.out.println(System.nanoTime()-tiempo);
        tiempo = System.nanoTime();

         */
    }
}
