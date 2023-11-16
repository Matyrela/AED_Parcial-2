# TODAVÍA EN PROCESO

# Resumen de Definiciones:
## Grafos

# Excentricidad
Es el peso máximo (o la suma de estos) de los posibles caminos desde un vértice A hacia cualquier vértice en el grafo (B, C, D o E).
Ejemplo:

(A)
 |  ↖ 
 3    2
 ↓      \
(B)--5->(C)

Al analizar la matriz de adyacencia:
(origen -> destino)
      A     B     C     
A     0.0   3.0   8.0   
B     7.0   0.0   5.0   
C     2.0   5.0   0.0 

### Obtener la excentricidad de A:
A tiene 2 posibles caminos:
1. A -> B (3)
2. A -> C (8) (Este camino no es directo; es la suma de A -> B -> C [3 + 5])
De estos caminos, nos quedamos con el mayor, ***8***, que es nuestra excentricidad.

## Para el ejemplo de arriba:
### Excentricidades:
+ A: 8.0
+ B: 7.0
+ C: 5.0

___
