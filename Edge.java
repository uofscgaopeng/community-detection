//http://stackoverflow.com/questions/919612/mapping-two-integers-to-one-in-a-unique-and-deterministic-way
package mygraph;

public class Edge implements Comparable {
	private Node node1;
	Node getNode1() {
		return node1;
	}

	void setNode1(Node node1) {
		this.node1 = node1;
	}

	Node getNode2() {
		return node2;
	}

	void setNode2(Node node2) {
		this.node2 = node2;
	}
	private Node node2;
	private double weight = 0;

	double getWeight() {
		return weight;
	}
	
	private int cantorparing()
	{
	
		int a = node1.getKey();
		int b = node2.getKey();
		if (a>b)
		{
			int c = a;
			a = b;
			b = c;
		}
		
		return (a + b) * (a + b + 1) / 2 + a;
		
		
		
	}
	void setWeight(double weight) {
		this.weight = weight;
	}

	public Edge(Node node1, Node node2) {
		// TODO Auto-generated constructor stub
		this.node1 = node1;
		this.node2  = node2;
	}
	 public boolean equals(Object o) {
		 
	        return this.cantorparing()== ((Edge)o).cantorparing() ;
	    }

	 public int hashCode() {
	        return this.cantorparing();
	    }
	@Override
	public int compareTo(Object o) {
		
		return Double.compare( ((Edge) o).getWeight(), this.getWeight());
	}

}
