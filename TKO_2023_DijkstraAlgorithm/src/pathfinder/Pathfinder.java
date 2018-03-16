package pathfinder;

import java.awt.Point;
import java.util.Collection;
import java.util.PriorityQueue;

import graph.Graph;

public class Pathfinder {
	/** Calculate cost between all or selected nodes on a graph.			
	* @Param 	graph 	Graph			
	* @Param  	start 	Starting point of the path
	* @Param 	end 	End point of the path (optional)
	*/			
	public void Dijkstra(Graph graph, Point start, Point end) {	
		Collection<Point> points = graph.GetPoints();
			
		Point[] route = new Point[0];													// Initialize route
		PriorityQueue<Point> queue = new PriorityQueue<Point>(points.size(), graph);	// Initialize queue
		queue.addAll(points);
		for (Point p : points) {
			graph.SetCost(p, Integer.MAX_VALUE);
		}
		graph.SetCost(start, 0);
		
		while (!queue.isEmpty()) {
			Point current = queue.remove();						// Get first item from queue
			
			Point[] children = graph.GetChildren(current);		// Get current point's children
			for (Point c : children) {							// And loop through them
				int costing = graph.Costing(current, c);
				if (costing < graph.GetCost(c)) {			// And it's current cost is more expensive than proposed costing
					graph.SetCost(c, costing);				// Set the costing as the new cost
					graph.SetParent(c, current);
				}
			}
		}
	}
}
