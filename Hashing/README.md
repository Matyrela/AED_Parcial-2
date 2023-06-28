### Hashing

## Metodos de hashing

## Insercion en tabla hash

## Tratamiento de colisiones

1. Sondeo lineal
2. Sondeo cuadratico
3. Doble Hashing
4. Encadenamiento simple

# Direccionamiento Abierto

  # Sondeo Lineal
  - Busca secuencialmente en la matriz hasta encontrar una celda vacía. 
  - Salta circularmente desde la ultima posicion a la primera.
  - El intervalo entre cada intento es constante (frecuentemente 1)
  
  # Sondeo Cuadrático
  - Busca secuencialmente en la matriz hasta encontrar una celda vacía.
  - El intervalo entre los intentos aumenta linealmente (por lo que los índices son descritos por una función cuadrática).
  
  # Doble Hashing
  -  El intervalo entre intentos es constante para cada registro pero es calculado por otra función hash.
  -  Se vuelve a aplicar la función hash cuando encuentra una colisión.
  -  Puede requerir más cálculos que las otras formas de sondeo.

# Direccionamiento Cerrado
- Cada casilla en el array referencia a una lista con los registros insertados que colisionan en dicha casilla.
- Consiste en encontrar la casilla correcta y agregar al final de la lista correspondiente.
- El borrado consiste en buscar y quitar de la lista.

## Eliminar de la tabla

- Es un proceso muy difícil a menos que sse use encadenamiento directo.

# Solución: Borrado perezoso
- marcamos la celda como borrada (borrado≠libre)
- al buscar, si encontramos una celda marcada como borrada continuamos la búsqueda
- al añadir, puede ocuparse una celda marcada como borrada
