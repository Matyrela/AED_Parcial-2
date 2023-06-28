# UT7 y UT8 Grafos

# Grafos Dirigidos
## Método bea (busqueda en amplitud)
![bea](assets/BusquedaEnAmplitud.png)
***
- Método a nivel del grafo
```java
@Override
public Collection<TVertice> bea(Comparable etiquetaOrigen) {
   if(this.getVertices().get(etiquetaOrigen) != null){
        LinkedList<TVertice> recorrido = new LinkedList<>();
        TVertice v = this.getVertices().get(etiquetaOrigen);
        v.bea(recorrido);
        return recorrido;
    }
    return null;
}
```
- Método a nivel del vértice
```java
@Override
public void bea(Collection<TVertice> visitados) {
    Queue<TVertice> queue = new LinkedList<>();
    this.setVisitado(true); // Lo visito
    queue.add(this);
    visitados.add(this); // Lo agrego a visitados

    while(!queue.isEmpty()){
        TVertice x = queue.poll();
        LinkedList<TAdyacencia> adyacencias = x.getAdyacentes();
        for(TAdyacencia y : adyacencias){
            TVertice j = y.getDestino();
            if(!j.getVisitado()){
                j.setVisitado(true);
                queue.add(j);
                visitados.add(j);
            }
        }
    }
}
```
## Método bpf (busqueda por profundidad)
- Se puede emplear el mismo algoritmo definido para *grafos dirigidos y no dirigidos*.
- En este caso, si el grafo es conexo, de la búsqueda en profundidad se obtiene un sólo árbol
- Para grafos no dirigidos, hay dos clases de arcos: de árbol y de retroceso.
![bpf](assets/BusquedaProfundidad.png)
***
- Método a nivel del grafo
```java
@Override
public void bpf(Collection<TVertice> visitados) {
    setVisitado(true);
    visitados.add(this);
    for (TAdyacencia adyacente : adyacentes) {
        TVertice vertAdy = adyacente.getDestino();
        if (!vertAdy.getVisitado()) {
            vertAdy.bpf(visitados);
        }
    }
}
```
- Método a nivel del vértice 
```java
@Override
public void bpf(Collection<TVertice> visitados) {
    setVisitado(true);
    visitados.add(this);
    for (TAdyacencia adyacente : adyacentes) {
        TVertice vertAdy = adyacente.getDestino();
        if (!vertAdy.getVisitado()) {
            vertAdy.bpf(visitados);
        }
    }
}
```

# Método todos los caminos
Método a nivel del grafo
```java
@Override
public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
    desvisitarVertices();
    TVertice origen = vertices.get(etiquetaOrigen);
    TCamino camino = new TCamino(origen);
    TCaminos resultado = new TCaminos();
    origen.todosLosCaminos(etiquetaDestino, camino, resultado);
    return resultado;
}
```

Método a nivel del vértice
```java
@Override
public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
    visitado = true;
    for(TAdyacencia adyacente : adyacentes){
        TVertice destino = adyacente.getDestino();
        if(!destino.getVisitado()){
            TCamino copia = caminoPrevio.copiar();
            copia.agregarAdyacencia(adyacente);
            if(destino.getEtiqueta().compareTo(etVertDest) == 0){
                todosLosCaminos.getCaminos().add(copia);
            }else{
                adyacente.getDestino().todosLosCaminos(etVertDest, copia, todosLosCaminos);
            }
        }
    }
    visitado = false;
    return todosLosCaminos;
}
```

# Grafos no Dirigidos

when.
