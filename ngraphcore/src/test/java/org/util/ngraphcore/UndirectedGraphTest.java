package org.util.ngraphcore;

import java.util.Collection;

import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.Edge;
import org.util.ngraphcore.api.EdgeManager;
import org.util.ngraphcore.api.UnDirectedGraph;
import org.util.ngraphcore.api.WeightedGraph;
import org.util.ngraphcore.edgemanager.impl.DirectedEdgeManager;
import org.util.ngraphcore.exception.GraphException;
import org.util.ngraphcore.graph.impl.BinaryTree;
import org.util.ngraphcore.graph.impl.MultiDigraph;
import org.util.ngraphcore.graph.impl.SimpleDirectedGraph;
import org.util.ngraphcore.graph.impl.SimpleDirectedWeightedGraph;
import org.util.ngraphcore.graph.impl.SimpleGraph;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UndirectedGraphTest extends TestCase {
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UndirectedGraphTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( UndirectedGraphTest.class );
    }
    
    public void testCycles() {	
    	
    	// Allow cycles!
    	UnDirectedGraph<Integer> sgraph=new SimpleGraph<Integer>();
    	sgraph.addNode(1);
    	sgraph.addNode(2);
    	sgraph.addNode(3);
    	sgraph.addNode(4);
    	
    	sgraph.addEdge(1, 2, "label");
    	sgraph.addEdge(2, 3, "label");
    	sgraph.addEdge(3, 4, "label");
    	sgraph.addEdge(4, 1, "label");
    	
    	// dont allow cycles!
    	sgraph=new SimpleGraph<Integer>(false);
    	sgraph.addNode(1);
    	sgraph.addNode(2);
    	sgraph.addNode(3);
    	sgraph.addNode(4);
    	sgraph.addEdge(1, 2, "label");
    	sgraph.addEdge(2, 3, "label");
    	sgraph.addEdge(3, 4, "label");
    	try {
    		sgraph.addEdge(1, 4, "label");
    		Assert.fail();
    	} catch ( Exception e) { }
    }
    
    
    public void testMaxMinIncomingOutgoingEdges() {
    	
    	DirectedGraph<Integer> btree=new BinaryTree<Integer>();
    	btree.addNode(1);
    	btree.addNode(2);
    	btree.addNode(3);
    	btree.addNode(4);
    	
    	Edge<Integer> edge12 = btree.addEdge(1, 2, "label");
    	Edge<Integer> edge13 = btree.addEdge(1, 3, "label");
    	try {
    		Edge<Integer> edge14 = btree.addEdge(1, 4, "label");
    		Assert.fail();
    	} catch ( GraphException e) { }
    	
    	try {
    		Edge<Integer> edge23 = btree.addEdge(2, 3, "label");
    		Assert.fail();
    	} catch ( GraphException e) { }
    }
    
    public void testWeightedEdge()
    {
    	WeightedGraph<Integer> wgraph=new SimpleDirectedWeightedGraph<Integer>();
    	wgraph.addNode(1);
    	wgraph.addNode(2);
    	
    	Edge<Integer> edge12 = wgraph.addEdge(1, 2, "label");
    	wgraph.setWeight(edge12, 1.0);
    	
    	wgraph=new SimpleDirectedGraph<Integer>();
    	wgraph.addNode(1);
    	wgraph.addNode(2);
       	Edge<Integer> edge21 = wgraph.addEdge(2, 1, "label");
    	try {
    		wgraph.setWeight(edge21, 5.0);
    		Assert.fail();
    	} catch ( GraphException e) {
    		
    	}
    }
    
    public void testMultipleEdges() {
    	
    	DirectedGraph<Integer> dgraph=new SimpleDirectedGraph<Integer>();
    	dgraph.addNode(1);
    	dgraph.addNode(2);
    	Edge<Integer> edge12 = dgraph.addEdge(1, 2, "label");
    	try {    		
    		Edge<Integer> edge122 = dgraph.addEdge(1, 2, "label");
    		Assert.fail();
    	} catch ( GraphException e ) {
    		
    	}
    	
    	dgraph = new MultiDigraph<Integer>();
    	dgraph.addNode(1);
    	dgraph.addNode(2);
    	edge12 = dgraph.addEdge(1, 2, "label");
    	Edge<Integer> edge122 = dgraph.addEdge(1, 2, "label");
    	Edge<Integer> edge1222 = dgraph.addEdge(1, 2, "label");
    	
    	Collection<Edge<Integer>> edges = dgraph.getAdjacentEdges(1);
    	Assert.assertTrue(edges.size()==3);
    	edges = dgraph.getOutgoingEdges(1);
    	Assert.assertTrue(edges.size()==3);
    	edges = dgraph.getIncomingEdges(1);
    	Assert.assertTrue(edges.size()==0);
    	
    	dgraph.removeEdge(edge12);
    	dgraph.removeEdge(edge122);
    	dgraph.removeEdge(edge1222);
    	
    	edges = dgraph.getAdjacentEdges(1);
    	Assert.assertTrue(edges.size()==0);
    	edges = dgraph.getOutgoingEdges(1);
    	Assert.assertTrue(edges.size()==0);
    	edges = dgraph.getIncomingEdges(1);
    	Assert.assertTrue(edges.size()==0);
    	
    }

    public void testAddRemoveEdge()
    {
    	DirectedGraph<Integer> dgraph=new SimpleDirectedGraph<Integer>();
    	dgraph.addNode(0);
    	dgraph.addNode(1);
    	dgraph.addNode(2);
    	dgraph.addNode(3);
    	dgraph.addNode(4);
    	Edge<Integer> edge12 = dgraph.addEdge(1, 2, "label");
    	Edge<Integer> edge13 = dgraph.addEdge(1, 3, "label");
    	Edge<Integer> edge14 = dgraph.addEdge(1, 4, "label");
    	Edge<Integer> edge01 = dgraph.addEdge(0, 1, "label");
    	Edge<Integer> edge41 = dgraph.addEdge(4, 1, "label");
    	
    	Collection<Edge<Integer>> edges = dgraph.getAdjacentEdges(1);
    	Assert.assertTrue(edges.size()==5);
    	edges = dgraph.getIncomingEdges(1);
    	Assert.assertTrue(edges.size()==2);   	
    	edges = dgraph.getOutgoingEdges(1);
    	Assert.assertTrue(edges.size()==3);
    	
    	dgraph.removeEdge(edge12);
    	dgraph.removeEdge(edge01);
    	
    	edges = dgraph.getAdjacentEdges(1);
    	Assert.assertTrue(edges.size()==3);
    	edges = dgraph.getIncomingEdges(1);
    	Assert.assertTrue(edges.size()==1);    	
    	edges = dgraph.getOutgoingEdges(1);
    	Assert.assertTrue(edges.size()==2);
    	
    	dgraph.removeEdge(edge13);
    	dgraph.removeEdge(edge14);
    	dgraph.removeEdge(edge41);
    	
    	edges = dgraph.getAdjacentEdges(1);
    	Assert.assertTrue(edges.size()==0);
    	edges = dgraph.getIncomingEdges(1);
    	Assert.assertTrue(edges.size()==0);    	
    	edges = dgraph.getOutgoingEdges(1);
    	Assert.assertTrue(edges.size()==0);
    	
    }
    
}
