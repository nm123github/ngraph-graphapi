package org.util.ngraphcore.api;

import org.util.ngraphcore.exception.GraphException;

public abstract class AbstractEdgeManager<T> implements EdgeManager<T> {
	
	public void setWeight(Edge<T> e, double weight) {
		if ( e.getClass() == WeightedEdge.class ) {
			WeightedEdge<T> wedge = (WeightedEdge<T>) e;
			wedge.setWeight(weight);
		} else {
			throw new GraphException("Cannot set weight on a non-weighted edge!");
		}
		
	}
	
	public boolean containsEdge(Edge<T> e) {
		
		// traverse through all edges of all nodes
		if ( getEdges().contains(e) )
			return true;
		
		return false;
		
	}
	
}
