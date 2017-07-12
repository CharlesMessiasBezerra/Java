package Algoritmos;

import AnaliseComplexibilidade.analiseHelper;

public class HeapSort {

	private static long trocas = 0;
	private static long comparacoes =0;	
	
    private static int total;

    private static void swap(Comparable[] arr, int a, int b)
    {
    	trocas +=1;
        Comparable tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static void heapify(Comparable[] arr, int i)
    {
        int lft = i * 2;
        int rgt = lft + 1;
        int grt = i;

        comparacoes +=1;
        if (lft <= total && arr[lft].compareTo(arr[grt]) > 0) grt = lft;
        comparacoes +=1;
        if (rgt <= total && arr[rgt].compareTo(arr[grt]) > 0) grt = rgt;
        
        if (grt != i) {
            swap(arr, i, grt);
            heapify(arr, grt);
        }
    }

    public  analiseHelper exec(Comparable[] arr)
    {
    	trocas = 0;
    	comparacoes =0;
    	
        total = arr.length - 1;

        for (int i = total / 2; i >= 0; i--)
            heapify(arr, i);

        for (int i = total; i > 0; i--) {
            swap(arr, 0, i);
            total--;
            heapify(arr, 0);
        }
        analiseHelper _analiseHelper = new analiseHelper(trocas, comparacoes);
		return _analiseHelper;
    }
}