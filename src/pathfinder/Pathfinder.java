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
	* @Param 	end 	End point of the path
	*/			
	public static Point[] dijkstra(Graph graph, Point start, Point end) {	
		System.out.println(String.format("Finding shortest path between %s and %s", start, end));		
		
		Collection<? extends Point> points = graph.getPoints();
		PriorityQueue<Point> queue = new PriorityQueue<Point>(points.size(), graph);	// Initialize queue
		for (Point p : points) {
			graph.setCost(p, Double.POSITIVE_INFINITY);
		}
		graph.setCost(start, 0);
		queue.add(start);
		
		ArrayList<Point> visited = new ArrayList<Point>();
		HashMap<Point, Point> parent = new HashMap<Point, Point>();
		parent.put(start, null);
		
		System.out.println(queue);
		
		while (!queue.isEmpty()) {
			// Poll cheapest point from queue and mark it as visited. Break if it is the destination.
			Point current = queue.poll();
			visited.add(current);
			System.out.println(String.format("Evaluating point %s at cost %s", current, graph.getCost(current)));
			if (current.equals(end)) break;
			
			// Loop through current point's children. Calculate the cost to visit them if they have not been visited already. 
			// If new cost is cheaper than their current cost, update it.
			Point[] children = graph.getChildren(current);
			for (Point c : children) {
				if (!visited.contains(c)) {
					double costing = graph.costing(current, c);
					System.out.println(String.format("Cost from point %s to %s is %s", current, c, costing));
					if (costing < graph.getCost(c)) {
						graph.setCost(c, costing);
						parent.put(c, current);
						queue.remove(c);
						queue.add(c);
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
		
		System.out.println("Result:");
		for (Point v : sp) {
			System.out.println(v);
		}
		return sp;
	}
}
