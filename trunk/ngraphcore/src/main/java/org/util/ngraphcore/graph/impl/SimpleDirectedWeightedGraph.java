package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.EdgeType;
import org.util.ngraphcore.api.WeightedGraph;
import org.util.ngraphcore.edgemanager.impl.DirectedEdgeManager;

public class SimpleDirectedWeightedGraph<T> extends AbstractGraph<T> implements WeightedGraph<T>, DirectedGraph<T>  {

	//Type						Edges			Multiple Edges		Loops	
	//Directed Graph			Directed		NO					YES
	public SimpleDirectedWeightedGraph() {
		// if loops are allowed, cycles have to be allowed!
		super(EdgeType.DIRECT, -1, -1, false, true, true, true);
	}

}
