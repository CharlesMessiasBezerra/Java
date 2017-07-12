package Algoritmos;

import AnaliseComplexibilidade.analiseHelper;

public class BubbleSort {

	public analiseHelper exec(Integer[] vet) {
		int aux = 0;
		int i = 0;

		long trocas = 0;
		long comparacoes = 0;

		System.out.println(" ");

		for (i = 0; i < vet.length; i++) {
			comparacoes +=1;
			for (int j = 0; j < vet.length - 1; j++) {				
				comparacoes +=1;
				if (vet[j] > vet[j + 1]) {
					trocas +=1; 		
					aux = vet[j];
					vet[j] = vet[j + 1];
					vet[j + 1] = aux;
				}
			}
		}
		analiseHelper _analiseHelper = new analiseHelper(trocas, comparacoes);
		return _analiseHelper;
	}
}
