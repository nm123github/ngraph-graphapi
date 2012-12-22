package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.EdgeType;
import org.util.ngraphcore.api.WeightedGraph;

public class WeightedMultiDigraph<T> extends AbstractGraph<T> implements DirectedGraph<T>, WeightedGraph<T> {
	
	//Type						Edges			Multiple Edges		Loops
	//Directed Multigraph		Directed		YES					YES
	public WeightedMultiDigraph() {
		// if loops are allowed, cycles have to be allowed!
		super(EdgeType.DIRECT, -1, -1, true, true, true, true);
	}

}
