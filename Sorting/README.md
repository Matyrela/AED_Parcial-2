# UT9 - Sort

## Por insercion - O(n^2)
En el mejor de los casos tiene un tiempo de ejecución de O(n), pero en el peor de los casos O(n^2)
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

## Quick Sort - O(n^2)
Si se elije un buen pivote podría ser el más eficiente con un tiempo de ejecucion de O(n log n), pero en el peor de los casos con una mala elección de pivote tendría un tiempo de ejecución de O(n^2).

## Heap Sort O - (n log n)
Tiene un tiempo de ejecución de O(n log n) en *TODOS* los casos. 
