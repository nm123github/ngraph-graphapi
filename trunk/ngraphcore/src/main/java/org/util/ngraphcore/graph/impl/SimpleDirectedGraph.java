package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.EdgeType;

public class SimpleDirectedGraph<T> extends AbstractGraph<T> implements DirectedGraph<T> {
	
	//Type						Edges			Multiple Edges		Loops	
	//Directed Graph			Directed		NO					YES
	public SimpleDirectedGraph() {
		// if loops are allowed, cycles have to be allowed!
		super(EdgeType.DIRECT, -1, -1, false, true, true, false);
	}
	
	// give option to create a SimpleDirectedGraph with/without loops and cycles!
	// if loops are NOT allowed cycles may not may not be allowed!
	public SimpleDirectedGraph(boolean allowCycles, boolean loops) {
		super(EdgeType.DIRECT, -1, -1, false, allowCycles, loops, false);
	}

}
