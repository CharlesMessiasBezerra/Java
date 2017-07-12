package Vetor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AnaliseComplexibilidade.analiseHelper;

public class TBVetor {

	public void geraVetorAleatorio(Integer[] vet, Integer tam) {

		Random gerador = new Random();

		for (int i = 0; i < tam; i++) {
			vet[i] = gerador.nextInt(Integer.MAX_VALUE);
			// vet[i] = gerador.nextInt(100);
		}
	}

	public void Ordena(Integer[] arr, int low, int high) {

		if (arr == null || arr.length == 0)
			return;
		if (low >= high)
			return;

		int middle = low + (high - low) / 2;
		int pivot = arr[middle];

		int i = low;
		int j = high;

		while (i <= j) {

			while (arr[i] < pivot) {
				i++;
			}

			while (arr[j] > pivot) {
				j--;
			}

			if (i <= j) {
				if (i != j) {
				}

				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}

		if (low < j)
			Ordena(arr, low, j);

		if (high > i)
			Ordena(arr, i, high);

	}

	public void OrdenaVetorInverso(Integer[] vetor) {
		int temp;
		for (int i = 0; i < vetor.length / 2; i++) {
			temp = vetor[vetor.length - i - 1];
			vetor[vetor.length - i - 1] = vetor[i];
			vetor[i] = temp;
		}

	}

}
