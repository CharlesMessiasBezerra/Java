package Algoritmos;

import java.awt.HeadlessException;

import AnaliseComplexibilidade.analiseHelper;

public class SelectionSort {

	long trocas ;
	long comparacoes;
	
	public analiseHelper exec(Integer[] vetor) {

		 trocas = 0;
		 comparacoes = 0;
				
		for (int i = 0; i < vetor.length - 1; i++) {
			comparacoes ++ ;
			int index = i;
			for (int j = i + 1; j < vetor.length; j++){
				comparacoes ++ ;
				if (vetor[j] < vetor[index]){
					index = j;
					trocas += 1;
				}
			}
			int smallerNumber = vetor[index];
			vetor[index] = vetor[i];
			vetor[i] = smallerNumber;
		}
		
		return (new analiseHelper(trocas, comparacoes));
	}
}
