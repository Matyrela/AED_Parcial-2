package GrafosNoDirigidos;


import java.util.Collection;

import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = " - ";

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        for (TArista edge : this){
            if (edge.getEtiquetaOrigen().compareTo(etOrigen)==0 && edge.getEtiquetaDestino().compareTo(etDestino)==0){
                return edge;
            }
        }
        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
       
        //---------COMPLETAR ALGORITMO--------
        // para todo u en Vertices U
        // para todo v en Vertices V
        // tA =buscar (u, v)
        // si tA <> null y tA.costo < costoMin entonces
        // tAMin = tA y costoMin = tA.costo
        // fin para todo v
        // fin para todo u
        // devolver tAMin
        TArista minArt = null;
        double costoMin = Double.MAX_VALUE;
        for (Comparable U : VerticesU){
            for (Comparable V : VerticesV){
                TArista art = buscar(U,V);
                if (art!=null && art.costo < costoMin){
                    minArt = art;
                    costoMin = art.costo;
                }
            }
        }
        return minArt;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for (TArista art : this){
            salida.append(art.getEtiquetaOrigen()+SEPARADOR_ELEMENTOS_IMPRESOS+art.getEtiquetaDestino()+SEPARADOR_ELEMENTOS_IMPRESOS+art.getCosto()+"\n");
        }
        //TODO: Completar codigo que imprime todas las aristas de la lista en el siguiente formato:
        //ORIGEN - DESTINO - COSTO
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        if (aristas == null) return;
        TArista tempArista;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }

}
