//MENOR A MAYOR
package me.mati.Sorting;

public class HeapSortInverso<T extends Comparable<T>> {
    private void intercambiar(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //O(N * Log n) -----------------------------------------------------------------------------------------------------
    private void armaHeap(T[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int actual = primero;
            while (actual <= ultimo / 2) {
                if (ultimo == 2 * actual) {
                    if (datosParaClasificar[actual].compareTo(datosParaClasificar[actual * 2]) < 0) { // Cambio la condición
                        intercambiar(datosParaClasificar, actual, 2 * actual);
                    }
                    actual = ultimo;
                } else {
                    int posicionIntercambio = 0;
                    if (datosParaClasificar[2 * actual].compareTo(datosParaClasificar[2 * actual + 1]) < 0) { // Cambio la condición
                        posicionIntercambio = 2 * actual + 1;
                    } else {
                        posicionIntercambio = 2 * actual;
                    }
                    if (datosParaClasificar[actual].compareTo(datosParaClasificar[posicionIntercambio]) < 0) { // Cambio la condición
                        intercambiar(datosParaClasificar, actual, posicionIntercambio);
                        actual = posicionIntercambio;
                    } else {
                        actual = ultimo;
                    }
                }
            }
        }
    }

    public T[] ordenarPorHeapSort(T[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) {
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }
        for (int i = datosParaClasificar.length - 1; i > 0; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

}
