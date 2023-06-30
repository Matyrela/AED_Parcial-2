package org.example;

import java.util.Collection;
import java.util.Map;

public interface IGrafoNoDirigido {

    Collection<TVertice> bea();

    Collection<TVertice> bea(Comparable etiquetaOrigen);

    boolean esConexo();

    boolean conectados(TVertice origen, TVertice destino);

    TGrafoNoDirigido Prim();

    TGrafoNoDirigido Kruskal();
}
