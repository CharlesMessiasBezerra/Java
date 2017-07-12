package algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tga.graph.Aresta;
import tga.graph.Grafo;
import tga.graph.Vertice;

public class ComponentesConexas {

	private static Grafo grafo;
	public static Queue<Vertice> FilaProximo = new LinkedList<>();
	public static List<Aresta> _lstAresta = new ArrayList<>();
	public static Vertice verticeProximo = new Vertice();

	// Constructor
	public ComponentesConexas(Grafo _grafo) {
		grafo = _grafo;
	}

	// public Methods
	/**
	 * Método <b> geraComponentesConexas</b> responsavel por gerar uma lista de
	 * aresta conexa diferenciando pela cor
	 * 
	 * @author Charles / Richars
	 * @return void
	 * @version 1.0
	 */
	public void geraComponentesConexas() {

		identficaComponentesConexas();
	}

	// public Methods
	/**
	 * Método <b> identficaComponentesConexas</b> responsavel por identficar as
	 * componentes conexas
	 * 
	 * @author Charles / Richars
	 * @return void
	 * @version 1.0
	 */
	private void identficaComponentesConexas() {
		int i = 0;

		for (Vertice v : grafo.getVertices()) {

			if (!(v.getsFoiVisitado())) {

				FilaProximo.add(v);

				while (FilaProximo.size() > 0) {
					verticeProximo = FilaProximo.remove();
					for (Aresta aresta : grafo.getArestas()) {

						if (aresta.getFonte().equals(verticeProximo)) {
							if (!(aresta.getDestino().getsFoiVisitado())) {
								verticeProximo.setFoiVisitado(true);
								FilaProximo.add(aresta.getDestino());
								aresta.setCor(i);
								if (!_lstAresta.contains(aresta)) {
									_lstAresta.add(aresta);
								}

							}

						}
						if (aresta.getDestino().equals(verticeProximo)) {
							if (!(aresta.getFonte().getsFoiVisitado())) {
								FilaProximo.add(aresta.getFonte());
								verticeProximo.setFoiVisitado(true);
								aresta.setCor(i);
								if (!_lstAresta.contains(aresta)) {
									_lstAresta.add(aresta);
								}
							}
						}
					}
				}
				i++;
			}
		}
	}
}