package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.EdgeType;
import org.util.ngraphcore.api.UnDirectedGraph;
import org.util.ngraphcore.api.WeightedGraph;

public class WeightedMultigraph<T> extends AbstractGraph<T> implements UnDirectedGraph<T>, WeightedGraph<T> {

	//Type						Edges			Multiple Edges		Loops
	//Multigraph				Undirected		YES					NO
	public WeightedMultigraph() {
		super(EdgeType.UNDIRECTED, -1, -1, true, true, false, true);
	}
	
	public WeightedMultigraph(boolean allowCycles) {
		super(EdgeType.UNDIRECTED, -1, -1, true, allowCycles, false, true);
	}

}
