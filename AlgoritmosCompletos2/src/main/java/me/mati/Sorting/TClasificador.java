package me.mati.Sorting;

import java.util.Random;

public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
	public static final int METODO_CLASIFICACION_QUICKSORT = 4;
	public static final int METODO_CLASIFICACION_SELECTION = 5;
	public static final int METODO_CLASIFICACION_HEAP = 6;
	public static final int METODO_CLASIFICACION_HEAPINVERSO = 7;

	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
			case METODO_CLASIFICACION_INSERCION:
				return ordenarPorInsercion(datosParaClasificar);
			case METODO_CLASIFICACION_SHELL:
				return ordenarPorShell(datosParaClasificar);
			case METODO_CLASIFICACION_BURBUJA:
				return ordenarPorBurbuja(datosParaClasificar);
			case METODO_CLASIFICACION_QUICKSORT:
				ordenarPorQuickSort(datosParaClasificar, 0, datosParaClasificar.length - 1);
				return datosParaClasificar;
			case METODO_CLASIFICACION_SELECTION:
				return selectionSort(datosParaClasificar);
			case METODO_CLASIFICACION_HEAP:
				/**
				 * IMPORTANTE:
				 * la clase HeapSort pide un tipo T, entonces hay que convertir el array de int a Integer ya que
				 * Integer es un objeto y int es un tipo primitivo y el tipo primitivo no es un objeto.
				 *
				 * Esto se hace en las siguientes lineas de codigo:
				 * el problema es que convertir un array de int a Integer es un O(n) + O(n) = O(2n) = O(n)
				 *
				 * No se puede usar Integer directamente porque el metodo donde esta esto escrito devuelve un int[]
				 * el dia del parcial se puede usar Integer[] directamente y no usar esta clase en absoluto.
				 * esto no es mas que un ejemplo de como usar la clase HeapSort y un parche para que funcione con int[]
				 * - Matias V.
				 */
				HeapSort<Integer> heapSort = new HeapSort<>();
				Integer[] aux = new Integer[datosParaClasificar.length];
				for (int i = 0; i < datosParaClasificar.length; i++) {
					aux[i] = Integer.valueOf(datosParaClasificar[i]);
				}

				aux = heapSort.ordenarPorHeapSort(aux);

				int[] resultado = new int[aux.length];
				for (int i = 0; i < aux.length; i++) {
					resultado[i] = aux[i].intValue();
				}

				return resultado;
			case METODO_CLASIFICACION_HEAPINVERSO:
				HeapSortInverso<Integer> heapSortInv = new HeapSortInverso<>();
				Integer[] aux1 = new Integer[datosParaClasificar.length];
				for (int i = 0; i < datosParaClasificar.length; i++) {
					aux1[i] = Integer.valueOf(datosParaClasificar[i]);
				}

				aux1 = heapSortInv.ordenarPorHeapSort(aux1);

				int[] resultado1 = new int[aux1.length];
				for (int i = 0; i < aux1.length; i++) {
					resultado1[i] = aux1[i].intValue();
				}
				return resultado1;
			default:
				System.err.println("Este codigo no deberia haberse ejecutado nunca. Revisa el metodo , en TClasificador.java");
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

	//O(n2) ------------------------------------------------------------------------------------------------------------
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



	//Metodo Main ------------------------------------------------------------------------------------------------------

	public static void main(String args[]) {
		TClasificador clasif = new TClasificador();
		GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
		int[] vectorAleatorio = gdg.generarDatosAleatorios();
		int[] vectorAscendente = gdg.generarDatosAscendentes();
		int[] vectorDescendente = gdg.generarDatosDescendentes();

		System.out.println("Burbuja --------------------------------");

		int[] ord1 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_BURBUJA);
		for (int i : ord1) {
			System.out.print(i + ", ");
		}
		System.out.println();

		System.out.println("Insercion --------------------------------");

		int[] ord2 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_INSERCION);
		for (int i : ord2) {
			System.out.print(i + ", ");
		}
		System.out.println();

		System.out.println("Shell --------------------------------");

		int[] ord3 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_SHELL);
		for (int i : ord3) {
			System.out.print(i);
		}
		System.out.println();

		System.out.println("Quick --------------------------------");

		int[] ord4 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_QUICKSORT);
		for (int i : ord4) {
			System.out.print(i);
		}
		System.out.println();

		System.out.println("Selection --------------------------------");

		int[] ord5 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_SELECTION);
		for (int i : ord5) {
			System.out.print(i);
		}
		System.out.println();

		System.out.println("HEAP --------------------------------");

		int[] ord6 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_HEAP);
		for (int i : ord6) {
			System.out.print(i);
		}
		System.out.println();

		System.out.println("HEAP INVERSO --------------------------------");

		int[] ord7 = clasif.clasificar(vectorAleatorio, METODO_CLASIFICACION_HEAPINVERSO);
		for (int i : ord7) {
			System.out.print(i);
		}
		System.out.println();
	}
}
