## UT9 - Sort

### Por insercion O(n2)

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

### Shell Sort

### Buble Sort

### Quick Sort O(n*log n)

### Heap Sort
