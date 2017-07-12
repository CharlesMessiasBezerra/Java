package algoritmos;

import java.util.ArrayList;
import java.util.List;

import tga.graph.Aresta;
import tga.graph.Grafo;
import tga.graph.Vertice;

public class Ciclo {

	private List<Aresta> lstAresta;
	private static Grafo grafo;
	public static List<Ciclo> lstCiclos = new ArrayList<Ciclo>();
	public static List<Aresta> historicoAresta = new ArrayList<Aresta>();
	public static boolean first = true;
	public static int Posicao = 0;
	public static int ciclos = 0;

	// Constructor

	public Ciclo(Grafo _grafo) {
		grafo = _grafo;
		lstAresta = new ArrayList<Aresta>();
	}

	public Ciclo(List<Aresta> sequencia) {

		lstAresta = sequencia;
	}

	// public Methods
	
	/**
	 * Método <b> setlstAresta</b> responsavel por setar uma referencia a
	 * lstAresta
	 * 
	 * @author Charles / Richars
	 * @return List<Aresta>
	 * @version 1.0
	 */
	public void setlstAresta(List<Aresta> lstAresta) {
		this.lstAresta = lstAresta;
	}

	/**
	 * Método <b> getlstAresta</b> responsavel por retornar uma lista de
	 * arrestas
	 * 
	 * @author Charles / Richars
	 * @return List<Aresta>
	 * @version 1.0
	 */
	public List<Aresta> getlstAresta() {
		return lstAresta;
	}

	/**
	 * Método <b> VerificaContem</b> verifica se lista<aresta> contem na
	 * lstAresta
	 * 
	 * @param List<Aresta>
	 * @author Charles / Richars
	 * @return booolean
	 * @version 1.0
	 */
	public boolean VerificaContem(List<Aresta> lstArestas) {
		int cont = 0;

		for (Aresta aresta : lstArestas) {
			if (lstAresta.contains(aresta))
				cont++;
		}

		if (cont == lstArestas.size() && lstArestas.size() == lstAresta.size())
			return true;

		return false;
	}

	/**
	 * Método <b> identfica</b> responsavel por verificar e gerar ciclos
	 * 
	 * 
	 * @param List<Aresta>
	 * @author Charles / Richars
	 * @return void
	 * @version 1.0
	 */
	public void geraCiclos() {
		lstCiclos = new ArrayList<Ciclo>();

		for (Vertice v : grafo.getVertices()) {

			// Algoritmo recusrivo
			identficaCiclo(v, v);

			// Diferenciar o primeiro elemento para nao confundir com um ciclo
			first = true;

			// Limpa o histórico de arestas caso seja um novo grafo
			historicoAresta = new ArrayList<Aresta>();
		}
		// zera a Posicao, dado que este sera um novo grafo
		Posicao = 0;
		ciclos = lstCiclos.size();
	}

	/**
	 * Método <b> geraCiclos</b> responsavel por verificar Ciclos
	 * 
	 * @param List<Aresta>
	 * @author Charles / Richars
	 * @return void
	 * @version 1.0
	 * 
	 */
	public static void identficaCiclo(Vertice verticeOrigem, Vertice VerticeAlvo) {

		// Verifica se o vertice origem é igual ao vertice Alvo e não é primeira
		// execução
		if (verticeOrigem == VerticeAlvo && !first) {
			GuardaCiclos();
			return;
		} else {
			first = false;

			// Para cada aresta e origem é analizada se o caminho para amesma
			// está no histórico de arestas
			for (MapVertArestas lstMapVertArestas : MapeamentoVerticesArestas(verticeOrigem, grafo.getArestas())) {
				if (!historicoAresta.contains(lstMapVertArestas.a)) {
					// Adiciona a aresta em questão
					historicoAresta.add(lstMapVertArestas.a);

					// é chamada a mesma função porém a origem sendo o vértice
					// adjacente.
					identficaCiclo(lstMapVertArestas.v, VerticeAlvo);

					// Conforme a recursão, ao voltar é removido a mesma
					historicoAresta.remove(lstMapVertArestas.a);

				}
			}
			for (Aresta aresta : historicoAresta) {
				System.out.println(aresta.toString());
			}
		}

	}

	/**
	 * Método <b> MapeamentoVerticesArestas</b> retorna uma lista mapeada de
	 * toda arestas que contem o vertice de parametro
	 * 
	 * @param Vertice , List<Aresta>
	 * @author Charles / Richars
	 * @return List<MapVertArestas>
	 * @version 1.0
	 */
	public static List<MapVertArestas> MapeamentoVerticesArestas(Vertice vet, List<Aresta> lstaArestas) {

		List<MapVertArestas> lstMapVertArestas = new ArrayList<MapVertArestas>();

		for (Aresta aresta : lstaArestas) {
			if (aresta.getFonte() == vet) {
				lstMapVertArestas.add(new MapVertArestas(aresta.getDestino(), aresta));
			} else if (aresta.getDestino() == vet) {
				lstMapVertArestas.add(new MapVertArestas(aresta.getFonte(), aresta));
			}
		}
		return lstMapVertArestas;
	}

	/**
	 * Método <b> GuardaCiclos</b> responsavel por guardar ciclos
	 * 	 
	 * @param 
	 * @author Charles / Richars
	 * @return void
	 * @version 1.0
	 */
	public static void GuardaCiclos() {

		// verifica se ciclo ja existe 
		for (Ciclo ciclo : lstCiclos) {
			if (ciclo.VerificaContem(historicoAresta)) 
				return;
		}


		List<Aresta> strCaminCiclo = new ArrayList<Aresta>();

		//guardando o caminhoa percorrer.
		for (Aresta aresta : historicoAresta) {
			for (int i = 0; i < grafo.getArestas().size(); i++) {
				if (grafo.getArestas().get(i) == aresta)
					strCaminCiclo.add(grafo.getArestas().get(i));
			}
		}

		//gerar um novo ciclo
		Ciclo c = new Ciclo(strCaminCiclo); 
		lstCiclos.add(c); 
	}

}