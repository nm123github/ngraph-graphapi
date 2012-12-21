package org.util.ngraphcore.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.util.ngraphcore.api.Edge;
import org.util.ngraphcore.api.EdgeType;
import org.util.ngraphcore.api.Graph;
import org.util.ngraphcore.exception.GraphException;

/**
 * There is a great article on graph at
 * http://pages.cs.wisc.edu/~skrentny/cs367-common/readings/Graphs/index.html
 * @author neville m
 *
 */
public class GraphAlgorithms {
	
	/**
	 * DepthFirstTraversal of Graph g starting from node a
	 * UNVISITED and VISITED SETS
	 * DFS( Node a, VISITED (initially empty) ) {
	 * 		if ( VISITED contains a )
	 * 			return;
	 * 
     * 		add a to VISITED
     * 		for ( Node m : a.getNeighbhors()) {
     *   		DFS(m, VISITED);
     * 		}
	 * 	}
	 * 
	 * @param g
	 * @param startNode
	 * @return
	 */
	public static <T> Collection<T> DepthFirstTraversal(Graph<T> g, T startNode) {
		
		Collection<T> unvisited = g.getNodes();	// UNVISITED NODES! INITIALLY HAS ALL NODES!
		List<T> visited=new ArrayList<T>();	//VISITED NODES! INITIALLY EMPTY
		if ( !unvisited.contains(startNode) ) {
			throw new GraphException("DepthFirstTraversal failed because graph doesn't contain node " + startNode);
		}
		Stack<T> dfs=new Stack<T>(); //depth first search stack
		dfs.add(startNode);
		
		while ( dfs.size() > 0 ) {
			T curr = dfs.pop();	// POP from top of DFS stack!
			if ( !visited.contains(curr) ) { // Check if node is VISITED
				// If node is NOT VISITED
				// 1. Add node to VISITED
				// 2. Get all outgoing neighbors of this node and add them to the stack!
				visited.add(curr);	
				Object adjacentNodes[] = g.getAdjacentNodes(curr).toArray();
				for ( int i = adjacentNodes.length-1 ; i >= 0 ; i-- ) {
					dfs.push((T) adjacentNodes[i]);
				}
			}
		}
		
		return visited;
		
	}
	
	
	/**
	 * Please note that a normal DFS will NOT work for detecing cycle.
	 * You may falsely detect a cycle if you assume hitting a VISITED node means a cycle!
	 * A -> B 
	 * A -> C
	 * B -> C
	 * You may falsely detect a cycle in the above graph if you simply assume hitting a VISITED node means a cycle
	 * Start from A
	 * Visit C
	 * Visit B
	 * Visit C (C is already VISITED, we have a cycle)
	 * 
	 * What you need to do is keep track of nodes IN_PROGRESS. Only if you encounter a node that's in progress
	 * means you have a CYCLE somewhere!
	 * After the iteration on a node is over mark it DONE. If you encounter a node thats DONE you could stop traversal
	 * however that doesn't mean you have a cycle!
	 * 
	 * Not sure how we can do this without recursion!
	 * 
	 * An added complexity in our case is that we want this to work with directed and undirected graphs!
	 * A - B
	 * The above graph will be detected as a cycle if adequate steps are not taken to taken into account edges too.
	 * We will have states for edges too. We need to make sure we DONT traverse edges that are IN_PROGRESS or DONE
	 * Start from A
	 * Visit B
	 * Cannot Visit A back as edge is IN_PROGRESS
	 * 
	 * Does Graph g have a cycle starting from node a
	 * UNVISITED and IN_PROGRESS and VISITED (DONE) SETS
	 * cycle( Node a, INPROGRESS, VISITED (or DONE initially empty) ) {
	 * 		if ( VISITED contains a )
	 * 			return false;
	 * 		if ( INPROGRESS contains a )
	 * 			return true;
	 * 		
     * 		add a to INPROGRESS
     * 		for ( Node m : a.getNeighbhors()) {
     *   		DFS(m, IN_PROGRESS, VISITED);
     * 		}
     * 		remove a from INPROGRESS
     * 		add a to VISITED
	 * 	}
	 * 
	 * @param g
	 * @return
	 */
	public static <T> boolean hasCycle(Graph<T> g) {
		
		Collection<T> unvistedNodes = g.getNodes();
		for ( T currnode : unvistedNodes ) {
			if ( doesCycleStartAtNode(g, currnode, new ArrayList<T>(), new ArrayList<T>(), null) ) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Does cycle start at node a . For explaination refer to hasCycle
	 * @param g
	 * @param a
	 * @param inprogressnodes
	 * @param visitednodes
	 * @return
	 */
	public static <T> boolean doesCycleStartAtNode(Graph<T> g, T a, List<T> inprogressnodes, List<T> visitednodes, Edge<T> lastedge) {
		
		if ( visitednodes.contains(a) )
			return false;
		if ( inprogressnodes.contains(a) )
			return true;
		
		inprogressnodes.add(a);
		//Collection<T> adjacentNodes = g.getAdjacentNodes(a);
		Collection<Edge<T>> adjacentedges = g.getAdjacentEdges(a);
		for ( Edge<T> currEdge : adjacentedges ) {
			
			// you just came to this node using this edge, don't go back
			// this case would happen only in undirected edges
			if ( lastedge == currEdge ) {
				continue;
			}
			
			// in case of directed edge getSource will always be T a
			// in case of undirected edge getSource might not always ve T a
			T dest = null;			
			if ( currEdge.getEdgeType() == EdgeType.UNDIRECTED ) {
				// find destination edge even if the edge is an undirected edge!
				if ( currEdge.getSource() == a )
					dest = currEdge.getDestination();
				else if ( currEdge.getDestination() == a )
					dest = currEdge.getSource();
				else
					throw new GraphException("Invalid edge " + currEdge + " from node " + a + " doesnt have a source or destination as " + a);
			} else if ( currEdge.getEdgeType() == EdgeType.DIRECT ) {
				// make sure its outgoing else skip!
				if ( currEdge.getSource() != a  )
					continue;
				
				dest = currEdge.getDestination();				
			} else {
				throw new GraphException("Unrecognized edge type " + currEdge + " found in graph.");
			}
			
			if ( doesCycleStartAtNode(g, dest, inprogressnodes, visitednodes, currEdge) )
				return true;
		}
		
		inprogressnodes.remove(a);
		visitednodes.add(a);
		
		return false;
	}
	
}
