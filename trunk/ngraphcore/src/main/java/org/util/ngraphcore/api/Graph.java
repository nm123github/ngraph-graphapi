package org.util.ngraphcore.api;

import java.util.Collection;

/**
 * Represents a graph that allows just one edge between nodes!
 * @author neville m
 *
 * @param <T>
 */
public interface Graph<T> {
	
	/**
	 * Add node to graph
	 * @param a
	 */
	public void addNode(T a);
	
	/**
	 * Add edge between a and b, weighted or non-weighted!
	 * @param a
	 * @param b
	 * @param label
	 */
	public Edge<T> addEdge(T a, T b, String label);
	
	/**
	 * Remove edge from graph!
	 */
	public void removeEdge(Edge<T> e);
	
	/**
	 * Remove node a
	 * @param a
	 */
	public void removeNode(T a);
	
	/**
	 * Get all nodes of the graph!
	 * @return
	 */
	Collection<T> getNodes();
	
	/**
	 * Get all adjacent edges to this node.
	 * Directed Graph- incoming and outgoing edges.
	 * UnDirected Graph - all adjacent edges.
	 * @param a
	 * @return
	 */
	public Collection<Edge<T>> getAdjacentEdges(T a);
	
	/**
	 * Get all adjacent nodes to this node.
	 * Directed Graph- outgoing nodes.
	 * UnDirected Graph - all adjacent nodes.
	 * @param a
	 * @return
	 */
	public Collection<T> getAdjacentNodes(T a);	
}
