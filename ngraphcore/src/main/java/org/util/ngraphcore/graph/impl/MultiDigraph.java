package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.EdgeType;

public class MultiDigraph<T> extends AbstractGraph<T> implements DirectedGraph<T> {
	
	//Type						Edges			Multiple Edges		Loops
	//Directed Multigraph		Directed		YES					YES
	public MultiDigraph() {
		// if loops are allowed, cycles have to be allowed!
		super(EdgeType.DIRECT, -1, -1, true, true, true, false);
	}

}
