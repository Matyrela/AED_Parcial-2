package GrafosNoDirigidos;


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
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    TCamino copia = caminoPrevio.copiar();
                    destino.todosLosCaminos(etVertDest, copia, todosLosCaminos);
                }
            }
        }
        this.setVisitado(false);
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

    public void getArticulaciones(Collection<TVertice> res) {
        this.setVisitado(true);
        this.valBP = 1;
        for (TAdyacencia ady : this.adyacentes) {
            TVertice dest = ady.getDestino();
            if (!dest.getVisitado()) {
                dest.setVisitado(true);
                dest.getArticulaciones(res, valBP);
            }
        }
        this.valBajo = this.valBP;
        for (TAdyacencia ady : this.adyacentes) {
            TVertice dest = ady.getDestino();
            if (this.valBP >= dest.getValBajo()) {
                if (this.valBajo > dest.getValBajo()) {
                    this.valBajo = dest.getValBajo();
                }
                if (this.valBajo > dest.getValBP()) {
                    this.valBajo = dest.getValBP();
                }
                res.add(this);
            } else {
                if (this.valBajo > dest.getValBajo()) {
                    this.valBajo = dest.getValBajo();
                }
                if (this.valBajo > dest.getValBP()) {
                    this.valBajo = dest.getValBP();
                }
            }
        }
    }

    private void getArticulaciones(Collection<TVertice> res, int BP){
        this.setVisitado(true);
        this.valBP = BP+1;
        for (TAdyacencia ady : this.adyacentes){
            TVertice dest = ady.getDestino();
            if(!dest.getVisitado()){
                dest.setVisitado(true);
                dest.getArticulaciones(res, valBP);
            }
        }
        this.valBajo = this.getValBP();
        for (TAdyacencia ady : this.adyacentes){
            TVertice dest = ady.getDestino();
            if (this.getValBP() >= dest.getValBajo()){
                if(this.getValBajo() > dest.getValBajo()){
                    this.valBajo = dest.getValBajo();
                }
                if(this.getValBajo() > dest.getValBP()){
                    this.valBajo = dest.getValBP();
                }
                res.add(this);
            } else{
                if(this.getValBajo() > dest.getValBajo()){
                    this.valBajo = dest.getValBajo();
                }
                if(this.getValBajo() > dest.getValBP()){
                    this.valBajo = dest.getValBP();
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
}