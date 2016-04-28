package mygraph;

public class GraphRunner {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 MyGraph graph = new MyGraph();
         MyGraphLoader loader = new  MyGraphLoader();
         loader.loadGraph(graph, "data/football.txt");
         //loader.loadGraph(graph, "data/karate.txt");
         //loader.loadGraph(graph, "data/facebook_ucsd.txt");
         if ((2*graph.getNumNodes()*graph.getNumNodes()-1)>Integer.MAX_VALUE)
         {
        	 System.out.println("cannot handle so many nodes");
        	 System.exit(0);
         }
         
         System.out.println("A graph is loaded");
         System.out.println("The graph has "+ graph.getNumNodes() +" node(s)");
         System.out.println("The graph has "+ graph.getNumEdges() + " edge(s)");
         
         graph.partitionGraph(graph);
       
	}

}
