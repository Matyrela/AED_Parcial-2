package org.example;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;


public class TVertice<T> implements IVertice, IVerticeKevinBacon {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }
    private int bacon;

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        bacon = 0;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }


    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }


    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

  
    @Override
    public void bpf(Collection<TVertice> visitados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void bea(Collection<TVertice> visitados) {

        this.setVisitado(true);

        Queue<TVertice> colaDeVertices = new LinkedList<>();
        visitados.add(this);
        colaDeVertices.add(this);
        while (!colaDeVertices.isEmpty()) {
            TVertice x = colaDeVertices.poll();
            LinkedList<TAdyacencia> adyacencias = x.getAdyacentes();
            for (TAdyacencia y : adyacencias) {
                if (!(y.getDestino().getVisitado())) {
                    y.getDestino().setVisitado(true);
                    colaDeVertices.add(y.getDestino());
                    visitados.add(y.getDestino());
                }
            }
        }
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean conectadoCon(TVertice destino) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public int getBacon() {
        return this.bacon;
    }

    @Override
    public void setBacon(int newBacon) {
        this.bacon = newBacon;
    }

    public int numBacon(Comparable actor) {
        this.setVisitado(true);
        Queue<TVertice> colaDeVertices = new LinkedList<>();
        colaDeVertices.add(this);
        this.setBacon(0);
        while (!colaDeVertices.isEmpty()) {
            TVertice x = colaDeVertices.poll();
            LinkedList<TAdyacencia> adyacencias = x.getAdyacentes();
            for (TAdyacencia y : adyacencias) {
                y.getDestino().setBacon(x.getBacon() + 1);
                if (!y.getDestino().getVisitado()){
                    y.getDestino().setVisitado(true);
                    colaDeVertices.add(y.getDestino());
                }
                if (y.getDestino().getEtiqueta().compareTo(actor) == 0){
                    return y.getDestino().getBacon();
                }
            }
        }
        return 0;
    }
}