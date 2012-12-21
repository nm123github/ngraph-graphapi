package org.util.ngraphcore.graph.impl;

import org.util.ngraphcore.api.AbstractGraph;
import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.EdgeType;

public class BinaryTree<T> extends AbstractGraph<T> implements DirectedGraph<T> {
	
	public BinaryTree() {
		super(EdgeType.DIRECT, 2, 1, false, false, false, false);
	}

}
