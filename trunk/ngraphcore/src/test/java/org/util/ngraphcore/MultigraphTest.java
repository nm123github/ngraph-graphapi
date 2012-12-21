package org.util.ngraphcore;

import org.util.ngraphcore.api.DirectedGraph;
import org.util.ngraphcore.api.UnDirectedGraph;
import org.util.ngraphcore.graph.impl.MultiDigraph;
import org.util.ngraphcore.graph.impl.Multigraph;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MultigraphTest extends TestCase {
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MultigraphTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MultigraphTest.class );
    }
    
    public void multipleEdges() {
    	DirectedGraph<Integer> multidigraph=new MultiDigraph<Integer>();
    	multidigraph.addNode(1);
    	multidigraph.addNode(2);
    	multidigraph.addEdge(1, 1, "label");
    	multidigraph.addEdge(1, 1, "label");
    	multidigraph.addEdge(1, 1, "label");
    	multidigraph.addEdge(1, 2, "label");
    	multidigraph.addEdge(1, 2, "label");
    	multidigraph.addEdge(1, 2, "label");
    	multidigraph.addEdge(1, 2, "label");
    	multidigraph.addEdge(1, 2, "label");
    	
    	UnDirectedGraph<Integer> multigraph=new Multigraph<Integer>(true);
    	multigraph.addNode(1);
    	multigraph.addNode(2);
    	multigraph.addEdge(1, 1, "label");
    	try {
    		multigraph.addEdge(1, 1, "label");
    		Assert.fail();
    	} catch ( Exception e ) {}
    	multigraph.addEdge(1, 2, "label");
    	multigraph.addEdge(1, 2, "label");
    }
    
    public void testCycles() {
    	
    	UnDirectedGraph<Integer> multigraph=new Multigraph<Integer>(false);
    	multigraph.addNode(1);
    	multigraph.addNode(2);
    	multigraph.addEdge(1, 2, "label");
    	try {
    		// loops not allowed!
    		multigraph.addEdge(1, 1, "label");
    		Assert.fail();
    	} catch ( Exception e ) { }
    	
    	try {
    		// cycles not allowed!
    		multigraph.addEdge(2, 1, "label");
    		Assert.fail();
    	} catch ( Exception e ) { }
    }
	
}
