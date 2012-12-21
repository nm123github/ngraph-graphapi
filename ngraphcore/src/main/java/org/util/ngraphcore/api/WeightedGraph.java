package org.util.ngraphcore.api;

public interface WeightedGraph<T> extends Graph<T> {
	
	public void setWeight(Edge<T> e, double weight);
		
}
