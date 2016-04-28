package mygraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;



public class MyGraph {

	private static Object mock = new Object();
	private HashMap<Integer, Node> nodes = new HashMap<Integer, Node> ();
	private HashMap<Integer,Edge> edges = new HashMap<Integer,Edge>();
	private int minNodeID = Integer.MAX_VALUE;
	private int maxNodeID = Integer.MIN_VALUE;
	private HashSet<Node> reset = new HashSet<Node>();
	
	int getMinNodeID() {
		return minNodeID;
	}
	void setMinNodeID(int minNodeID) {
		this.minNodeID = minNodeID;
	}
	int getMaxNodeID() {
		return maxNodeID;
	}
	void setMaxNodeID(int maxNodeID) {
		this.maxNodeID = maxNodeID;
	}

	HashMap<Integer, Edge> getEdges() {
		return edges;
	}
	void setEdges(HashMap<Integer, Edge> edges) {
		this.edges = edges;
	}
	
	public void addVertex(int key) {
		// TODO Auto-generated method stub
		
		if (key>this.maxNodeID)
		{
			this.maxNodeID = key;
		}
		
		if (key<this.minNodeID)
		{
			this.minNodeID = key;
		}
		Node node = new Node (key,-1, new HashSet<Node>());
		this.getNodes().put(key,node);
		
	}
	public int getNumNodes ()
	{
		return getNodes().size();
	}
	public int getNumEdges()
	{
		return edges.size();
	}
	public void addEdge(int key1, int key2) {
		// TODO Auto-generated method stub
		if (!getNodes().containsKey(key1))
		{
			this.addVertex(key1);
		}
		if (!getNodes().containsKey(key2))
		{
			this.addVertex(key2);
		}
		Node node1 = getNodes().get(key1);
		Node node2 = getNodes().get(key2);
		if (!node1.getNeighs().contains(node2))
		{
			int edgekey = (key1+key2)*(key1+key2+1)/2+key1;
			
			node1.getNeighs().add(getNodes().get(key2));
			getNodes().remove(key1);
			getNodes().put(key1, node1);
			Edge edge = new Edge(getNodes().get(key1),getNodes().get(key2));
			
			edges.put(edgekey, edge);
			
			
			
			node2.getNeighs().add(getNodes().get(key1));
			getNodes().remove(key2);
			getNodes().put(key2, node2);
		}
	
	
		
	}
 	public void partitionGraph(MyGraph graph)
 	{
 		int start =graph.getMinNodeID();
        int end = graph.getMaxNodeID();
        int count = 0;
        while (graph.getEdges().size()>0)
        {
        	count++;
       	 for (int i = start; i<=end;i++)
            {
       		 //System.out.println("processing node " + i);
           	 for (int j = i+1; j<=  end;j++)
           	 {
           		
           		 graph.findAllShortestPaths(graph, graph.getNodes().get(i), graph.getNodes().get (j));
           	 }
            }
            List<Edge> edges = new ArrayList( graph.getEdges().values());
            if (edges.size()==0)
            {
            	break;
            }
            Collections.sort(edges);
            Edge highest = edges.get(0);
            int key1 = highest.getNode1().getKey();
            int key2 = highest.getNode2().getKey();
            double weight = edges.get(0).getWeight();
            // print the edge to be cut and its betweenness
            System.out.println("the edge to be cut in iteration "+ count+ " is ");
            
            System.out.println("the edge has node "+key1 + " and " + key2 + ", the edge betweenness is: "+ weight);
            
            Node node1 = edges.get(0).getNode1();
            Node node2 = edges.get(0).getNode2();
            
            graph.getNodes().get(node1.getKey()).getNeighs().remove(node2);
            graph.getNodes().get(node2.getKey()).getNeighs().remove(node1);
            graph.getEdges().remove((key1+key2)*(key1+key2+1)/2+key1);
            
            findSubgraph(graph);
            for (Edge edge:graph.getEdges().values())
            {
           	 edge.setWeight(0);
            }
            
           
        }
 	}
	public void findSubgraph(MyGraph g)
	{
		HashSet<Node> toexplore = new HashSet<Node>();
		toexplore.addAll(g.getNodes().values());
		ArrayList<ArrayList<Node>> components = new ArrayList<ArrayList<Node>>();
		int count = 0;
		while (toexplore.size()>0)
		{
			Node start = toexplore.iterator().next();
			ArrayList<Node> component = new ArrayList<Node>();
			// use bfs to identify components
			bfs(g,start, toexplore, component);
			components.add(component);
			count++;
		}
		System.out.println(count+ " component(s) were/was found");
		//printComponents(components);
		if (components.size()==10)
		{
			printComponentsByList(components);
			
		}
		
	}
	

	private void bfs(MyGraph g, Node start, HashSet<Node> toexplore, ArrayList<Node> component) {
		// TODO Auto-generated method stub
		 Queue<Node> queue = new LinkedList<Node>();
		 queue.add(start);
		 HashSet<Node> visited = new HashSet<Node>();
		 visited.add(start);
		 component.add(start);
		 toexplore.remove(start);
		// System.out.println(start.getKey()+" is added to component");
	
		 //System.out.println(start.getKey()+" removed from toexplore");
		 while (queue.size()>0)
		 {
			 Node  curr = queue.poll();
			 for (Node n:curr.getNeighs())
			 {
				 if (!visited.contains(n))
				 {
					queue.add(n);
					visited.add(n);
					component.add(n);
					//System.out.println(n.getKey()+" is added to component");
					toexplore.remove(n);
					//System.out.println(n.getKey()+" removed from toexplore");
					
					
				}
			 } 
			 queue.remove(curr);
			

		 }
		 
		
	}
	private void printComponentsByList(ArrayList<ArrayList<Node>> components) {
		// TODO Auto-generated method stub
		System.out.println("component ID, Node ID");
		for (int i = 0; i<components.size();i++)
		{
			
			for (int j = 0; j<components.get(i).size();j++)
			{
				System.out.println((i +1)+","+ components.get(i).get(j).getKey());
			}
			
		}
	}
	
	private void printComponents(ArrayList<ArrayList<Node>> components) {
		// TODO Auto-generated method stub
		for (int i = 0; i<components.size();i++)
		{
			System.out.print("component " + (i +1)  + " contains node(s): ");
			for (int j = 0; j<components.get(i).size()-1;j++)
			{
				System.out.print( components.get(i).get(j).getKey()+ ",");
			}
			System.out.println(components.get(i).get(components.get(i).size()-1).getKey());
		}
	}
	
	public List<ArrayList<Node>> findAllShortestPaths (MyGraph graph, Node from, Node to)
	{
		 LinkedHashMap<Node, Object> queue = new LinkedHashMap<>();
	     Set<Node> visited = new HashSet<Node>();
	     from.setLevel(0);
	     reset.add(from);
	     queue.put(from, mock);
	     Node nodeTo = null;
	     while (queue.keySet().size()>0)
	     {
	    	 Node next = queue.keySet().iterator().next();
	    
	    	 if (next.equals(to))
	    	 {
	    		 nodeTo = next;
	    		 break;
	    	 }
	    	 for (Node n:next.getNeighs())
	    	 {
	    		 if (!visited.contains(n))
	    		 {
	    			 if (queue.get(n) ==null)
	    			 {
	    				 queue.put(n,mock);
	    			 }
	    			 n.addParent(next);
	    			 reset.add(n);
	    		 }
	    		 
	    		 
	    	 }
	    	 queue.remove(next);
	    	 visited.add(next);
	     }
	     if (nodeTo ==null)
	     {
	    	 cleanup(reset);
	    	 return Collections.emptyList();
	     }
	     List<ArrayList<Node>> result = new ArrayList<ArrayList<Node>>();
	  
	    
	     dfs(nodeTo, result, new LinkedList<Node>());
	  //  for debug print path
	     //printPaths (result);
	     addEdgeWeights(graph,result);
	     // reset edges' weight and nodes' parent
	     cleanup(reset);
	     return result;
	}
	
	private void dfs(Node n, List<ArrayList<Node>> result, LinkedList<Node> path) {
		// TODO Auto-generated method stub
		path.addFirst(n);
        if (n.getParents().size() == 0) {
            // base case: we came to target vertex
            result.add(new ArrayList<>(path));
        }
        for (Node p : n.getParents()) {
            dfs(p, result, path);
        }
        // do not forget to remove the processed element from path
        path.removeFirst();
	}
	
	private void addEdgeWeights(MyGraph graph, List<ArrayList<Node>> pathes) {
		// TODO Auto-generated method stub
		for (ArrayList<Node> path:pathes)
		{
			for (int i = 0; i<path.size()-1;i++)
			{
				int key1 = path.get(i).getKey();
				int key2 = path.get(i+1).getKey();
				if (key1 > key2)
				{
					int temp = key1;
					key1 = key2;
					key2 = temp;
				}
			
				int pairedkey = (key1+key2)*(key1+key2+1)/2+key1;
				Edge e = graph.getEdges().get(pairedkey);
				e.setWeight(e.getWeight()+(double)1/pathes.size());
			}
		}
		
		
	}
	private void cleanup(HashSet<Node> reset) {
		// TODO Auto-generated method stub
		for (Node n: reset)
		{
			n.clearParent();
			n.setLevel(-1);
		}
		
	}
	
	private void printPaths(List<ArrayList<Node>> result) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i< result.size();i++)
		{
			System.out.print("path " + (i+1)+":");
			for (int j = 0;j<result.get(i).size();j++)
			{
				System.out.print(result.get(i).get(j).getKey()+",");
			}
			System.out.println("");
			
		}
		
	}

	public HashMap<Integer, Node> getNodes() {
		return nodes;
	}
	public void setNodes(HashMap<Integer, Node> nodes) {
		this.nodes = nodes;
	}

}
