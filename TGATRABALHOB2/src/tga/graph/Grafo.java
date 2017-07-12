package tga.graph;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

	private boolean direcionado;
	
	private List<Vertice> vertices;
	
	private List<Aresta> arestas;

	public Grafo() {
	}
	
	public Grafo(boolean direcionado) {
		this.direcionado = direcionado;
	}
	
	public void setDirecionado(boolean direcionado) {
		this.direcionado = direcionado;
	}

	public boolean isDirecionado() {
		return direcionado;
	}

	public List<Vertice> getVertices() {
		if (vertices == null) {
			vertices = new ArrayList<Vertice>();
		}
		return vertices;
	}

	public Grafo addVertice(Vertice vertice) {
		getVertices().add(vertice);
		return this;
	}
	
	public List<Aresta> getArestas() {
		if (arestas == null) {
			arestas = new ArrayList<Aresta>();
		}
		return arestas;
	}

	public Grafo addAresta(Aresta aresta) {
		getArestas().add(aresta);
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (vertices != null) {
			for (Vertice vertice : vertices) {
				str.append(vertice.toString());	
			}
		}
		str.append('\n');
		
		if (arestas != null) {
			for (Aresta aresta : arestas) {
				str.append(aresta.toString());	
			}
		}
		return str.toString();
	}
	
}
