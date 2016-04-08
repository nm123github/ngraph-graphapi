# Introduction #

A simple graph API implementation in java that gives you the ability to create various kinds of [directed](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/api/DirectedGraph.java), [undirected](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/api/UnDirectedGraph.java), [weighted graphs](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/api/WeightedGraph.java). Specific graphs that you could create are [BinaryTree](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/BinaryTree.java), [MultiDigraph](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/MultiDigraph.java), [Multigraph](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/Multigraph.java), [SimpleDirectedGraph](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/SimpleDirectedGraph.java), [SimpleDirectedWeightedGraph](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/SimpleDirectedWeightedGraph.java), [SimpleGraph](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/SimpleGraph.java), [SimpleWeightedGraph](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/SimpleWeightedGraph.java), [WeightedMultiDigraph](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/WeightedMultiDigraph.java) and [WeightedMultigraph](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphcore/src/main/java/org/util/ngraphcore/graph/impl/WeightedMultigraph.java).


# Sample Code #

**Binary Tree**

![http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/BinaryTree.png](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/BinaryTree.png)

```java

DirectedGraph<Integer> btree=new BinaryTree<Integer>();
btree.addNode(1);
btree.addNode(2);
btree.addNode(3);
btree.addNode(4);
Edge<Integer> edge12 = btree.addEdge(1, 2, "label");
Edge<Integer> edge13 = btree.addEdge(1, 3, "label");
Edge<Integer> edge24 = btree.addEdge(2, 4, "label");
```

**MultiDiGraph**

![http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/MultiDigraph.png](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/MultiDigraph.png)

```java

DirectedGraph<Integer> multidigraph=new MultiDigraph<Integer>();
multidigraph.addNode(1);
multidigraph.addNode(2);
multidigraph.addNode(3);
multidigraph.addNode(4);
Edge<Integer> edge11_1 = multidigraph.addEdge(1, 1, "label");
Edge<Integer> edge12_1 = multidigraph.addEdge(1, 2, "label");
Edge<Integer> edge13_1 = multidigraph.addEdge(1, 3, "label");
Edge<Integer> edge24_1 = multidigraph.addEdge(2, 4, "label");
Edge<Integer> edge24_2 = multidigraph.addEdge(2, 4, "label");
Edge<Integer> edge24_3 = multidigraph.addEdge(2, 4, "label");
```

**MultiGraph**

![http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/Multigraph.png](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/Multigraph.png)

```java

UnDirectedGraph<Integer> multigraph=new Multigraph<Integer>(true);
multigraph.addNode(1);
multigraph.addNode(2);
multigraph.addNode(3);
multigraph.addNode(4);
Edge<Integer> edge12_1 = multigraph.addEdge(1, 2, "label");
Edge<Integer> edge13_1 = multigraph.addEdge(1, 3, "label");
Edge<Integer> edge24_1 = multigraph.addEdge(2, 4, "label");
Edge<Integer> edge24_2 = multigraph.addEdge(2, 4, "label");
Edge<Integer> edge24_3 = multigraph.addEdge(2, 4, "label");
```

**SimpleDirectedGraph**

![http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/SimpleDirectedGraph.png](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/SimpleDirectedGraph.png)

```java

DirectedGraph<Integer> simpledgraph=new SimpleDirectedGraph<Integer>();
simpledgraph.addNode(1);
simpledgraph.addNode(2);
simpledgraph.addNode(3);
simpledgraph.addNode(4);
Edge<Integer> edge11 = simpledgraph.addEdge(1, 1, "label");
Edge<Integer> edge12 = simpledgraph.addEdge(1, 2, "label");
Edge<Integer> edge13 = simpledgraph.addEdge(1, 3, "label");
Edge<Integer> edge24 = simpledgraph.addEdge(2, 4, "label");
```

**SimpleWeightedGraph**

![http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/SimpleWeightedGraph.png](http://ngraph-graphapi.googlecode.com/svn/trunk/ngraphpics/SimpleWeightedGraph.png)

```java

WeightedGraph<Integer> simplewgraph=new SimpleWeightedGraph<Integer>(true);
simplewgraph.addNode(1);
simplewgraph.addNode(2);
simplewgraph.addNode(3);
simplewgraph.addNode(4);
Edge<Integer> edge12 = simplewgraph.addEdge(1, 2, "label");
Edge<Integer> edge13 = simplewgraph.addEdge(1, 3, "label");
Edge<Integer> edge24 = simplewgraph.addEdge(2, 4, "label");
Edge<Integer> edge34 = simplewgraph.addEdge(3, 4, "label");
simplewgraph.setWeight(edge12, 3.1);
simplewgraph.setWeight(edge13, -10.1);
simplewgraph.setWeight(edge24, 4.0);
simplewgraph.setWeight(edge34, 5.0);
```