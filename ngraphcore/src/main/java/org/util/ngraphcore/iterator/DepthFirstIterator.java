package org.util.ngraphcore.iterator;

import java.util.Collection;
import java.util.Iterator;

import org.util.ngraphcore.algorithms.GraphAlgorithms;
import org.util.ngraphcore.api.Graph;

public class DepthFirstIterator<T> implements Iterator<T> {
	
	Graph<T> graph;
	T start;
	Collection<T> dfs;
	Iterator<T> dfsIterator;
	
	public DepthFirstIterator(Graph<T> graph, T start) {
		this.graph = graph;
		this.start = start;
		this.dfs = GraphAlgorithms.DepthFirstTraversal(graph, start);
		this.dfsIterator = dfs.iterator();
	}
	
	public int size() {
		return dfs.size();
	}
	
	public boolean hasNext() {
		return dfsIterator.hasNext();
	}

	public T next() {
		return dfsIterator.next();
	}

	public void remove() {
		dfsIterator.remove();
	}
	
}
