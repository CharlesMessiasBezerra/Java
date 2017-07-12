package AnaliseComplexibilidade;

public class analiseHelper {

	private long trocas;
	private long comparacoes;
	
	public analiseHelper(long trocas, long comparacoes) {
		super();
		this.trocas = trocas;
		this.comparacoes = comparacoes;
	}

	public long getTrocas() {
		return trocas;
	}

	public long getComparacoes() {
		return comparacoes;
	}
	
}
