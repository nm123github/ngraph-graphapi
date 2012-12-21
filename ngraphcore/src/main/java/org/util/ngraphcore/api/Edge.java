package org.util.ngraphcore.api;

public class Edge<T> {
	
	T source;
	T destination;
	String label;
	EdgeType edgeType;
	
	public Edge(T source, T destination, String label, EdgeType edgeType) {
		this.source = source;
		this.destination = destination;
		this.label = label;
		this.edgeType = edgeType;
	}
	
	public EdgeType getEdgeType() {
		return edgeType;
	}
	
	public T getSource() {
		return source;
	}
	
	public T getDestination() {
		return destination;
	}
	
	public String getLabel() {
		return label;
	}
	
}
