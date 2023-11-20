package me.mati.GrafosDirigidos;


import me.mati.GrafosNoDirigidos.TAristas;
import me.mati.Util.UtilGrafos;

import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    protected final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.

    private Collection<TArista> lasAristas;
    

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.lasAristas = aristas;
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    public Collection<TArista> getLasAristas() {
        return this.lasAristas;
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     * @param nombreVertice
     * @return
     */
    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

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

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Matodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                //getLasAristas().add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }


    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    /**
     * @return the vertices
     */
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        Collection result = new LinkedList<TVertice>();
        if (vertice != null){
            vertice.bpf(result);
        }
        return result;
    }

    @Override
    public Collection<TVertice> bpf() {
       desvisitarVertices();
        Collection result = new LinkedList<TVertice>();
        if (vertices.isEmpty()){
            return null;
        } else{
            for (TVertice vert : this.vertices.values()){
                if (!vert.getVisitado()){
                    vert.bpf(result);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        desvisitarVertices();
        Collection result = new LinkedList<TVertice>();
        TVertice nuevoOrigen = vertices.get(etiquetaOrigen);
        if (nuevoOrigen != null){
            nuevoOrigen.bpf(result);
        }
        return result;
    }

    @Override
    public Comparable centroDelGrafo() {
        Comparable res = null;
        Double minExc = Double.MAX_VALUE;
        for (Comparable vert : vertices.keySet()){
            Double exc = (Double)obtenerExcentricidad(vert);
            if (exc < minExc && exc != -1){
                res = vert;
                minExc = exc;
            }
        }
        return res;
    }

    @Override
    public Double[][] floyd() {
        Double[][] res = UtilGrafos.obtenerMatrizCostos(this.vertices);
        int largo = res.length;
        for (int i = 0; i < largo; i++){
            for (int j = 0; j < largo; j++){
                for (int k = 0; k < largo; k++){
                    if(res[j][i] + res[i][k] < res[j][k]){
                        res[j][k] = res[j][i] + res[i][k];
                    }
                }
            }
        }
        return res;
    }

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
        if (valMax == 0){
            return -1.0;
        }else{
            return valMax;
        }
    }

    @Override
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

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        TVertice origen = vertices.get(etiquetaOrigen);
        TCamino camino = new TCamino(origen);
        TCaminos resultado = new TCaminos();
        origen.todosLosCaminos(etiquetaDestino, camino, resultado);
        return resultado;
    }


    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        TVertice vertV = vertices.get(etiquetaOrigen);
        if (vertV != null) {
            desvisitarVertices();
            LinkedList<Comparable> camino = new LinkedList<>();
            return vertV.tieneCiclo(camino);
        }
        return false;
    }

    @Override
    public boolean tieneCiclo(TCamino camino) {
        desvisitarVertices();
        LinkedList<Comparable> caminoAux = new LinkedList<>();
        return camino.getOrigen().tieneCiclo(caminoAux);
    }

    @Override
    public boolean tieneCiclo() {
        boolean result = false;
        if (vertices.isEmpty()) {
            System.out.println(" el grafo está vacio");
            return result;
        }
        desvisitarVertices();
        for (TVertice vertV : this.vertices.values()) {
            if (!vertV.getVisitado()) {
                LinkedList<Comparable> camino = new LinkedList<>();
                result = vertV.tieneCiclo(camino);
                if (result) {
                    return true;
                }
            }
        }
        if (!result) {
            System.out.println("no hay ciclos");
        }
        return result;
    }

    @Override
    public Collection<TVertice> bea() {
        desvisitarVertices();
        TVertice vert = getVertices().get(vertices.values().iterator().next().getEtiqueta());
        LinkedList res = new LinkedList<TVertice>();
        vert.bea(res);
        return res;
    }

    public HashMap<TCamino, Double> todosLosCaminosConCosto(String inicio, String fin) {
        desvisitarVertices();
        TVertice origen = vertices.get(inicio);
        TCamino camino = new TCamino(origen);
        HashMap resultado = new HashMap<TCamino, Double>();
        origen.todosLosCaminosConCosto(fin, camino, resultado, 0);
        return resultado;
    }

    public TGrafoDirigido grafoinverso(){
        Collection<TArista> Arist = new ArrayList<>();
        for (TArista arista: getLasAristas()) {
            Arist.add(new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto()));
        }

        Collection<TVertice> Vert = new ArrayList<>();
        for (TVertice vertice: getVertices().values()) {
            Vert.add(new TVertice(vertice.getEtiqueta()));
        }

        TGrafoDirigido grafodadovuelta = new TGrafoDirigido(Vert, Arist);

        return grafodadovuelta;
    }
}
