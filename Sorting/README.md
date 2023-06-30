# UT9 - Sort

## Por insercion - O(n^2)
En el mejor de los casos tiene un tiempo de ejecución de O(n) siempre y cuando el array este ordenado en la primera iteracion, pero en el peor de los casos el tiempo de ejecucion O(n^2).
El metodo por insercion funciona de tal forma que en el i-esimo recorrido se inserta el i-esimo elemento en el lugar correcto, resumiendo si se itera sobre el tercer elemento del un array ese tercer elemento va a ponerse en su lugar en los espacios anteriores.

Un metodo facilitador seria por ejemplo añadir como primer elemento de este array un numero menor a los demas numeros del array.

```java
private int[] ordenarPorInsercion(int[] datosParaClasificar) {
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
```

## Shell Sort - O(n^2)
El tiempo de ejecución promedio es O(n log n), siempre y cuando haya una buena elección de incrementos.
```java
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
```

## Bubble Sort - O(n^2)

Compara cada par de elementos adyacentes y los intercambia si el de la izquierda es mayor que el de la derecha y se hace varias veces hasta que todo este ordenado.
En el mejor de los casos tiene un tiempo de ejecución de O(n log n), pero en el peor de los casos tiene O(n^2).
```java
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
```


## Quick Sort - O(n^1.26)
Si se elije un buen pivote podría ser el más eficiente con un tiempo de ejecucion de O(n log n), pero en el peor de los casos con una mala elección de pivote tendría un tiempo de ejecución de O(n^1.26).
Elección del pivote:
```java
public int obtenerClavePivote(int i, int j){
	return new Random().nextInt(i, j);
}					
```
Método auxiliar partición
```java
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
```
Quicksort:
```java
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
```


## Selection Sort - O(n^2)

El array se divide en dos partes, la ordenada y la no ordenada. Al inicio la ordenada está vacía y la no ordenada es todo el array. En cada paso el algoritmo busca el elemento mínimo de la parte desordenada y la agrega al final de la ordenada. Cuando la lista no ordenada se vacía se detiene. 

```java
public void selectionSort(int[] arr) {
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
}
```

## Heap Sort - O(n log n)
Tiene un tiempo de ejecución de O(n log n) en *TODOS* los casos. 

```java
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
		System.out.println("");
		for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
			System.out.println(i);
			armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
		}
			
		for (int i = datosParaClasificar.length - 1; i > 0; i--) {
			intercambiar(datosParaClasificar, 0, i);
			armaHeap(datosParaClasificar, 0, i - 1);
		}
		return datosParaClasificar;
	}
```
