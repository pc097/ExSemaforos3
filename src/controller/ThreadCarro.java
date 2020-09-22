package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {

	private int idCarro;
	private Semaphore semaforo;
	public static int[][] resultado = new int[2][14];
	// [0][] = idCarro / [1][] = tempo
	public static int fimVoltas;
	public ThreadCarro(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	public void run() {
		try {
			semaforo.acquire();
			carroNaPista();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void carroNaPista() {
		int[] tempos = new int[3];
		for (int i = 0; i < 3; i++) {
			tempos[i] = (int) ((Math.random() * 31) + 70);
			System.out.println("" + nomePiloto(idCarro) + " fez " + tempos[i] + " segundos na " + (i + 1) + "° volta.");
		}
		for (int i = 1; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if (tempos[i] > tempos[j]) {
					int aux = 0;
					aux = tempos[i];
					tempos[i] = tempos[j];
					tempos[j] = aux;
				}
			}
		}
		int melhorVolta = tempos[2];
		resultado[0][idCarro] = idCarro;
		resultado[1][idCarro] = melhorVolta;
		fimVoltas++;
		if (fimVoltas > 13) {
			classificacao();
		}
	}

	public void classificacao() {
		for (int i = 0; i < 14; i++) {
			for (int j = (i + 1); j < 14; j++) {
				if (resultado[1][i] > resultado[1][j]) {
					int auxId = resultado[0][j];
					int auxTempo = resultado[1][j];
					resultado[0][j] = resultado[0][i];
					resultado[1][j] = resultado[1][i];
					resultado[0][i] = auxId;
					resultado[1][i] = auxTempo;

				}
			}
		}
		System.out.println();
		System.out.println("Classificação Final do Treino:");
		System.out.println();
		for (int i = 0; i < 14; i++) {
			System.out.println("#" + (i + 1) + " " + nomePiloto(ThreadCarro.resultado[0][i]) + " -> "
					+ ThreadCarro.resultado[1][i] + " segundos. ");
		}
	}

	public static String nomePiloto(int idCarro) {

		switch (idCarro) {
		case 0:
			return "Vettel";
		case 1:
			return "Leclerc";
		case 2:
			return "Norris";
		case 3:
			return "Sainz";
		case 4:
			return "Hamilton";
		case 5:
			return "Bottas";
		case 6:
			return "Ricciardo";
		case 7:
			return "Ocon";
		case 8:
			return "Albon";
		case 9:
			return "Verstappen";
		case 10:
			return "Latifi";
		case 11:
			return "Russel";
		case 12:
			return "Raikkonen";
		case 13:
			return "Giovinazzi";
		}
		return null;

	}
}