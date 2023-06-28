# Hashing

## Metodos de hashing

## Insercion en tabla hash

## Tratamiento de colisiones

1. [Sondeo Lineal](#sondeo-lineal)
2. [Sondeo Cuadratico](#sondeo-cuadrático)
3. [Doble Hashing](#doble-hashing)
4. [Encadenamiento Simple](#encadenamiento-simple)

## Direccionamiento Abierto

### Sondeo Lineal
- Busca secuencialmente en la matriz hasta encontrar una celda vacía. 
- Salta circularmente desde la ultima posicion a la primera.
- El intervalo entre cada intento es constante (frecuentemente 1)

### Sondeo Cuadrático
- Busca secuencialmente en la matriz hasta encontrar una celda vacía.
- El intervalo entre los intentos aumenta linealmente (por lo que los índices son descritos por una función cuadrática).

### Doble Hashing
-  El intervalo entre intentos es constante para cada registro pero es calculado por otra función hash.
-  Se vuelve a aplicar la función hash cuando encuentra una colisión.
-  Puede requerir más cálculos que las otras formas de sondeo.

## Direccionamiento Cerrado

### Encadenamiento Simple
- Cada casilla en el array referencia a una lista con los registros insertados que colisionan en dicha casilla.
- Consiste en encontrar la casilla correcta y agregar al final de la lista correspondiente.
- El borrado consiste en buscar y quitar de la lista.

---

## Eliminar de la tabla
- Es un proceso muy difícil a menos que se use encadenamiento directo.

### Solución: Borrado perezoso
- marcamos la celda como borrada (borrado == libre).
- al buscar, si encontramos una celda marcada como borrada continuamos la búsqueda.
- al añadir, puede ocuparse una celda marcada como borrada y sobreescribir el dato.

# Colecciones

## Set (clave)
- [HashSet](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashSet.html)
- [TreeSet](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeSet.html)
- [LinkedHashSet](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedHashSet.html)

## Map (clave-valor)
- [HashMap](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashMap.html)
- [TreeMap](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeMap.html)
- [LinkedHashMap](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedHashMap.html)

## List
- [ArrayList](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html)
- [LinkedList](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedList.html)

[ARRIBA](#hashing)
