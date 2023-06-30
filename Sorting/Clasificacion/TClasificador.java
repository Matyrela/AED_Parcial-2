import java.util.Arrays;
import java.util.Random;

public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
	public static final int METODO_CLASIFICACION_BINSORTTRIVIAL = 4;
	public static final int METODO_CLASIFICACION_QUICKSORT = 5;
	public static final int METODO_CLASIFICACION_SELECTION = 6;
	public static final int METODO_CLASIFICACION_HEAP = 7;

	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
			case METODO_CLASIFICACION_INSERCION:
				return ordenarPorInsercion(datosParaClasificar);
			case METODO_CLASIFICACION_SHELL:
				return ordenarPorShell(datosParaClasificar);
			case METODO_CLASIFICACION_BURBUJA:
				return ordenarPorBurbuja(datosParaClasificar);
			case METODO_CLASIFICACION_BINSORTTRIVIAL:
				return ordenarPorBinSortTrivial(datosParaClasificar);
			case METODO_CLASIFICACION_QUICKSORT:
				ordenarPorQuickSort(datosParaClasificar, 0, datosParaClasificar.length - 1);
				return datosParaClasificar;
			case METODO_CLASIFICACION_SELECTION:
				return selectionSort(datosParaClasificar);
			case METODO_CLASIFICACION_HEAP:
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
	//------------------------------------------------------------------------------------------------------------------
	//Algoritmos de Sorting --------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------

	//O(n2)
	protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			for (int i = 1; i < datosParaClasificar.length; i++) {
				int j = i - 1;
				while ((j >= 0) && (datosParaClasificar[j+1] < datosParaClasificar[j])) {
					intercambiar(datosParaClasificar, j, j + 1);
					j--;
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	//O(n2) ------------------------------------------------------------------------------------------------------------
	private int[] ordenarPorShell(int[] datosParaClasificar) {
		if(datosParaClasificar != null){
			int j, inc;
			int[] incrementos = new int[] { 3223, 358, 51, 10, 3, 1 };

			for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
				inc = incrementos[posIncrementoActual];
				if (inc < (datosParaClasificar.length / 2)) {
					for (int i = inc; i < datosParaClasificar.length; i++) {
						j = i - inc;
						while (j >= 0) {
							if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
								intercambiar(datosParaClasificar, j, j + inc);
							}
							j--;
						}
					}
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	//O(n2) ------------------------------------------------------------------------------------------------------------
	private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
		if(datosParaClasificar != null){
			int n = datosParaClasificar.length - 1;
			for (int i = 0; i <= n; i++) {
				for (int j = n; j >= (i + 1); j--) {
					if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
						intercambiar(datosParaClasificar, j - 1, j);
					}
				}
			}
		}

		return datosParaClasificar;
	}

	//O(?) -------------------------------------------------------------------------------------------------------------
	private int[] ordenarPorBinSortTrivial(int[] A){
		int[] B = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			B[A[i]] = A[i];
		}
		return B;
	}

	//O(n ** 1.26) -----------------------------------------------------------------------------------------------------
	private int[] ordenarPorQuickSort(int[] datosParaClasificar, int i, int j) {
		if (i < j) {
			int iPivote = obtenerClavePivote(i, j);
			int pivote = datosParaClasificar[iPivote];
			int k = particion(datosParaClasificar, i, j, pivote);
			ordenarPorQuickSort(datosParaClasificar, i, k - 1);
			ordenarPorQuickSort(datosParaClasificar, k, j);
		}
		return datosParaClasificar;
	}
	public int obtenerClavePivote(int i, int j){
		return new Random().nextInt(i, j);
	}
	private int particion(int[] arreglo, int izq, int der, int pivote) {
		while (izq <= der) {
			while (arreglo[izq] < pivote) {
				izq++;
			}
			while (arreglo[der] > pivote) {
				der--;
			}
			if (izq <= der) {
				intercambiar(arreglo, izq, der);
				izq++;
				der--;
			}
		}
		return izq;
	}

	//O(n**2) ----------------------------------------------------------------------------------------------------------

	public int[] selectionSort(int[] arr) {
		int i, j, minIndex, tmp;
		int n = arr.length;
		for (i = 0; i < n - 1; i++) {
			minIndex = i;
			for (j = i + 1; j < n; j++)
				if (arr[j] < arr[minIndex])
					minIndex = j;
			if (minIndex != i) {
				tmp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = tmp;
			}
		}
		return arr;
	}

	//O(N * Log n) -----------------------------------------------------------------------------------------------------

	private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
		if (primero < ultimo) {
			int actual = primero;
			while (actual <= ultimo / 2) {
				if (ultimo == 2 * actual) { //r tiene un hijo solo
					if (datosParaClasificar[actual] > datosParaClasificar[actual * 2]) {
						intercambiar(datosParaClasificar, actual, 2 * actual);
					}
					actual = ultimo;
				} else { //r tiene 2 hijos
					int posicionIntercambio = 0;
					if (datosParaClasificar[2 * actual] > datosParaClasificar[2 * actual + 1]) {
						posicionIntercambio = 2 * actual + 1;
					} else {
						posicionIntercambio = 2 * actual;
					}
					if (datosParaClasificar[actual] > datosParaClasificar[posicionIntercambio]) {
						intercambiar(datosParaClasificar, actual, posicionIntercambio);
						actual = posicionIntercambio;
					} else {
						actual = ultimo;
					}
				}
			}
		}
	}
	private int[] ordenarPorHeapSort(int[] datosParaClasificar) {
		for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
			armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
		}
		for (int i = datosParaClasificar.length - 1; i > 0; i--) {
			intercambiar(datosParaClasificar, 0, i);
			armaHeap(datosParaClasificar, 0, i - 1);
		}
		return datosParaClasificar;
	}



	//Metodo Main ------------------------------------------------------------------------------------------------------

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
        
        System.out.println("Selection --------------------------------");

        int[] ord6 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_SELECTION);
        for(int i : ord){
            System.out.println(i);
        }
        
        System.out.println("HEAP --------------------------------");

        int[] ord7 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_HEAP);
        for(int i : ord){
            System.out.println(i);
        }
}
