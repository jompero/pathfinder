package scenes;

import graph.AdjacencyList;
import graph.Vertex;

public class BasicGraph extends AdjacencyList {
	
	// Minimum and maximum edges per vertex on creation (can be more if another vertex wants to connect to one with max)
	int minEdges = 1;
	int maxEdges = 2;
	
    public BasicGraph() {
        // Create random graph
    		randomGraph();
    }
    
    void randomGraph() {
		// Add 100 points to graph
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				int x = (int) (Math.random() * 70 + i * 100);
				int y = (int) (Math.random() * 70 + j * 70);
				Vertex a = new Vertex(x, y);
				addPoint(a);
			}
		}
		// Connect all vertices to 1-4 others
		int vSize = graph.size();
		for (int i = 0; i < vSize; i++) {
			for (int j = (int) (Math.random() * maxEdges + minEdges); j > 0; j--) {
				int connectTo = (int) (Math.random() * (vSize - 1));
				
				if (connectTo == i) connectTo = (connectTo + 1) % vSize;

				double cost = graph.get(i).distance(graph.get(connectTo));
				graph.get(i).Connect(graph.get(connectTo), cost);
				graph.get(connectTo).Connect(graph.get(i), cost);
			}
		}
    }
}
