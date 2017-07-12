package tga;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import tga.graph.Aresta;
import tga.graph.Grafo;
import tga.graph.Vertice;
import tga.graphml.GraphMLReader;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import algoritmos.Ciclo;
import algoritmos.Complemento;
import algoritmos.ComponentesConexas;
import algoritmos.GerarcoresAleatorias;

@SuppressWarnings("serial")
public class JGraph extends JFrame {

	// Fields
	private static JGraph frame;
	private static JMenuBar menuBar;
	private static JMenu MenuArquivos;
	private static JMenu MenuAlgoritmos;
	private static JMenuItem SubMenuAbrir;
	private static JMenuItem SubMenuFechar;
	private static JMenuItem SubMenuComplemento;
	private static JMenuItem SubMenuComponentesConexas;
	private static JMenuItem SubMenuIdentificarCiclo;
	private static String strCaminho;
	private static Grafo grafo;
	private static Map<Vertice, mxCell> mapeamento;
	private static JPanel barButtons;
	private static JGraph frameCiclo;
	static JButton btnNext;
	static JButton btnBack;
	static Component componente;

	// Constructor
	public JGraph() {

	}

	// Private Methods
	/**
	 * Método main
	 * 
	 * @author Charles / Richars
	 * @return Void
	 * @version 1.0
	 */
	public static void main(String[] args) {
		onLoadControls();
	}

	// Private Methods
	/**
	 * Método <b> onLoadControls</b> método responsavel por carregar os
	 * componentes na tela.
	 * 
	 * @author Charles / Richars
	 * @return Void
	 * @version 1.0
	 */
	private static void onLoadControls() {

		frame = new JGraph();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);

		// componente menu
		menuBar = new JMenuBar();

		// Menu Principal
		MenuArquivos = new JMenu("Arquivos");
		MenuAlgoritmos = new JMenu("Algoritmos");

		// sub Menus
		SubMenuAbrir = new JMenuItem("Abrir");
		SubMenuAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser("c");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Grafos", "graphml");

				fc.setFileFilter(filter);

				fc.setCurrentDirectory(new File(System.getProperty("user.home")));
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					strCaminho = (fc.getSelectedFile()).toString();
					frame.onLoadGrafo();
					frame.setVisible(true);
				}
			}
		});

		SubMenuFechar = new JMenuItem("Fechar");
		SubMenuFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		SubMenuComplemento = new JMenuItem("Complemento");
		SubMenuComplemento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (grafo != null) {
					Complemento algComp = new Complemento(grafo);
					algComp.verifica();
					algComp.removeVeticeDuplicado();
					JGraph frameComplemento = new JGraph();
					frameComplemento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frameComplemento.setSize(800, 600);
					frameComplemento.onLoadGrafoComplemento(algComp);
					frameComplemento.setResizable(false);
					frameComplemento.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Favor carregue um grafo antes de aplicar um algoritmo");
				}
			}
		});

		SubMenuComponentesConexas = new JMenuItem("Componentes Conexas");
		SubMenuComponentesConexas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (grafo != null) {
					ComponentesConexas algComponentesConexas = new ComponentesConexas(grafo);
					algComponentesConexas.geraComponentesConexas();
					if (ComponentesConexas._lstAresta.size() > 0) {
						JGraph _componentesConexas = new JGraph();
						_componentesConexas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						_componentesConexas.setSize(800, 600);
						_componentesConexas.onLoadGrafoComponenteConexa();
						_componentesConexas.setResizable(false);
						_componentesConexas.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Não existe componentes conexas. para esse grafo");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Favor carregue um grafo antes de aplicar um algoritmo");
				}

			}
		});

		SubMenuIdentificarCiclo = new JMenuItem("Identificar Ciclo");
		SubMenuIdentificarCiclo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (grafo != null) {
					if (grafo.getArestas().size() == 0) {
						JOptionPane.showMessageDialog(null, "Este grafo não possui arestas");
					} else {

						Ciclo algCiclo = new Ciclo(grafo);
						algCiclo.geraCiclos();
						frameCiclo = new JGraph();
						frameCiclo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frameCiclo.setSize(800, 600);
						frameCiclo.add(barButtons, BorderLayout.SOUTH);
						frameCiclo.setResizable(false);
						frameCiclo.setVisible(true);
						Ciclo.Posicao = 0;
						btnNext.setEnabled(true);
						btnBack.setEnabled(false);
						componente = onLoadGrafoCiclo(strCaminho);
						frameCiclo.add(componente);
						frameCiclo.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Favor carregue um grafo antes de aplicar um algoritmo");
				}
			}
		});

		// Buttons
		btnNext = new JButton("Next");
		btnBack = new JButton("Back");

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (Ciclo.Posicao - 1 < 0)
					return;
				// verifica se é o proximo é menor que o da primeira posicao
				if (Ciclo.Posicao - 1 == 0 && Ciclo.ciclos != 0) {
					btnNext.setEnabled(true);
					btnBack.setEnabled(false);
					Ciclo.Posicao--;
				} else if (Ciclo.ciclos != 0) {
					btnNext.setEnabled(true);
					btnBack.setEnabled(true);
					Ciclo.Posicao--;
				}

				// Trecho responsável pela atualização do frame principal (tela)
				if (componente != null)
					frameCiclo.remove(componente);
				componente = onLoadGrafoCiclo(strCaminho);
				frameCiclo.add(componente);
				frameCiclo.setVisible(true);
			}
		});

		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// verifica se o proximo ultrapassa o limite
				if (Ciclo.Posicao + 1 > Ciclo.lstCiclos.size())
					return;
				if (Ciclo.Posicao + 1 == Ciclo.lstCiclos.size() && Ciclo.ciclos != 0) {
					btnNext.setEnabled(false);
					btnBack.setEnabled(true);
					Ciclo.Posicao++;
				} else if (Ciclo.ciclos != 0) {
					btnNext.setEnabled(true);
					btnBack.setEnabled(true);
					Ciclo.Posicao++;
				}

				// atualiza o grafo na tela
				if (componente != null)
					frameCiclo.remove(componente);
				componente = onLoadGrafoCiclo(strCaminho);
				frameCiclo.add(componente);
				frameCiclo.setVisible(true);
			}
		});

		// JPanel
		barButtons = new JPanel();
		barButtons.add(btnBack);
		barButtons.add(btnNext);
		frame.setJMenuBar(menuBar);
		menuBar.add(MenuArquivos);
		menuBar.add(MenuAlgoritmos);
		MenuArquivos.add(SubMenuAbrir);
		MenuArquivos.add(SubMenuFechar);
		MenuAlgoritmos.add(SubMenuComplemento);
		MenuAlgoritmos.add(SubMenuComponentesConexas);
		MenuAlgoritmos.add(SubMenuIdentificarCiclo);
		frame.setVisible(true);
	}

	/**
	 * Método <b> onLoadGrafo</b> método responsavel por carregar o grafo
	 * inicial na tela.
	 * 
	 * @author Charles / Richars
	 * @return Void
	 * @version 1.0
	 */
	private void onLoadGrafo() {

		mxGraph graph = new mxGraph();
		graph.setDropEnabled(true);
		Object parent = graph.getDefaultParent();

		// Estilo para o vertice.
		mxStylesheet stylesheet = graph.getStylesheet();
		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "#0080FF");
		stylesheet.putCellStyle("VERTICE", style);

		// Estilo para aresta.
		style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		style.put(mxConstants.STYLE_STROKECOLOR, "#00D900");
		stylesheet.putCellStyle("ARESTA", style);

		// Estilo para arco.
		style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
		style.put(mxConstants.STYLE_STROKECOLOR, "#00D900");
		stylesheet.putCellStyle("ARCO", style);
		graph.getModel().beginUpdate();

		grafo = new GraphMLReader(strCaminho).processar();

		mapeamento = new HashMap<Vertice, mxCell>();

		try {

			for (Vertice vertice : grafo.getVertices()) {
				mxCell v = (mxCell) graph.insertVertex(parent, null, vertice.getRotulo(), vertice.getPosicao().getX(),
						vertice.getPosicao().getY(), vertice.getDimensao().getWidth(),
						vertice.getDimensao().getHeight(), "VERTICE");
				System.out.println(v.toString());
				mapeamento.put(vertice, v);
			}

			for (Aresta aresta : grafo.getArestas()) {
				mxCell e = (mxCell) graph.insertEdge(parent, null, aresta.getRotulo(),
						mapeamento.get(aresta.getFonte()), mapeamento.get(aresta.getDestino()), "ARESTA");
				System.out.println(e.toString());
			}
		} finally {
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		add(graphComponent);
	}

	/**
	 * Método <b>onLoadGrafoComplemento</b> método responsavel por carregar o
	 * grafo complemento no frame.
	 * 
	 * @author Charles / Richars
	 * @return Void
	 * @version 1.0
	 */
	private void onLoadGrafoComplemento(Complemento algComp) {

		mxGraph graph = new mxGraph();
		graph.setDropEnabled(true);
		Object parent = graph.getDefaultParent();

		// Estilo para o vertice.
		mxStylesheet stylesheet = graph.getStylesheet();
		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "#00D900");
		stylesheet.putCellStyle("VERTICE", style);

		// Estilo para aresta.
		style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		style.put(mxConstants.STYLE_STROKECOLOR, "#00D900");
		stylesheet.putCellStyle("ARESTA", style);

		// Estilo para aresta.
		style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		style.put(mxConstants.STYLE_STROKECOLOR, "#FF0000");
		style.put(mxConstants.STYLE_STROKEWIDTH,2);
		stylesheet.putCellStyle("ARESTACOMPLEMENTO", style);

		// Estilo para arco.
		style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
		style.put(mxConstants.STYLE_STROKECOLOR, "#00D900");
		stylesheet.putCellStyle("ARCO", style);
		graph.getModel().beginUpdate();

		Map<Vertice, mxCell> mapeamento2 = new HashMap<Vertice, mxCell>();

		try {

			for (Vertice vertice : grafo.getVertices()) {
				mxCell v = (mxCell) graph.insertVertex(parent, null, vertice.getRotulo(), vertice.getPosicao().getX(),
						vertice.getPosicao().getY(), vertice.getDimensao().getWidth(),
						vertice.getDimensao().getHeight(), "VERTICE");
				System.out.println(v.toString());
				mapeamento2.put(vertice, v);
			}

			List<Vertice> lstVetComplemento;
			for (Vertice verticeOrigen : grafo.getVertices()) {
				lstVetComplemento = algComp.RetornaListVertice(verticeOrigen);
				for (Vertice verticeDestino : lstVetComplemento) {
					mxCell f = (mxCell) graph.insertEdge(parent, null, null, mapeamento2.get(verticeOrigen),
							mapeamento2.get(verticeDestino), "ARESTACOMPLEMENTO");
				}
			}
		} finally {
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		add(graphComponent);
	}

	/**
	 * Método <b>onLoadGrafoComponenteConexa</b> método responsavel por carregar
	 * o grafo ComponenteConexa no frame.
	 * 
	 * @author Charles / Richars
	 * @return Void
	 * @version 1.0
	 */
	private void onLoadGrafoComponenteConexa() {

		mxGraph graph = new mxGraph();
		graph.setDropEnabled(true);
		Object parent = graph.getDefaultParent();

		// Estilo para o vertice.
		mxStylesheet stylesheet = graph.getStylesheet();
		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "#00D900");
		stylesheet.putCellStyle("VERTICE", style);

		// Estilo para aresta.
		style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		style.put(mxConstants.STYLE_STROKECOLOR, "#00D900");
		stylesheet.putCellStyle("ARESTA", style);

		// Estilo para arco.
		style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
		style.put(mxConstants.STYLE_STROKECOLOR, "#00D900");
		stylesheet.putCellStyle("ARCO", style);
		graph.getModel().beginUpdate();

		Map<Vertice, mxCell> mapeamento2 = new HashMap<Vertice, mxCell>();

		try {

			for (Vertice vertice : grafo.getVertices()) {
				mxCell v = (mxCell) graph.insertVertex(parent, null, vertice.getRotulo(), vertice.getPosicao().getX(),
						vertice.getPosicao().getY(), vertice.getDimensao().getWidth(),
						vertice.getDimensao().getHeight(), "VERTICE");
				System.out.println(v.toString());
				mapeamento2.put(vertice, v);
			}

			int anterior = -1;
			GerarcoresAleatorias cores = new GerarcoresAleatorias();
			for (Aresta aresta : ComponentesConexas._lstAresta) {

				if (aresta.getCor() != anterior) {

					String corHexa = cores.gerar();
					// Estilo para aresta.
					style = new Hashtable<String, Object>();
					style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);				
					style.put(mxConstants.STYLE_STROKECOLOR, corHexa);
					style.put(mxConstants.STYLE_STROKEWIDTH,2);
					stylesheet.putCellStyle("ARESTACOMPONENTECONEXA" + anterior, style);
				}
				anterior = aresta.getCor();

				mxCell e = (mxCell) graph.insertEdge(parent, null, aresta.getRotulo(),mapeamento2.get(aresta.getFonte()), mapeamento2.get(aresta.getDestino()),"ARESTACOMPONENTECONEXA" + anterior);
				System.out.println(e.toString());

			}

		} finally {
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		add(graphComponent);
	}

	/**
	 * Método <b>onLoadGrafoCiclo</b> método responsavel por carregar o grafo
	 * ciclo no frame.
	 * 
	 * @author Charles / Richars
	 * @return Component (retorna Component que vai ser carregado na tela)
	 * @version 1.0
	 */
	private static Component onLoadGrafoCiclo(String caminho) {
		mxGraph graph = new mxGraph();
		graph.setDropEnabled(true);
		Object parent = graph.getDefaultParent();

		mxStylesheet stylesheet = graph.getStylesheet();
		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
		stylesheet.putCellStyle("ROUNDED", style);

		style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		stylesheet.putCellStyle("ARROW", style);

		graph.getModel().beginUpdate();

		Map<Vertice, mxCell> mapeamento = new HashMap<Vertice, mxCell>();

		try {

			for (Vertice vertice : grafo.getVertices()) {
				mxCell v = (mxCell) graph.insertVertex(parent, null, vertice.getRotulo(), vertice.getPosicao().getX(),
						vertice.getPosicao().getY(), vertice.getDimensao().getWidth(),
						vertice.getDimensao().getHeight(), "ROUNDED");
				mapeamento.put(vertice, v);
			}

			for (Aresta aresta : grafo.getArestas()) {
				@SuppressWarnings("unused")
				mxCell ares;
				if (Ciclo.Posicao != 0) {

					if (Ciclo.lstCiclos.get(Ciclo.Posicao - 1).getlstAresta().contains(aresta)) {
						ares = (mxCell) graph.insertEdge(parent, "#000000", aresta.getRotulo(),
								mapeamento.get(aresta.getFonte()), mapeamento.get(aresta.getDestino()),
								"ARROW;strokeWidth=2;strokeColor=#FF0000");
					} else {

						ares = (mxCell) graph.insertEdge(parent, "#000000", aresta.getRotulo(),
								mapeamento.get(aresta.getFonte()), mapeamento.get(aresta.getDestino()),
								"ARROW;strokeWidth=1;strokeColor=#00D900");
					}

				} else {

					ares = (mxCell) graph.insertEdge(parent, "#000000", aresta.getRotulo(),
							mapeamento.get(aresta.getFonte()), mapeamento.get(aresta.getDestino()),
							"ARROW;strokeWidth=1;strokeColor=#00D900");
				}
			}

		} finally {
			graph.getModel().endUpdate();
		}

		// ajeitando para retornar ao menu inicial
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel barButtons = new JPanel();

		panel.add(graphComponent, Component.BOTTOM_ALIGNMENT);
		barButtons.add(btnBack);
		barButtons.add(btnNext);
		panel.add(barButtons, BorderLayout.SOUTH);

		return panel;

	}

}