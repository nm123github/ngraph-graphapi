package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.EdgeType;
import org.util.ngraphcore.api.UnDirectedGraph;

public class Multigraph<T> extends AbstractGraph<T> implements UnDirectedGraph<T> {

	//Type						Edges			Multiple Edges		Loops
	//Multigraph				Undirected		YES					NO
	public Multigraph() {
		super(EdgeType.UNDIRECTED, -1, -1, true, true, false, false);
	}
	
	public Multigraph(boolean allowCycles) {
		super(EdgeType.UNDIRECTED, -1, -1, true, allowCycles, false, false);
	}

}
