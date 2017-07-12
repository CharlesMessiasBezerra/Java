package Algoritmos;

import AnaliseComplexibilidade.analiseHelper;

public class InsertionSort {

	public analiseHelper exec(Integer[] array){
		
		long trocas = 0;
		long comparacoes = 0;
		
	    int temp;
	    for (int i = 1; i < array.length; i++) {
	    	comparacoes++;
		    for(int j = i ; j > 0 ; j--){
		    	comparacoes++;
			    if(array[j] < array[j-1]){
			    	trocas++;   	
				    temp = array[j];
				    array[j] = array[j-1];
				    array[j-1] = temp;
			    }
		    }
	    }
		analiseHelper _analiseHelper = new analiseHelper(trocas, comparacoes);
		return _analiseHelper;
	}
	
}
