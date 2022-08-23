package it.polito.tdp.genes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new GenesDao();
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiunta vertici
		Graphs.addAllVertices(this.grafo, dao.getAllLocalizations());
		
		// aggiunta archi
		for (Adiacenza a : dao.getAllAdiacenze()) {
			Graphs.addEdge(this.grafo, a.getV1(), a.getV2(), a.getPeso());
		}
		
	}
	
	public Map<String, Integer> getVerticiConnessiVerticeDato(String vertice){
		List<String> verticiConnessi = Graphs.neighborListOf(this.grafo, vertice);
		Map<String, Integer> result = new HashMap<>();
		
		for (String s : verticiConnessi) {
			result.put(s, (int)this.grafo.getEdgeWeight(this.grafo.getEdge(vertice, s)));
		}

		return result;
	}
	
	public List<String> getAllLocalizations(){
		return dao.getAllLocalizations();
	}
	
	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumArchi() {
		return this.grafo.edgeSet().size();
	}
	
}