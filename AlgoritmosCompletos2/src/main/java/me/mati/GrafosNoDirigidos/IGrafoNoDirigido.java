package me.mati.GrafosNoDirigidos;


import me.mati.GrafosDirigidos.TVertice;

import java.util.Collection;
import java.util.LinkedList;

public interface IGrafoNoDirigido {

    public Collection <TVertice> bea();
    
	public Collection <TVertice> bea(Comparable etiquetaOrigen);
    
	public TGrafoNoDirigido Prim();

    public TGrafoNoDirigido Kruskal();
	
	public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen);
    
	boolean esConexo();
}
