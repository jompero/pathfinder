package scenes;

import java.awt.Point;

import graph.AdjacencyList;
import graph.Vertex;
import pathfinder.Pathfinder;

public class MainScene {
	public static void main(String [] args) {
		// Prepare graph
		AdjacencyList al = new AdjacencyList();
		// Add some points
		Vertex a = new Vertex(1,1);
		Vertex b = new Vertex(1,9);
		Vertex c = new Vertex(6,9);
		Vertex d = new Vertex(4,5);
		
		a.Connect(b, 8.0);
		a.Connect(d, 5f);
		
		b.Connect(a, 8);
		b.Connect(d, 5);
		b.Connect(c, 5);
		
		c.Connect(b, 5);
		c.Connect(d, 4);
		
		d.Connect(a, 5);
		d.Connect(b, 5);
		d.Connect(c, 4);
		
		al.AddPoint(a, b, c, d);
		
		Pathfinder pf = new Pathfinder();
		Point[] ps = pf.Dijkstra(al, a, b);
		System.out.println("Result:");
		for (Point p : ps) {
			System.out.println(p);
		}
	}
}
