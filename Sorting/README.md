# UT9 - Sort

## Por insercion O(n2)

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

## Shell Sort

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

## Buble Sort

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

## Quick Sort O(n*log n)

## Heap Sort
