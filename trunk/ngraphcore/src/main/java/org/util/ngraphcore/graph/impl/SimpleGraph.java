package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.EdgeType;
import org.util.ngraphcore.api.UnDirectedGraph;

public class SimpleGraph<T> extends AbstractGraph<T> implements UnDirectedGraph<T> {

	//Type					Edges			Multiple Edges		Loops
	//Simple Graph			Undirected		NO					NO
	public SimpleGraph() {
		super(EdgeType.UNDIRECTED, -1, -1, false, true, false, false);
	}
	
	public SimpleGraph(boolean allowCycles) {
		super(EdgeType.UNDIRECTED, -1, -1, false, allowCycles, false, false);
	}

}
