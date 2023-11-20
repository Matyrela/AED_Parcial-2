package me.mati.GrafosDirigidos;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;

class TGrafoDirigidoTest {

    @Test
    void bpf() {

        TVertice vert1 = new TVertice<>("1");
        TVertice vert2 = new TVertice<>("2");
        TVertice vert3 = new TVertice<>("3");
        TVertice vert4 = new TVertice<>("4");

        TArista art1 = new TArista(vert1.getEtiqueta(), vert2.getEtiqueta(), 4);
        TArista art2 = new TArista(vert2.getEtiqueta(), vert3.getEtiqueta(), 2);
        TArista art3 = new TArista(vert3.getEtiqueta(), vert1.getEtiqueta(), 6);
        TArista art4 = new TArista(vert3.getEtiqueta(), vert4.getEtiqueta(), 8);


        LinkedList <TVertice> vertices = new LinkedList<>();
        vertices.add(vert1);
        vertices.add(vert2);
        vertices.add(vert3);
        vertices.add(vert4);

        LinkedList <TArista> aristas = new LinkedList<>();

        aristas.add(art1);
        aristas.add(art2);
        aristas.add(art3);
        aristas.add(art4);


        TGrafoDirigido gd = new TGrafoDirigido(vertices, aristas);


        Collection<TVertice> Resultado = gd.bpf();

        Collection<TVertice> resultadoEsperado = new LinkedList<>();
        resultadoEsperado.add(vert1);
        resultadoEsperado.add(vert2);
        resultadoEsperado.add(vert3);
        resultadoEsperado.add(vert4);

        Assert.assertEquals(Resultado, resultadoEsperado);
    }

    @Test
    void centroDelGrafo() {
        
    }

    @Test
    void floyd() {
    }

    @Test
    void obtenerExcentricidad() {
    }

    @Test
    void warshall() {
    }

    @Test
    void todosLosCaminos() {
    }

    @Test
    void tieneCiclo() {
    }

    @Test
    void bea() {
    }

    @Test
    void todosLosCaminosConCosto() {
    }

    @Test
    void grafoinverso() {
    }
}