package mygraph;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MyGraphLoader {

	
	public  void loadGraph(MyGraph g, String filename) {
        
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Iterate over the lines in the file, adding new
        // vertices as they are found and connecting them with edges.
        while (sc.hasNextInt()) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            // make the key of the origin/first node smaller than the destination/second node.
            if  (v1>v2)
            {
            	int temp;
            	temp = v1;
            	v1 = v2;
            	v2 = temp;
            }
          
            g.addEdge(v1, v2);
        }
        
        sc.close();
       
    }
	

}
