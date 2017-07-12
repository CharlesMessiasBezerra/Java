package tga.graph;

public class Aresta {

	private String rotulo;

	private Vertice fonte;

	private Vertice destino;

	private int cor;

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public Vertice getFonte() {
		return fonte;
	}

	public void setFonte(Vertice fonte) {
		this.fonte = fonte;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[").append(rotulo).append(", (");
		str.append(fonte.getRotulo()).append(',');
		str.append(destino.getRotulo()).append(")]");
		return str.toString();
	}

}
