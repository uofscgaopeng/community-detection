package mygraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;



public class Node {

	private List<Node> parents = new ArrayList<Node>();
	private HashSet<Node> neighs = new HashSet<Node>();
	
	private static Map<Integer, Node> map = new HashMap<Integer, Node>();
    final  private Integer key;
    private int level = -1;

    int getLevel() {
		return level;
	}

	void setLevel(int level) {
		this.level = level;
	}

	public static Node get(Integer key) {
        // inner interning of nodes
    	Node res = map.get(key);
        if (res == null) {
            res = new Node(key, -1);
            map.put(key, res);
        }
        return res;
    }

    Node(Integer id) {
        key = id;
        this.level = level;
    }
    Node(Integer id, int level) {
        key = id;
        this.level = level;
       
    }
    Node(Integer id, int level,HashSet<Node> nei ) {
        key = id;
        this.level = level;
        this.setNeighs(nei);
    }
   

	public void addParent(Node n) {
        // forbidding the parent it its level is equal to ours
        if (n.level == level) {
            return;
        }
        parents.add(n);

        level = n.level + 1;
    }

    public List<Node> getParents() {
        return parents;
    }

    public boolean equals(Object n) {
        return this.getKey().equals(((Node) n).getKey());
    }

    public int hashCode() {
        return this.getKey().hashCode();
    }

    @Override
    public String toString() {
        return this.getKey().toString();
    }

	public HashSet<Node> getNeighs() {
		return this.neighs;
	}

	public void setNeighs(HashSet<Node> neighs) {
		this.neighs = neighs;
	}

	public Integer getKey() {
		return this.key;
	}

	public void clearParent() {
		// TODO Auto-generated method stub
		this.parents.clear();
	}
}
