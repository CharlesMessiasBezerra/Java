package tga.graphml;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tga.graph.Aresta;
import tga.graph.Grafo;
import tga.graph.Vertice;

public class GraphMLReader {

	private String filePath;
	
	private static final boolean LOGAR = false;
	
	public GraphMLReader(String arquivo) {
		filePath = arquivo;
	}

	public Grafo processar() {
		Grafo grafo = new Grafo();
		File graphMLFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder dBuilder;
		
		Map<String, Vertice> mapeamento = new HashMap<String, Vertice>();
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(graphMLFile);

			Element graphml = doc.getDocumentElement();
			logarTag(graphml.getTagName());

			NodeList graphList = graphml.getElementsByTagName("graph");
			
			for (int g = 0; g < graphList.getLength(); g++) {
				
				Element elemento = (Element) graphList.item(g);
				logarTag(elemento.getNodeName());

				logarAtributo("edgedefault", elemento.getAttribute("edgedefault"));
				if ("directed".equalsIgnoreCase(elemento.getAttribute("edgedefault"))) {
					grafo.setDirecionado(false);
				}
				
				processarVertices(grafo, elemento, mapeamento);
				processarArestas(grafo, elemento, mapeamento);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return grafo;
	}

	private void processarVertices(Grafo grafo, Element graph, Map<String, Vertice> mapeamento) {
		
		Vertice vertice;
		
		NodeList nodeList = graph.getElementsByTagName("node");
		
		for (int n = 0; n < nodeList.getLength(); n++) {
			
			vertice = new Vertice();
			
			Element node = (Element) nodeList.item(n);
			logarTag(node.getNodeName());
			logarAtributo("id", node.getAttribute("id"));

			mapeamento.put(node.getAttribute("id"), vertice);
			
			NodeList dataList = node.getElementsByTagName("data");
			
			for (int d = 0; d < dataList.getLength(); d++) {
				
				Element data = (Element) dataList.item(d);
				logarTag(data.getNodeName());
				
				NodeList shapeList = data.getElementsByTagName("y:ShapeNode");
				
				for (int s = 0; s < shapeList.getLength(); s++) {

					Element shape = (Element) shapeList.item(s);
					logarTag(shape.getNodeName());

					NodeList geometryList = shape.getElementsByTagName("y:Geometry");
					
					for (int gy = 0; gy < geometryList.getLength(); gy++) {
						
						Element geometry = (Element) geometryList.item(s);
						logarTag(geometry.getNodeName());
						logarAtributo("height", geometry.getAttribute("height"));
						logarAtributo("width", geometry.getAttribute("width"));
						logarAtributo("x", geometry.getAttribute("x"));
						logarAtributo("y", geometry.getAttribute("y"));
						
						vertice.setPosicao(new Point(Float.valueOf(geometry.getAttribute("x")).intValue(), 
													 Float.valueOf(geometry.getAttribute("y")).intValue()));
						
						vertice.setDimensao(new Dimension(Float.valueOf(geometry.getAttribute("width")).intValue(), 
														  Float.valueOf(geometry.getAttribute("height")).intValue()));
					}
					
					NodeList labelList = shape.getElementsByTagName("y:NodeLabel");

					for (int ly = 0; ly < labelList.getLength(); ly++) {
						
						Element label = (Element) labelList.item(ly);
						logarTag(label.getNodeName());
						logarValorTag(label.getNodeName(), label.getTextContent());
						
						vertice.setRotulo(label.getTextContent());
					}
				}
			}
			grafo.addVertice(vertice);
		}

	}

	private void processarArestas(Grafo grafo, Element graph, Map<String, Vertice> mapeamento) {
		
		Aresta aresta;
		
		NodeList edgeList = graph.getElementsByTagName("edge");
		
		for (int e = 0; e < edgeList.getLength(); e++) {
			
			aresta = new Aresta();
			
			Element edge = (Element) edgeList.item(e);
			logarTag(edge.getNodeName());
			logarAtributo("source", edge.getAttribute("source"));
			logarAtributo("target", edge.getAttribute("target"));

			aresta.setFonte(mapeamento.get(edge.getAttribute("source")));
			aresta.setDestino(mapeamento.get(edge.getAttribute("target")));
			
			NodeList dataList = edge.getElementsByTagName("data");
			
			for (int d = 0; d < dataList.getLength(); d++) {
				
				Element data = (Element) dataList.item(d);
				logarTag(data.getNodeName());
				
				NodeList lineList = data.getElementsByTagName("y:PolyLineEdge");
				
				for (int p = 0; p < lineList.getLength(); p++) {
					
					Element line = (Element) lineList.item(p);
					logarTag(line.getNodeName());

					NodeList labelList = line.getElementsByTagName("y:EdgeLabel");
					
					for (int l = 0; l < labelList.getLength(); l++) {
						
						Element label = (Element) labelList.item(l);
						logarTag(label.getNodeName());
						logarValorTag(label.getNodeName(), label.getTextContent());
						
						aresta.setRotulo(label.getTextContent());
						
					}
				}
			}
			
			grafo.addAresta(aresta);
		}

	}
	
	private void logarTag(String nome) {
		print("tag: " + nome);
	}
	
	private void logarAtributo(String nome, String valor) {
		print(nome + ": " + valor);
	}
	
	private void logarValorTag(String nome, String valor) {
		print(nome + ": " + valor);
	}
	
	private void print(String str) {
		if (LOGAR) {
			System.out.println(str);
		}
	}
	
	public static void main(String[] args) {
		Grafo grafo = new GraphMLReader("resources/Direcionado.graphml").processar();
		System.out.print(grafo.toString());
	}

}
