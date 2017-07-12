package algoritmos;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import tga.graph.Aresta;
import tga.graph.Grafo;
import tga.graph.Vertice;

public class Complemento {

	// Variaveis
	private Grafo grafo;
	public Hashtable<Vertice, List<Vertice>> MapVerticesArrestas = new Hashtable<Vertice, List<Vertice>>();

	// Constructor
	public Complemento(Grafo _grafo) {
		grafo = _grafo;
	}

	/**
	 * Método <b> verifica</b> faz um mapeamento dos vertices e arestas
	 * 
	 * @author Charles / Richars
	 * @return void
	 * @version 1.0
	 */
	public void verifica() {

		List<Vertice> lsVertice = grafo.getVertices();
		List<Aresta> lsAresta = grafo.getArestas();

		for (Vertice verticeOrigen : lsVertice) {
			List<Vertice> lstVerticeExistente = new ArrayList<>();

			for (Aresta aresta : lsAresta) {
				if (verticeOrigen.equals(aresta.getFonte())) {

					lstVerticeExistente.add(aresta.getDestino());
				} else if (verticeOrigen.equals(aresta.getDestino())) {

					lstVerticeExistente.add(aresta.getFonte());
				}
			}
			// gerando complemento
			List<Vertice> vt1 = getLstVeticeComplemento(verticeOrigen, lstVerticeExistente);
			MapVerticesArrestas.put(verticeOrigen, vt1);
		}
	}

	/**
	 * Método <b> getLstVeticeComplemento</b> cria um lista de vertices que são
	 * o complemento do vertice original
	 * 
	 * @param Vertice
	 *            , List<Aresta>
	 * @author Charles / Richars
	 * @return void
	 * @version 1.0
	 */
	public List<Vertice> getLstVeticeComplemento(Vertice Origem, List<Vertice> lstVerticeExistente) {
		List<Vertice> LstVeticeComplemento = new ArrayList<>();
		List<Vertice> lstvertice = grafo.getVertices();
		boolean existe = false;

		for (Vertice lsttodosvertice : lstvertice) {
			existe = false;
			if (!(Origem.equals(lsttodosvertice))) {
				for (Vertice VerticeExistente : lstVerticeExistente) {

					if (lsttodosvertice.equals(VerticeExistente)) {
						existe = true;
					}
				}
				if (!existe) {
					LstVeticeComplemento.add(lsttodosvertice);
				}

			}

		}

		if (LstVeticeComplemento == null || LstVeticeComplemento.size() <= 0) {
			return null;
		}
		return LstVeticeComplemento;

	}

	/**
	 * Método <b> RetornaListVertice</b> metodo feito para retornar lista de
	 * vertices
	 * 
	 * @param Vertice
	 *            , List<Aresta>
	 * @author Charles / Richars
	 * @return List<Vertice>
	 * @version 1.0
	 */
	public List<Vertice> RetornaListVertice(Vertice v) {
		return MapVerticesArrestas.get(v);
	}

	/**
	 * Método <b> removeVeticeDuplicado</b> metodo feito remover vertice
	 * duplicado
	 * 
	 * @author Charles / Richars
	 * @return void
	 * @version 1.0
	 */
	public void removeVeticeDuplicado() {

		List<Vertice> lsVertice = grafo.getVertices();

		for (Vertice verticeOrigem : lsVertice) {

			List<Vertice> AlsVert = MapVerticesArrestas.get(verticeOrigem);

			for (Vertice vt : AlsVert) {
				List<Vertice> Novo = new ArrayList<>();

				List<Vertice> BlsV = MapVerticesArrestas.get(vt);

				for (Vertice vertice : BlsV) {
					if (!(verticeOrigem.equals(vertice))) {
						Novo.add(vertice);
					}
				}
				MapVerticesArrestas.remove(vt);
				MapVerticesArrestas.put(vt, Novo);
			}
		}
	}

}