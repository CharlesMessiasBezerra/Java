package Algoritmos;

import AnaliseComplexibilidade.analiseHelper;

public class QuickSort {

	public static long trocas;
	public static long comparacoes;

	public QuickSort() {
		trocas = 0;
		comparacoes = 0;
	}

	public analiseHelper exec(Integer[] arr, int low, int high) {

		if (arr == null || arr.length == 0)
			return (new analiseHelper(0, 0));

		if (low >= high)
			return (new analiseHelper(0, 0));

		
		int middle = low + (high - low) / 2;
		int pivot = arr[middle];

		int i = low;
		int j = high;

		while (i <= j) {

			while (arr[i] < pivot) {
				comparacoes += 1;
				i++;
			}

			while (arr[j] > pivot) {
				comparacoes += 1;
				j--;
			}

			if (i <= j) {
				 if(i != j){
				trocas += 1;
				 }

				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}

		if (low < j)
			exec(arr, low, j);

		if (high > i)
			exec(arr, i, high);

		return (new analiseHelper(trocas, comparacoes));
	}

}
