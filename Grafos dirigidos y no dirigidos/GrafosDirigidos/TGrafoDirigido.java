package GrafosDirigidos;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ernesto
 */
public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
            TVertice origen = this.vertices.get(arista.getEtiquetaOrigen());
            TVertice dest = this.vertices.get(arista.getEtiquetaDestino());
            if (origen != null && dest != null && !origen.getAdyacentes().contains(dest)){
                origen.getAdyacentes().add(new TAdyacencia(arista.getCosto(), dest));
            }
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino.
     * En caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean inv�lidas, retorna falso.
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
     * existir el vertice, retorna falso. En caso de que la etiqueta sea
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


    /**
     * Metodo encargado de verificar la existencia de una arista. Las
     * etiquetas pasadas por par�metro deben ser v�lidas.
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
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea
     * valida, se deben cumplir los siguientes casos: 1) Las etiquetas pasadas
     * por parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen()!= null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
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
    @Override
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
     if (!existeVertice(unaEtiqueta)) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    
    
    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }
    
 

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

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
    public Double obtenerExcentricidad(Comparable etiquetaVertice) {
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
            if (aux[i][index] != Double.MAX_VALUE && aux[i][index] > valMax){
                valMax = aux[i][index];
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
    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        Collection result = new LinkedList<TVertice>();
        if (vertice != null){
            vertice.bpf(result);
        }
        return result;
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice verts : this.vertices.values()){
            verts.setVisitado(false);
        }
    }
    
    public boolean existeConexion(Comparable vert1, Comparable vert2){
        desvisitarVertices();
        
        boolean result = false;
        TVertice nuevoOrigen = vertices.get(vert1);
        TVertice nuevoDest = vertices.get(vert2);
        if (nuevoOrigen != null && nuevoDest != null){
            result = nuevoOrigen.estaConectado(vert2);
        }
        return result;
    }
    
    public String getRecorridoBPF(){
        desvisitarVertices();
        Collection<TVertice> bpf = this.bpf();
        String res = "";
        for(TVertice vert : bpf){
            res+= vert.getEtiqueta() + "-";
        }
        return res.substring(0, res.length()-1);
    }
    
    public String getRecorridoBPF(Comparable origen){
        desvisitarVertices();
        Collection<TVertice> bpf = this.bpf(origen);
        String res = "";
        for(TVertice vert : bpf){
            res+= vert.getEtiqueta() + "-";
        }
        return res.substring(0, res.length()-1);
    }

    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = buscarVertice(etiquetaOrigen);
        if(v != null){
            TCamino caminoPrevio = new TCamino(v);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }

}
