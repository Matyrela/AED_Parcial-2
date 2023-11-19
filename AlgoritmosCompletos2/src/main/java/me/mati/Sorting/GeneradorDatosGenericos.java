package me.mati.Sorting;

import java.util.Random;

public class GeneradorDatosGenericos {
	//TAMAÑO DE LOS DATOS GENERADOS
	private static int TAMANIO_MAX = 100;
	public int[] generarDatosAleatorios() {
		Random rnd = new Random();
		int[] datosGenerados = new int[TAMANIO_MAX];
		boolean[] datosUtilizados = new boolean[TAMANIO_MAX];
		for (int i = 0; i < datosGenerados.length; i++) {
			int j = rnd.nextInt(TAMANIO_MAX);
			while(datosUtilizados[j]){
				j = (j + 1) % TAMANIO_MAX;
			}
			datosGenerados[j] = i;
			datosUtilizados[j] = true;
		}
		return datosGenerados;
	}

	public int[] generarDatosAleatorios(int tm) {
		Random rnd = new Random();
		int[] datosGenerados = new int[tm];
		boolean[] datosUtilizados = new boolean[tm];
		for (int i = 0; i < datosGenerados.length; i++) {
			int j = rnd.nextInt(tm);
			while(datosUtilizados[j]){
				j = (j + 1) % tm;
			}
			datosGenerados[j] = i;
			datosUtilizados[j] = true;
		}
		return datosGenerados;
	}
	
	public int[] generarDatosAscendentes() {
		int [] copiaAscendente = new int[TAMANIO_MAX];
		for (int i = 0; i < TAMANIO_MAX; i++) {
			copiaAscendente[i] = i;
		}
		return copiaAscendente;
	}

	public int[] generarDatosAscendentes(int tm) {
		int [] copiaAscendente = new int[tm];
		for (int i = 0; i < tm; i++) {
			copiaAscendente[i] = i;
		}
		return copiaAscendente;
	}
	
	public int[] generarDatosDescendentes() {
		int [] copiaDescendente = new int[TAMANIO_MAX];
		for (int i = 0; i < TAMANIO_MAX; i++) {
			copiaDescendente[i] = TAMANIO_MAX - i;
		}
		return copiaDescendente;
	}

	public int[] generarDatosDescendentes(int tm) {
		int [] copiaDescendente = new int[tm];
		for (int i = 0; i < tm; i++) {
			copiaDescendente[i] = tm - i;
		}
		return copiaDescendente;
	}
	
}
