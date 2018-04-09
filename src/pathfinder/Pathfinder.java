package pathfinder;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

import graph.Graph;

public class Pathfinder {
	/** Calculate cost between all or selected nodes on a graph.			
	* @Param 	graph 	Graph			
	* @Param  	start 	Starting point of the path
	* @Param 	end 	End point of the path (optional)
	*/			
	public Point[] Dijkstra(Graph graph, Point start, Point end) {	

		Collection<? extends Point> points = graph.GetPoints();
		PriorityQueue<Point> queue = new PriorityQueue<Point>(points.size(), graph);	// Initialize queue
		queue.addAll(points);
		
		HashMap<Point, Point> parent = new HashMap<Point, Point>();
		parent.put(start, null);
		
		for (Point p : points) {
			graph.SetCost(p, Integer.MAX_VALUE);
		}
		graph.SetCost(start, 0);
		
		while (!queue.isEmpty()) {
			Point current = queue.remove();						// Get first item from queue
			if (current.equals(end)) break;						// Early break once the algorithm finds the end point
			
			Point[] children = graph.GetChildren(current);		// Get current point's children
			for (Point c : children) {							// And loop through them
				if (queue.contains(c)) {
					int costing = graph.Costing(current, c);
					if (costing < graph.GetCost(c)) {					// And if it's current cost is more expensive than proposed costing
						graph.SetCost(c, costing);					// Set the costing as the new cost
						parent.put(c, current);
						//graph.SetParent(c, current);
					}
				}
			}
		}
		
		System.out.println("Parentlist:");
		for (Point key : parent.keySet()) {
			System.out.println(key + " > " + parent.get(key));
		}
		
		// Work out the answer to algorithm by going through the parents (parent HashMap) of each vertex starting from the end Point
		ArrayList<Point> shortestPath = new ArrayList<Point>();
		Point last = end;
		shortestPath.add(last);
		while (!start.equals(last)) {
			last = parent.get(last);
			if (last == null) break;
			shortestPath.add(last);
		}
		
		Collections.reverse(shortestPath);
		Point[] sp = shortestPath.toArray(new Point[0]);
		
		return sp;
	}
}
