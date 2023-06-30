package org.example;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido, IGrafoKevinBacon {
protected TAristas lasAristas = new TAristas() ;
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

    @Override
    public boolean esConexo() {
        return false;
    }

    @Override
    public boolean conectados(TVertice origen, TVertice destino) {
        return false;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<TVertice> bea() {

        this.desvisitarVertices();

        Collection<TVertice> resultadoBea = new LinkedList<>();

        var vertices = this.vertices.values();
        var primerVertice = vertices.iterator().next();
        primerVertice.bea(resultadoBea);
        return resultadoBea;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {

        this.desvisitarVertices();

        Collection<TVertice> resultadoBea = new LinkedList<>();

        TVertice primerVertice = null;

        var vertices = this.vertices.values();
        for (TVertice vertice : vertices) {
            if (vertice.getEtiqueta() == etiquetaOrigen) {
                primerVertice = vertice;
                break;
            }
        }
        primerVertice.bea(resultadoBea);
        return resultadoBea;
    }


    @Override
    public int numBacon(Comparable actor) {
        desvisitarVertices();
        TVertice verticeBacon = null;

        var vertices = this.vertices.values();
        for (TVertice vertice : vertices) {
            if (vertice.getEtiqueta().compareTo("Kevin_Bacon") == 0) {
                verticeBacon = vertice;
                break;
            }
        }
        return verticeBacon.numBacon(actor);

    }
}
