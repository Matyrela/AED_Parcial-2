# TODAVÍA EN PROCESO

#Grafos

## Excentricidad
Es el peso máximo (o la suma de estos) de los posibles caminos desde un vértice A hacia cualquier vértice en el grafo (B, C, D o E).  
Ejemplo:

```plaintext
(A)
 |  ↖ 
 3    2
 ↓      \
(B)--5->(C)
```

Al analizar la matriz de adyacencia:
(origen -> destino)
```plaintext
------------------------
      A     B     C     
------------------------
A     0.0   3.0   8.0   
------------------------
B     7.0   0.0   5.0   
------------------------
C     2.0   5.0   0.0   
------------------------
```

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

## Centro del grafo
El centro del grafo es aquel vertice cuya excentricidad sea la menor de todas, esto quiere decir que el vértice central tiene la propiedad de minimizar esa distancia máxima  
con la lista anterior podemos sacar la conclucion de que ***C*** es el centro del grafo ya que tiene la menor de las excentricidades.

+ A: 8.0
+ B: 7.0
+ ***C: 5.0***


___
