package me.mati.GrafosDirigidos;


import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice<T> implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int valBP;
    private int valBajo;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    public int getValBajo() {
        return this.valBajo;
    }

    public int getValBP() {
        return this.valBP;
    }

    public void setValBajo(int value) {
        this.valBajo = value;
    }

    public void setValBP(int value) {
        this.valBP = value;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void sortTopologico(LinkedList<TVertice> lista) {
        this.setVisitado(true);
        for (TAdyacencia ady : this.adyacentes) {
            if (!ady.getDestino().getVisitado()) {
                ady.getDestino().sortTopologico(lista);
            }
        }
        lista.add(this);
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
        visitados.add(this);
        this.visitado = true;
        for (TAdyacencia adyacente : this.adyacentes) {
            if (!adyacente.getDestino().getVisitado()) {
                adyacente.getDestino().bpf(visitados);
            }
        }
    }

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

    @Override
    public void bea(Collection<TVertice> visitados) {
        Queue<TVertice> cola = new LinkedList<>();

        this.setVisitado(true);
        cola.add(this);
        visitados.add(this);
        while (!cola.isEmpty()) {
            TVertice x = cola.remove();
            LinkedList<TAdyacencia> adyacentes = x.getAdyacentes();
            for (TAdyacencia ady : adyacentes) {
                TVertice y = ady.getDestino();
                if (!y.getVisitado()) {
                    y.setVisitado(true);
                    cola.add(y);
                    visitados.add(y);
                }
            }
        }
    }

    public void getArticulaciones(Collection<TVertice> art, int[] cont){
        cont[0] ++;
        this.valBP = cont[0];
        this.valBajo = cont[0];
        LinkedList<TVertice> hijos = new LinkedList<>();
        this.setVisitado(true);
        for (TAdyacencia ady : this.adyacentes){
            TVertice dest = ady.getDestino();
            if(!dest.getVisitado()){
                dest.getArticulaciones(art,cont);
                hijos.add(dest);
                this.valBajo = Math.min(this.getValBajo(), dest.getValBajo());
            } else {
                this.valBajo = Math.min(this.getValBajo(), dest.getValBP());
            }
        }
        if(this.valBP > 1){
            for(TVertice hijo : hijos) {
                if (hijo.getValBajo() >= this.getValBP()) {
                    art.add(this);
                }
            }
        } else {
            if (hijos.size() > 1){
                art.add(this);
            }
        }
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        this.setVisitado(true);
        camino.add(this.etiqueta);
        for (TAdyacencia adyacencia : this.adyacentes) {
            Comparable etiquetaAdyacente = adyacencia.getDestino().getEtiqueta();
            if (camino.contains(etiquetaAdyacente)) {
                return true;
            }
            if (!adyacencia.getDestino().getVisitado()) {
                if (adyacencia.getDestino().tieneCiclo(camino)) {
                    return true;
                }
            }
        }
        camino.remove(this.etiqueta);
        return false;
    }

    @Override
    public boolean conectadoCon(TVertice destino) {
        if (this.etiqueta.compareTo(destino.getEtiqueta()) == 0) {
            return true;
        }

        this.setVisitado(true);
        for (TAdyacencia adyacencia : adyacentes) {
            TVertice verticeAdyacente = adyacencia.getDestino();
            if (!verticeAdyacente.getVisitado()) {
                if (verticeAdyacente.conectadoCon(destino)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return (getEtiqueta() + ": " + getAdyacentes().toString());
    }

}
