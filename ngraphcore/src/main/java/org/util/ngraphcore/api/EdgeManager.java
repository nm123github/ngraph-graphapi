package org.util.ngraphcore.api;

import java.util.Collection;

/**
 * A generic edge manager to 
 * @author apple
 *
 * @param <T>
 */
public interface EdgeManager<T> {
	
	/**
	 * Add Edge connecting T a and T b
	 * @param a
	 * @param b
	 * @param label
	 * @param edgeType
	 */
	public Edge<T> addEdge(T a, T b, String label, boolean isWeighted);
	
	/**
	 * Remove Edge e
	 */
	public void removeEdge(Edge<T> e);
	
	/**
	 * Set weight for edge
	 * @param weight
	 */
	public void setWeight(Edge<T> e, double weight);
	
	/**
	 * Get all outgoing edges from node T a
	 * @param a
	 * @return
	 */
	public Collection<Edge<T>> getOutgoingEdges(T a);
	
	/**
	 * Get all incoming edges from node T a
	 * @param a
	 * @return
	 */
	public Collection<Edge<T>> getIncomingEdges(T a);
	
	/**
	 * Get all edges adjacent to node T a
	 * In case of undirected edges, it means all edges attached to this node
	 * In case of directed edges, it means incoming and outgoing edges attached to this node
	 * @param a
	 * @return
	 */
	public Collection<Edge<T>> getEdges(T a);
	
	/**
	 * Get all edges in this graph!
	 * @return
	 */	
	public Collection<Edge<T>> getEdges();
	
	/**
	 * Is there an edge between a and b
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean containsEdge(T a, T b);
	
	/**
	 * Is there an edge e in graph!
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean containsEdge(Edge<T> e);
	
}
