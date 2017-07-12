package Algoritmos;

import AnaliseComplexibilidade.analiseHelper;

public class HeapSort {

	private Integer[] vetor;
	private int n;
	private int esquerdo;
	private int dirireito;
	private int maior;
	public static long trocas;
	public static long comparacoes;

	public void buildheap(Integer[] a) {
		n = a.length - 1;
		comparacoes++;
		for (int i = n / 2; i >= 0; i--) {
			maxheap(a, i);
			comparacoes++;
		}
	}

	public void maxheap(Integer[] a, int i) {
		esquerdo = 2 * i;
		dirireito = 2 * i + 1;
		comparacoes++;
		if (esquerdo <= n && a[esquerdo] > a[i]) {
			maior = esquerdo;

		} else {
			maior = i;

		}
		comparacoes++;
		if (dirireito <= n && a[dirireito] > a[maior]) {
			maior = dirireito;

		}

		comparacoes++;
		if (maior != i) {
			exchange(i, maior);
			maxheap(a, maior);

		}
	}

	public void exchange(int i, int j) {
		int t = vetor[i];
		vetor[i] = vetor[j];
		vetor[j] = t;
		trocas++;
	}

	public analiseHelper exec(Integer[] vet) {
		trocas = 0;
		comparacoes = 0;
		vetor = vet;
		buildheap(vetor);
		comparacoes++;
		for (int i = n; i > 0; i--) {
			exchange(0, i);
			n = n - 1;
			maxheap(vetor, 0);
			comparacoes++;
		}

		analiseHelper _analiseHelper = new analiseHelper(trocas, comparacoes);
		return _analiseHelper;
	}

}