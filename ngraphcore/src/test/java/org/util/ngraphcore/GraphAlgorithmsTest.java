package org.util.ngraphcore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.util.ngraphcore.algorithms.GraphAlgorithms;
import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.Edge;
import org.util.ngraphcore.api.UnDirectedGraph;
import org.util.ngraphcore.graph.impl.BinaryTree;
import org.util.ngraphcore.graph.impl.SimpleDirectedGraph;
import org.util.ngraphcore.graph.impl.SimpleGraph;
import org.util.ngraphcore.iterator.DepthFirstIterator;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GraphAlgorithmsTest extends TestCase {
	
	/**
	 * See how crucial ordering of edges is!
	 * You wont be able to test DFS if we use data structures that don't have ordering.
	 */
	public void testDFSWithUnDirectedGraph() {
		UnDirectedGraph<Integer> simpleg=new SimpleGraph<Integer>();
    	simpleg.addNode(1);
    	simpleg.addNode(2);
    	simpleg.addNode(3);
    	simpleg.addNode(4);
    	simpleg.addNode(5);
    	Edge<Integer> edge12 = simpleg.addEdge(1, 2, "");
    	Edge<Integer> edge13 = simpleg.addEdge(1, 3, "");
    	Edge<Integer> edge32 = simpleg.addEdge(3, 2, "");
    	Edge<Integer> edge24 = simpleg.addEdge(2, 4, "");
    	Edge<Integer> edge15 = simpleg.addEdge(1, 5, "");
    	
    	List<Integer> dfsAns=new ArrayList<Integer>();
    	dfsAns.add(1);
    	dfsAns.add(2);
    	dfsAns.add(3);
    	dfsAns.add(4);
    	dfsAns.add(5);
    	Iterator<Integer> dfs = new DepthFirstIterator<Integer>(simpleg, 1);
    	Iterator<Integer> dfsAnsIterator = dfsAns.iterator();
    	while ( dfs.hasNext() && dfsAnsIterator.hasNext()  ) {
    		Integer curr = dfs.next();
    		Integer currAns = dfsAnsIterator.next();
    		Assert.assertEquals(curr, currAns);
    		//System.out.print(curr + " ");
    	}
    	Assert.assertEquals(dfs.hasNext(), dfsAnsIterator.hasNext());
    	//System.out.println("");
    	
    	dfsAns=new ArrayList<Integer>();
    	dfsAns.add(1);
    	dfsAns.add(2);
    	dfsAns.add(3);
    	dfsAns.add(4);
    	dfsAns.add(5);
    	Edge<Integer> edge45 = simpleg.addEdge(4, 5, "");
    	dfs = new DepthFirstIterator<Integer>(simpleg, 1);
    	dfsAnsIterator = dfsAns.iterator();
    	while ( dfs.hasNext() && dfsAnsIterator.hasNext()  ) {
    		Integer curr = dfs.next();
    		Integer currAns = dfsAnsIterator.next();
    		Assert.assertEquals(curr, currAns);
    		//System.out.print(curr + " ");
    	}
    	Assert.assertEquals(dfs.hasNext(), dfsAnsIterator.hasNext());
    	//System.out.println("");
    	
    	dfsAns=new ArrayList<Integer>();
    	dfsAns.add(5);
    	dfsAns.add(1);
    	dfsAns.add(2);
    	dfsAns.add(3);
    	dfsAns.add(4);
    	dfs = new DepthFirstIterator<Integer>(simpleg, 5);
    	dfsAnsIterator = dfsAns.iterator();
    	while ( dfs.hasNext() && dfsAnsIterator.hasNext()  ) {
    		Integer curr = dfs.next();
    		Integer currAns = dfsAnsIterator.next();
    		Assert.assertEquals(curr, currAns);
    		//System.out.print(curr + " ");
    	}
    	Assert.assertEquals(dfs.hasNext(), dfsAnsIterator.hasNext());
    	//System.out.println("");
	}
	
	/**
	 * See how crucial ordering of edges is!
	 * You wont be able to test DFS if we use data structures that don't have ordering.
	 */
	public void testDFSWithDirectedGraph() {
		DirectedGraph<Integer> simpleg=new SimpleDirectedGraph<Integer>();
    	simpleg.addNode(1);
    	simpleg.addNode(2);
    	simpleg.addNode(3);
    	simpleg.addNode(4);
    	simpleg.addNode(5);
    	Edge<Integer> edge12 = simpleg.addEdge(1, 2, "");
    	Edge<Integer> edge13 = simpleg.addEdge(1, 3, "");
    	Edge<Integer> edge32 = simpleg.addEdge(3, 2, "");
    	Edge<Integer> edge24 = simpleg.addEdge(2, 4, "");
    	Edge<Integer> edge15 = simpleg.addEdge(1, 5, "");
    
    	List<Integer> dfsAns=new ArrayList<Integer>();
    	dfsAns.add(1);
    	dfsAns.add(2);
    	dfsAns.add(4);
    	dfsAns.add(3);
    	dfsAns.add(5);
    	Iterator<Integer> dfs = new DepthFirstIterator<Integer>(simpleg, 1);
    	Iterator<Integer> dfsAnsIterator = dfsAns.iterator();
    	while ( dfs.hasNext() && dfsAnsIterator.hasNext()  ) {
    		Integer curr = dfs.next();
    		Integer currAns = dfsAnsIterator.next();
    		Assert.assertEquals(curr, currAns);
    		//System.out.print(curr + " ");
    	}
    	Assert.assertEquals(dfs.hasNext(), dfsAnsIterator.hasNext());
    	//System.out.println("");
    	
    	dfsAns=new ArrayList<Integer>();
    	dfsAns.add(1);
    	dfsAns.add(2);
    	dfsAns.add(4);
    	dfsAns.add(5);
    	dfsAns.add(3);
    	Edge<Integer> edge45 = simpleg.addEdge(4, 5, "");
    	dfs = new DepthFirstIterator<Integer>(simpleg, 1);
    	dfsAnsIterator = dfsAns.iterator();
    	while ( dfs.hasNext() && dfsAnsIterator.hasNext()  ) {
    		Integer curr = dfs.next();
    		Integer currAns = dfsAnsIterator.next();
    		Assert.assertEquals(curr, currAns);
    		//System.out.print(curr + " ");
    	}
    	Assert.assertEquals(dfs.hasNext(), dfsAnsIterator.hasNext());
    	//System.out.println("");
    	
    	dfsAns=new ArrayList<Integer>();
    	dfsAns.add(5);
    	dfs = new DepthFirstIterator<Integer>(simpleg, 5);
    	dfsAnsIterator = dfsAns.iterator();
    	while ( dfs.hasNext() && dfsAnsIterator.hasNext()  ) {
    		Integer curr = dfs.next();
    		Integer currAns = dfsAnsIterator.next();
    		Assert.assertEquals(curr, currAns);
    		//System.out.print(curr + " ");
    	}
    	Assert.assertEquals(dfs.hasNext(), dfsAnsIterator.hasNext());
    	//System.out.println("");
	}
	
	
}
