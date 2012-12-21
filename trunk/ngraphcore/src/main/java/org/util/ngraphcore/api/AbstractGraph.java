package org.util.ngraphcore.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.util.ngraphcore.algorithms.GraphAlgorithms;
import org.util.ngraphcore.api.EdgeManager;
import org.util.ngraphcore.api.Graph;
import org.util.ngraphcore.edgemanager.impl.DirectedEdgeManager;
import org.util.ngraphcore.edgemanager.impl.UndirectedEdgeManager;
import org.util.ngraphcore.exception.GraphException;

/**
 * Good presentation on Simple Graph
 * http://student.ccbcmd.edu/~ssorkin/RosenCh7/
 * 
 * Types of graph
 * http://student.ccbcmd.edu/~ssorkin/RosenCh7/sld017.htm
 * 
 * Type						Edges			Multiple Edges		Loops
 * 
 * Simple Graph				Undirected		NO					NO
 * Multigraph				Undirected		YES					NO
 * Pseudograph				Undirected		YES					YES
 * Directed Graph			Directed		NO					YES
 * Directed Multigraph		Directed		YES					YES
 * 
 * @param <T>
 */
public abstract class AbstractGraph<T> implements Graph<T>, DirectedGraph<T>, UnDirectedGraph<T>, WeightedGraph<T> {
	
	EdgeManager<T> edgeManager;
	Set<T> nodes;	
	boolean isWeighted;
	int maxOutgoing;
	int maxIncoming;
	boolean multiple;
	boolean cycle;
	boolean loops;
	
	public AbstractGraph(EdgeType edgetype, int maxOutgoing, int maxIncoming, boolean multiple, boolean cycle, boolean loops, boolean isWeighted) {
		
		this.maxOutgoing = maxOutgoing;
		this.maxIncoming = maxIncoming;
		this.multiple = multiple;
		this.cycle = cycle;
		this.isWeighted = isWeighted;
		this.loops = loops;
		this.nodes = new LinkedHashSet<T>();

		if ( edgetype == EdgeType.DIRECT ) {		
			this.edgeManager = new DirectedEdgeManager<T>();				
		} else if ( edgetype == EdgeType.UNDIRECTED ) {			
			this.edgeManager = new UndirectedEdgeManager<T>();
		} else {			
			throw new GraphException("Cannot create edge manager. Unrecognized edge type!");		
		}
		
	}
	
	public Collection<T> getNodes() {
		return nodes;
	}
	
	public void addNode(T a) {
		if ( nodes.contains(a) )
			throw new GraphException("Graph already contains node " + a);
		nodes.add(a);
	}

	public Edge<T> addEdge(T a, T b, String label) {
		
		if ( !nodes.contains(a) )
			throw new GraphException("Node " + a + " doesnt exist");
		if ( !nodes.contains(b) )
			throw new GraphException("Node " + b + " doesnt exist");
		
		// are loops allowed!
		if ( !this.loops && a == b ) {
			throw new GraphException("This graph doesnt allow loops.");
		}			
		
		// are multiple edges being added And multiple edges not allowed?
		if ( !multiple && getEdgeManager().containsEdge(a, b) )
			throw new GraphException("There already exists an edge between " + a + " and " + b + ". Multiple edges are not allowed in this graph.");
		
		// make sure maxOutgoing restraint is not broken
		Collection<Edge<T>> outgoingNodes = getEdgeManager().getOutgoingEdges(a);
		if ( outgoingNodes.size() == maxOutgoing )
			throw new GraphException("Outgoing edges from node " + a + " are " + outgoingNodes.size() + " and max outgoing edges from any node are " + maxOutgoing);
			
		// make sure maxIncoming restraint is not broken
		Collection<Edge<T>> incomingNodes = getEdgeManager().getIncomingEdges(b);
		if ( incomingNodes.size() == maxIncoming )
			throw new GraphException("Incoming edges to node " + b + " are " + incomingNodes.size() + " and max incoming edges from any node are " + maxIncoming);
				
		Edge<T> justAddedEdge = getEdgeManager().addEdge(a, b, label, isWeighted);
		
		if ( !cycle && GraphAlgorithms.hasCycle(this) ) {
			getEdgeManager().removeEdge(justAddedEdge);
			justAddedEdge = null;
			throw new GraphException("Edge " + a + " and " + b + " will create a cycle in the graph. This graph doesnt allow cycles.");
		}
		
		return justAddedEdge;
	}
	
	public void removeNode(T a) {
		nodes.remove(a);
	}

	public void removeEdge(Edge<T> e) {
		if ( !getEdgeManager().containsEdge(e) )
			throw new GraphException("Edge " + e + " does not exist!");
		
		getEdgeManager().removeEdge(e);
	}

	public EdgeManager<T> getEdgeManager() {
		return edgeManager;
	}

	public Collection<Edge<T>> getIncomingEdges(T a) {
		return edgeManager.getIncomingEdges(a);
	}

	public Collection<Edge<T>> getOutgoingEdges(T a) {
		return edgeManager.getOutgoingEdges(a);
	}
	
	public Collection<T> getAdjacentNodes(T a) {
		Collection<T> outgoingNodes = new ArrayList<T>();
		Collection<Edge<T>> outgoingEdges = getOutgoingEdges(a);
		for ( Edge<T> outEdge : outgoingEdges ) {
			if ( outEdge.getEdgeType() == EdgeType.DIRECT ) {
				
				outgoingNodes.add(outEdge.getDestination());
				
			} else if ( outEdge.getEdgeType() == EdgeType.UNDIRECTED ) {
				
				if ( outEdge.getSource() == a ) {
					outgoingNodes.add(outEdge.getDestination());
				} else if ( outEdge.getDestination() == a ) {
					outgoingNodes.add(outEdge.getSource());
				} else {
					throw new GraphException("Invalid edge " + outEdge + " from node " + a + " doesnt have a source or destination as " + a);
				}
				
				
			} else {
				throw new GraphException("Unrecognized edge type " + outEdge + " found in graph.");
			}
		}
		return outgoingNodes;
	}
	
	public void setWeight(Edge<T> e, double weight) {
		if ( !getEdgeManager().containsEdge(e) )
			throw new GraphException("Edge " + e + " does not exist!");
		if ( !isWeighted )
			throw new GraphException("Cannot set edge weight on a non weighted graph!");
		getEdgeManager().setWeight(e, weight);
	}
	
	public Collection<Edge<T>> getAdjacentEdges(T a) {
		return getEdgeManager().getEdges(a);
	}
		
}
