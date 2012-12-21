package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.EdgeType;
import org.util.ngraphcore.api.UnDirectedGraph;
import org.util.ngraphcore.api.WeightedGraph;

public class SimpleWeightedGraph<T> extends AbstractGraph<T> implements WeightedGraph<T>, UnDirectedGraph<T>  {

	//Type					Edges			Multiple Edges		Loops
	//Simple Graph			Undirected		NO					NO
	public SimpleWeightedGraph() {
		super(EdgeType.UNDIRECTED, -1, -1, false, true, false, true);
	}
	
	public SimpleWeightedGraph(boolean allowCycles) {
		super(EdgeType.UNDIRECTED, -1, -1, false, allowCycles, false, true);
	}

}
