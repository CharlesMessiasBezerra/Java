package tga.graph;

import java.awt.Dimension;
import java.awt.Point;

public class Vertice {

	private String rotulo;

	private Point posicao;

	private Dimension dimensao;

	private boolean FoiVisitado;

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public Point getPosicao() {
		return posicao;
	}

	public void setPosicao(Point posicao) {
		this.posicao = posicao;
	}

	public Dimension getDimensao() {
		return dimensao;
	}

	public void setDimensao(Dimension dimensao) {
		this.dimensao = dimensao;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[").append(rotulo).append("]");
		return str.toString();
	}

	public boolean getsFoiVisitado() {
		return FoiVisitado;
	}

	public void setFoiVisitado(boolean foiVisitado) {
		FoiVisitado = foiVisitado;
	}

}
