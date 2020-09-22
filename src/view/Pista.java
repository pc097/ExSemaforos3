package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

public class Pista {
	public static void main(String[] args) {
		int permissao = 5;
		Semaphore semaforo = new Semaphore(permissao);
		for (int idCarro = 0; idCarro < 14; idCarro++) {
			Thread carro = new ThreadCarro(idCarro, semaforo);
			carro.start();
		}
	}
}