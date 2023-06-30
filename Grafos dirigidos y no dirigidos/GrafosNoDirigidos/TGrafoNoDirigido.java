package GrafosNoDirigidos;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim(){
        TAristas T = new TAristas();
        HashMap<Comparable,TVertice> U = new HashMap<>();
        HashMap<Comparable,TVertice> V = new HashMap<>(this.vertices);
        U.put((Comparable)V.keySet().toArray()[0],(TVertice)V.values().toArray()[0]);
        
        while (!V.isEmpty()){
            TArista min = getLasAristas().buscarMin(U.keySet(),V.keySet());
            T.add(min);
            U.put(min.etiquetaDestino, vertices.get(min.etiquetaDestino));
            V.remove(min.getEtiquetaDestino());
        }
        return new TGrafoNoDirigido(U.values(), T);
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TGrafoNoDirigido arbolCostoMinimo = new TGrafoNoDirigido(vertices.values(),new TAristas());
        lasAristas.sort((TArista o1, TArista o2) -> {
            if (o1.costo < o2.costo){
                return -1;
            } else if(o1.costo > o2.costo){
                return 1;
            } else{
                return 0;
            }
        });;
        TAristas aristasOrdenadas = new TAristas();
        aristasOrdenadas.addAll(lasAristas);
        int aristasAgregadas = 0;

        while (aristasAgregadas != getVertices().size() - 1){
            TArista aristaMin = aristasOrdenadas.removeFirst();
            TVertice verticeOrigen = arbolCostoMinimo.vertices.get(aristaMin.getEtiquetaOrigen());
            TVertice verticeDestino = arbolCostoMinimo.vertices.get(aristaMin.getEtiquetaDestino());
            if (!arbolCostoMinimo.estanConectados(verticeOrigen.getEtiqueta(), verticeDestino.getEtiqueta())){
                arbolCostoMinimo.insertarArista(aristaMin);
                arbolCostoMinimo.getLasAristas().add(aristaMin);
                arbolCostoMinimo.getLasAristas().add(aristaMin.aristaInversa());
                aristasAgregadas++;
            }
        }
        return arbolCostoMinimo;

    }
    public boolean estanConectados(Comparable etiqueta1, Comparable etiqueta2){
        TVertice vert1 = vertices.get(etiqueta1);
        TVertice vert2 = vertices.get(etiqueta2);
        desvisitarVertices();

        return vert1.conectadoCon(vert2) || vert2.conectadoCon(vert1);
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        desvisitarVertices();
        TVertice vert = getVertices().get(etiquetaOrigen);
        LinkedList res = new LinkedList<TVertice>();
        vert.bea(res);
        return res;
    }

    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        desvisitarVertices();
        LinkedList<TVertice> res = new LinkedList<>();
        vertices.get(etOrigen).getArticulaciones(res);
        return res;
    }

    @Override
    public boolean esConexo() {
        return this.vertices.values().size() == bpf(vertices.keySet().iterator().next()).size();
    }
}
