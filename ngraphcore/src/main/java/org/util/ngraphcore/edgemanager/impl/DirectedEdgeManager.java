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

public class DirectedEdgeManager<T> extends AbstractEdgeManager<T> implements EdgeManager<T> {
		
	Map<T, Collection<Edge<T>>> outgoing;
	Map<T, Collection<Edge<T>>> incoming;
		
	public DirectedEdgeManager() {
		this.outgoing = new HashMap<T, Collection<Edge<T>>>();
		this.incoming = new HashMap<T, Collection<Edge<T>>>();
	}
		
	public Edge<T> addEdge(T a, T b, String label, boolean isWeighted) {
				
		// create edge!
		Edge<T> currEdge = null;
		if ( isWeighted ) {
			currEdge=new WeightedEdge<T>(a, b, label, EdgeType.DIRECT);
		} else {
			currEdge=new Edge<T>(a, b, label, EdgeType.DIRECT);
		}
		
		// add edges
		getOutgoingEdges(a).add(currEdge);
		getIncomingEdges(b).add(currEdge);
		
		return currEdge;
		
	}
	
	public Collection<Edge<T>> getEdges() {
		// return all edges in this graph!
		Collection<Edge<T>> getedges = new ArrayList<Edge<T>>();
		
		// traverse through all outgoing edges of all nodes
		Set<T> nodes = outgoing.keySet();
		Iterator<T> nodesiterator = nodes.iterator();
		while ( nodesiterator.hasNext() ) {
			T node = nodesiterator.next();
			Collection<Edge<T>> edges = outgoing.get(node);
			getedges.addAll(edges);
		}
		
		// traverse through all incoming edges of all nodes
		nodes = incoming.keySet();
		nodesiterator = nodes.iterator();
		while ( nodesiterator.hasNext() ) {
			T node = nodesiterator.next();
			Collection<Edge<T>> edges = incoming.get(node);
			getedges.addAll(edges);
		}
		
		return getedges;
	}
	
	public void removeEdge(Edge<T> e) {
		
		// traverse through all incoming edges of all nodes
		Set<T> nodes = outgoing.keySet();
		Iterator<T> nodesiterator = nodes.iterator();
		while ( nodesiterator.hasNext() ) {
			T node = nodesiterator.next();
			Collection<Edge<T>> edges = outgoing.get(node);
			edges.remove(e);
		}
		
		// traverse through all incoming edges of all nodes
		nodes = incoming.keySet();
		nodesiterator = nodes.iterator();
		while ( nodesiterator.hasNext() ) {
			T node = nodesiterator.next();
			Collection<Edge<T>> edges = incoming.get(node);
			edges.remove(e);
		}
			
	}
	
	public Collection<Edge<T>> getOutgoingEdges(T a) {
		if ( outgoing.get(a) == null )
			outgoing.put(a, new ArrayList<Edge<T>>());
		return outgoing.get(a);
	}

	public Collection<Edge<T>> getIncomingEdges(T a) {
		if ( incoming.get(a) == null )
			incoming.put(a, new ArrayList<Edge<T>>());
		return incoming.get(a);
	}
	
	public Collection<Edge<T>> getEdges(T a) {
		// return all edges adjacent to this edge, (incoming and outgoing)!
		Collection<Edge<T>> outgoing = getOutgoingEdges(a);
		Collection<Edge<T>> incoming = getIncomingEdges(a);
		Collection<Edge<T>> getedges = new ArrayList<Edge<T>>();
		getedges.addAll(outgoing);
		getedges.addAll(incoming);
		return getedges;
	}

	public boolean containsEdge(T a, T b) {
		Collection<Edge<T>> aEdges = outgoing.get(a);
		if ( aEdges != null ) {
			Iterator<Edge<T>> aEdgesIterator = aEdges.iterator();
			while ( aEdgesIterator.hasNext() ) {
				Edge<T> curr = aEdgesIterator.next();
				if ( curr.getDestination() == b )
					return true;
			}
		}
		return false;
	}

}
