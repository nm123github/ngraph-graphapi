package org.util.ngraphcore.edgemanager.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.util.ngraphcore.api.AbstractEdgeManager;
import org.util.ngraphcore.api.Edge;
import org.util.ngraphcore.api.EdgeManager;
import org.util.ngraphcore.api.EdgeType;
import org.util.ngraphcore.api.WeightedEdge;
import org.util.ngraphcore.exception.GraphException;

public class UndirectedEdgeManager<T> extends AbstractEdgeManager<T> implements EdgeManager<T> {
		
	Map<T, Collection<Edge<T>>> adjacentedges;
		
	public UndirectedEdgeManager() {
		this.adjacentedges = new HashMap<T, Collection<Edge<T>>>();
	}
		
	public Edge<T> addEdge(T a, T b, String label, boolean isWeighted) {
		
		// create Map entries for a and b!
		if ( adjacentedges.get(a) == null ) {
			adjacentedges.put(a, new ArrayList<Edge<T>>());
		}
		if ( adjacentedges.get(b) == null ) {
			adjacentedges.put(b, new ArrayList<Edge<T>>());
		}
		
		Edge<T> currEdge = null;
		if ( isWeighted ) {
			currEdge=new WeightedEdge<T>(a, b, label, EdgeType.UNDIRECTED);
		} else {
			currEdge=new Edge<T>(a, b, label, EdgeType.UNDIRECTED);
		}
		
		// same edge added to adjacentedges of a and b
		getEdges(a).add(currEdge);
		getEdges(b).add(currEdge);
		
		return currEdge;
		
	}

	public Collection<Edge<T>> getEdges() {
		Collection<Edge<T>> getedges = new ArrayList<Edge<T>>();
		
		// traverse through all edges of all nodes
		Set<T> nodes = adjacentedges.keySet();
		Iterator<T> nodesiterator = nodes.iterator();
		while ( nodesiterator.hasNext() ) {
			T node = nodesiterator.next();
			Collection<Edge<T>> edges = adjacentedges.get(node);
			getedges.addAll(edges);
		}
		
		return getedges;
	}
	
	public void removeEdge(Edge<T> e) {
		
		// traverse through all edges of all nodes and delete when found!
		Set<T> nodes = adjacentedges.keySet();
		Iterator<T> nodesiterator = nodes.iterator();
		while ( nodesiterator.hasNext() ) {
			T node = nodesiterator.next();
			Collection<Edge<T>> edges = adjacentedges.get(node);
			edges.remove(e);
		}
			
	}
	
	public Collection<Edge<T>> getOutgoingEdges(T a) {
		// all adjacent edges are by default incoming and outgoing!
		if ( adjacentedges.get(a) == null )
			adjacentedges.put(a, new ArrayList<Edge<T>>());
		return adjacentedges.get(a);
	}

	public Collection<Edge<T>> getIncomingEdges(T a) {
		// all adjacent edges are by default incoming and outgoing!
		if ( adjacentedges.get(a) == null )
			adjacentedges.put(a, new ArrayList<Edge<T>>());
		return adjacentedges.get(a);
	}
	
	public Collection<Edge<T>> getEdges(T a) {
		// all adjacent edges are by default incoming and outgoing!
		if ( adjacentedges.get(a) == null )
			adjacentedges.put(a, new ArrayList<Edge<T>>());
		return adjacentedges.get(a);
	}

	public boolean containsEdge(T a, T b) {
		Collection<Edge<T>> aEdges = adjacentedges.get(a);
		if ( aEdges != null ) {
			Iterator<Edge<T>> aEdgesIterator = aEdges.iterator();
			while ( aEdgesIterator.hasNext() ) {
				Edge<T> curr = aEdgesIterator.next();
				if ( ( curr.getSource() == a && curr.getDestination() == b ) || ( curr.getSource() == b && curr.getDestination() == a ) )
					return true;
			}
		}
		return false;
	}

}
