package org.util.ngraphcore.api;

public class WeightedEdge<T> extends Edge<T> {

	double weight;

	public WeightedEdge(T source, T destination, 
			String label, EdgeType edgeType) {
		super(source, destination, label, edgeType);
		weight = -1.0;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
