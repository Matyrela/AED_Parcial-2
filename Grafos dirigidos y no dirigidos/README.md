# UT7 y UT8 Grafos

# Grafos Dirigidos
## Método centro del gráfo:
```java
@Override
public Comparable centroDelGrafo() {
    Comparable res = null;
    Double minExc = Double.MAX_VALUE;
    for (Comparable vert : vertices.keySet()){
        Double exc = obtenerExcentricidad(vert);
        if (exc < minExc && exc != -1){
            res = vert;
            minExc = exc;
        }
    }
    return res;
}
```
Método adyascente obtener excentricidad
```java
 @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] aux = this.floyd();
        int index = 0;
        for (Comparable vert : vertices.keySet()){
            if(vert.equals(etiquetaVertice)){
                break;
            }
            index++;
        }

        Double valMax = 0.0;
        for(int i = 0; i < vertices.keySet().size(); i++){
            if (aux[index][i] != Double.MAX_VALUE && aux[index][i] > valMax){
                valMax = aux[index][i];
            }
        }

        return (valMax == 0) ? -1.0 : valMax;
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
public LinkedList<TVertice> bpf(Comparable etiquetaOrigen) {
    desvisitarVertices();
    LinkedList<TVertice> resultado = new LinkedList();
    TVertice origen = vertices.get(etiquetaOrigen);
    if (origen != null){
        origen.bpf(resultado);
    }
    return resultado;
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
[BEA y BPF](https://www.encora.com/es/blog/dfs-vs-bfs)

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
# Método sort topológico
Método a nivel del grafo
```java
public LinkedList<TVertice> sortTopologico(Comparable vertice) {
        //vertice tiene que ser final, no puede tener vertices incidentes
        LinkedList<TVertice> lista = new LinkedList<TVertice>();
        desvisitarVertices();
        LinkedList<TVertice> verticesList = new LinkedList<TVertice>();
        LinkedList<TArista> aristasList = new LinkedList<TArista>();
        //invertir aristas
        for (TVertice v : this.getVertices().values()) {
            verticesList.add(new TVertice(v.getEtiqueta()));
            for (Object a : v.getAdyacentes()) {
                TAdyacencia ad = (TAdyacencia) a;
                aristasList.add(new TArista(ad.getEtiqueta(), v.getEtiqueta(), ad.getCosto()));
            }
        }

        TGrafoDirigido gd = new TGrafoDirigido(verticesList, aristasList);

        TVertice vert = gd.buscarVertice(vertice);
        if (vert != null) {
            vert.sortTopologico(lista);
            for (Map.Entry<Comparable, TVertice> entry : gd.getVertices().entrySet()) {
                TVertice v = entry.getValue();
                if (!v.getVisitado()) {
                    v.sortTopologico(lista);
                }
            }
        }
        return lista;
    }
```
Método a nivel del vértice
```java
public void sortTopologico(LinkedList<TVertice> lista) {
        this.setVisitado(true);
        for (TAdyacencia ady : this.adyacentes) {
            if (!ady.getDestino().getVisitado()) {
                ady.getDestino().sortTopologico(lista);
            }
        }
        lista.add(this);
    }
```

## Algoritmo de FLOYD
```java
@Override
public Double[][] floyd() {
    Double[][] matriz = UtilGrafos.obtenerMatrizCostos(getVertices());
    int capacidad = matriz[0].length;
    Double[][] matrizFloyd = new Double[capacidad][capacidad];
    for (int i = 0; i < capacidad; i++){
        for (int j = 0; j < capacidad; j++){
            if (i != j){
                matrizFloyd[i][j] = (double) (j + 1);
            }
        }
    }
    for (int k = 0; k < capacidad; k++){
        for (int i = 0; i < capacidad; i++){
            for (int j = 0; j < capacidad; j++){
                if(matriz[i][k] + matriz[k][j] < matriz[i][j]){
                    matriz[i][j] = matriz[i][k] + matriz[k][j];
                    matrizFloyd[i][j] = matrizFloyd[i][k];
                }
            }
        }
    }
    return matrizFloyd;
}
```

## Algoritmo de WARSHALL
```java
public boolean[][] warshall() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(getVertices());
        boolean[][] matrizWarshall = new boolean[matrizCostos.length][matrizCostos.length];
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                matrizWarshall[i][j] = false;

                if (i != j && matrizCostos[i][j] != Integer.MAX_VALUE) {
                    matrizWarshall[i][j] = true;
                }
            }
        }
        for (int k = 0; k < matrizWarshall.length; k++) {
            for (int i = 0; i < matrizWarshall.length; i++) {
                for (int j = 0; j < matrizWarshall.length; j++) {
                    if ((i != k) && (k != j) && (i != j)) {
                        if (!matrizWarshall[i][j]) {
                            matrizWarshall[i][j] = matrizWarshall[i][k] && matrizWarshall[k][j];
                        }
                    }
                }
            }
        }
        return matrizWarshall;
    }
```
## Bosque Abarcador

Es un conjunto de árboles generados a partir del recorrido de un grafo.
- Bosque abarcador en profundidad: utiliza el algoritmo de búsqueda en profundidad.
- Cada árbol del bosque se formara con vértices de digráfo y aquellos arcos que en el recorrido llevan a vértices sin visitar.

### Tipos de Arcos

- Arcos de árbol: son los que forman el árbol.
- Arcos de avance: van de un vértice v a un vértice w que es descendiente propio de v en el árbol abarcador.
- Arcos de retroceso: van de un vértice v a un w que es antecesor de v en el árbol abarcador.
- Arcos cruzados: van de un vértice v a uno w que no es ancestro ni descendiente.

- Un arco no abarcador que va de un vértice a un descendiente propio se llama arco de avance.

## Grafos dirigidos Acíclicos

- Ciclo: Camino simple de longitud al menos 1 que empieza donde termina.
- Un grafo dirigido acíclico(gda) es un grafo dirigido sin ciclos.

### Prueba de Aciclicidad
- Se utiliza una búsqueda en profundidad para ver si un grafo es acíclico.
- Si se encuentra un arco de retroceso durante la búsqueda en profundidad de G, el grafo tiene un ciclo.
- Si, al contrario, un grafo dirigido tiene un ciclo, entonces siempre habrá un arco de retroceso en la búsqueda en profundidad del grafo.
  
# Grafos no Dirigidos

# Arcos

- Para grafos no dirigidos, hay dos clases de arcos: de árbol y de retroceso.
- En grafos no dirigidos no existe distinción entre las aristas de retroceso y las de avance, se denominarán arcos de retroceso.

# Puntos de articulación
- Un punto de articulación es un vértice v tal que, cuando se elimina, junto con todas las aristas incidentes sobre él, se divide un componente conexo en dos o más partes.
• A un grafo sin puntos de articulación se le llama “grafo biconexo”.
• Un grafo tiene conectividad k si la eliminación de k-1 vértices cualesquiera no lo desconecta.
• La búsqueda en profundidad es muy útil para encontrar los componentes biconexos de un grafo.
  
## Propiedad AAM (Árbol Abarcador de costo Mínimo)

- Si (u,v) es una arista de costo mínimo tal que u pertenece a U y v pertenece a V-U, existe un AAM que incluye a (u,v) entre sus aristas.
Dos algoritmos hacen uso de esta propiedad: Prim y Kruskal.

## Método bea (busqueda en amplitud)
Se recorre los nodos de un grafo, comenzando en la raíz para luego explorar todos los vecinos de este nodo.
Luego para cada uno de los vecinos se exploran sus respectivos vecinos adyacentes, y así hasta que se recorra todo el grafo. 
![bea](assets/BusquedaEnAmplitud.png)
***
- Método a nivel del grafo
```java
@Override
public Collection<TVertice> bea(Comparable etiquetaOrigen) {
    desvisitarVertices();
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

## Algoritmo de PRIM - O(n^2)

```java
@Override
public TGrafoNoDirigido Prim() {
    Collection<Comparable> V = new ArrayList<>();
    Collection<Comparable> U = new ArrayList<>();
    Collection<TArista> AristasAAM = new ArrayList<>();
    double costoPrim = 0;

    for (TVertice vertice : this.getVertices().values()) {
            V.add(vertice.getEtiqueta());
    }

    U.add(V.iterator().next());
    V.remove(V.iterator().next());

    while (V.size() != 0) {
        TArista tempArista = this.lasAristas.buscarMin(U, V);
        AristasAAM.add(tempArista);
        V.remove(tempArista.getEtiquetaDestino());
        U.add(tempArista.getEtiquetaDestino());
        costoPrim = costoPrim + tempArista.getCosto();
    }

    Collection<TVertice> VerticesSeleccionados = new ArrayList<>();

    for (Comparable vertice : U) {
        VerticesSeleccionados.add(new TVertice(vertice));
    }

    return new TGrafoNoDirigido(VerticesSeleccionados, AristasAAM);
}
```

## Algoritmo de Kruskal - O(a.log a)

```java
@Override
public TGrafoNoDirigido Kruskal() {
    TGrafoNoDirigido AAM = new TGrafoNoDirigido(getVertices().values(),new TAristas());
    var aristasDesordenadas = lasAristas;
    aristasDesordenadas.sort((TArista a1, TArista a2) -> {
        if (a1.costo < a2.costo){
            return -1;
        } else if(a1.costo > a2.costo){
            return 1;
        } else{
            return 0;
        }
    });;
    TAristas aristasOrdenadas = new TAristas();
    aristasOrdenadas.addAll(aristasDesordenadas);
    int aristasAgregadas = 0;

    while (aristasAgregadas != getVertices().size() - 1){
        TArista aristaMin = aristasOrdenadas.removeFirst();
        TVertice verticeOrigen = AAM.getVertices().get(aristaMin.getEtiquetaOrigen());
        TVertice verticeDestino = AAM.getVertices().get(aristaMin.getEtiquetaDestino());
        if (!AAM.estanConectados(verticeOrigen.getEtiqueta(), verticeDestino.getEtiqueta())){
            AAM.insertarArista(aristaMin);
            AAM.getLasAristas().add(aristaMin);
            AAM.getLasAristas().add(aristaMin.aristaInversa());
            aristasAgregadas++;
        }
    }
    return AAM;
}
```

## Estan Conectados
Grafo
```java
public boolean estanConectados(Comparable vertice1, Comparable vertice2){
        TVertice verticeGrafo1 = vertices.get(vertice1);
        TVertice verticeGrafo2 = vertices.get(vertice2);

        return verticeGrafo1.estaConectado(verticeGrafo2) || verticeGrafo2.estaConectado(verticeGrafo1);
    }
```

Vertice
```java
public boolean estaConectado(TVertice vertice2) {
        for (TAdyacencia adyacente : adyacentes) {
            if (adyacente.getDestino().getEtiqueta().compareTo(vertice2.getEtiqueta()) == 0){
                return true;
            }
        }
        return false;
    }
```
