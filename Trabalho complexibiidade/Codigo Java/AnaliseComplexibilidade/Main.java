package AnaliseComplexibilidade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Algoritmos.*;
import Vetor.TBVetor;

public class Main {

	public static void main(String[] args) {
		Integer tam = 600000;
		Integer[] vet_Aleat_vt = new Integer[tam];
		TBVetor vetor = new TBVetor();
		Integer[] vet;
		BubbleSort bbs = new BubbleSort();
		QuickSort qcks = new QuickSort();
		SelectionSort SlcSort = new SelectionSort();
		analiseHelper _analiseHelper;
		HeapSort Hps = new  HeapSort(); 
		
		int low;
		int high;

		long startTime;
		long endTime;
		long duration;

		System.out.println("Gerendo Vetor Aleatório...");	
		vetor.geraVetorAleatorio(vet_Aleat_vt, tam);
		System.out.println("**Vetor Aleatório Gerado**");
		
		System.out.println("Gerendo Vetor Ordenado...");
		Integer[] vet_Ord_vt = vet_Aleat_vt.clone();
		low = 0;
		high = vet_Ord_vt.length - 1;
		qcks = new QuickSort();
		_analiseHelper  = qcks.exec(vet_Ord_vt, low, high);
		System.out.println("**Vetor ordenado Gerado**");
		
		System.out.println("Gerendo Vetor inverso...");
		Integer[] vet_Inv_vt = vet_Ord_vt.clone();
		vetor.OrdenaVetorInverso(vet_Inv_vt);
		System.out.println("**Vetor inverso Gerado**");
		
//		System.out.println("===========================================");
//		System.out.println("             Bubble Sort ");
//		System.out.print("===========================================");
//
//		vet = vet_Aleat_vt.clone();
//		startTime = System.currentTimeMillis();
//		_analiseHelper  = bbs.exec(vet);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);		
//		System.out.print("Vetor aleatorio Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms" );
//		
//
//		vet = vet_Ord_vt.clone();
//		startTime = System.currentTimeMillis();
//		_analiseHelper  = bbs.exec(vet);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);
//		System.out.print("Vetor ordenado Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms" );
//
//		vet = vet_Inv_vt.clone();
//		startTime = System.currentTimeMillis();
//		_analiseHelper  = bbs.exec(vet);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);
//		System.out.print("Vetor inverso Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );
//
//		System.out.println("===========================================");
//		System.out.println("             Quick Sort ");
//		System.out.println("===========================================");
//		vet = vet_Aleat_vt.clone();
//		low = 0;
//		high = vet.length - 1;
//		startTime = System.currentTimeMillis();
//		qcks = new QuickSort();
//		_analiseHelper  = qcks.exec(vet, low, high);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);
//		System.out.print("Vetor aleatorio Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );
//
//		vet = vet_Ord_vt.clone();
//		low = 0;
//		high = vet.length - 1;
//		startTime = System.currentTimeMillis();
//		qcks = new QuickSort();
//		_analiseHelper  = qcks.exec(vet, low, high);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);
//		System.out.print("Vetor ordenado Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );
//
//		vet = vet_Inv_vt.clone();
//		low = 0;
//		high = vet.length - 1;
//		startTime = System.currentTimeMillis();
//		qcks = new QuickSort();
//		_analiseHelper  = qcks.exec(vet, low, high);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);
//		System.out.print("Vetor inverso Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );
//
//		System.out.println("===========================================");
//		System.out.println("             Selection Sort ");
//		System.out.println("===========================================");
//
//		vet = vet_Aleat_vt.clone();
//		startTime = System.currentTimeMillis();
//		_analiseHelper = SlcSort.exec(vet);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);
//		System.out.print("Vetor aleatorio Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );
//
//		vet = vet_Ord_vt.clone();
//		startTime = System.currentTimeMillis();
//		_analiseHelper = SlcSort.exec(vet);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);
//		System.out.print("Vetor ordenado Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );
//
//		
//		vet = vet_Inv_vt.clone();
//		startTime = System.currentTimeMillis();
//		_analiseHelper = SlcSort.exec(vet);
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime);
//		System.out.print("Vetor inverso Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );
//

		System.out.println("===========================================");
		System.out.println("             Heap Sort " + tam);
		System.out.println("===========================================");

		vet = vet_Aleat_vt.clone();
		startTime = System.currentTimeMillis();
		_analiseHelper = Hps.exec(vet);
		endTime = System.currentTimeMillis();
		duration = (endTime - startTime);
		System.out.print("Vetor aleatorio Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );

		vet = vet_Ord_vt.clone();
		startTime = System.currentTimeMillis();
		_analiseHelper = Hps.exec(vet);
		endTime = System.currentTimeMillis();
		duration = (endTime - startTime);
		System.out.print("Vetor ordenado Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );

		vet = vet_Inv_vt.clone();
		startTime = System.currentTimeMillis();
		_analiseHelper = Hps.exec(vet);
		endTime = System.currentTimeMillis();
		duration = (endTime - startTime);
		System.out.print("Vetor inverso Trocas( " + _analiseHelper.getTrocas() + " )  Comparacões( "+_analiseHelper.getComparacoes()+") " + "Tempo : " + duration + " ms\n" );


	
	}
}
