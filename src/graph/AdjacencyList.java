package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AdjacencyList extends Graph {
	
	ArrayList<Vertex> graph;
	
	
	/**
	 * Adjacency list is a list of vertices where each vertex contains the information of connected vertices and the cost of connection.
	 */
	public AdjacencyList() {
		graph = new ArrayList<Vertex>();
	}

	public void AddPoint(Vertex... vertices) {
		for (Vertex v : vertices) {
			graph.add(v);
		}
	}
	
	@Override
	public Collection<? extends Point> GetPoints() {
		return (Collection<? extends Point>) graph;
	}

	@Override
	public void SetCost(Point p, int cost) {
		GetPoint(p).SetCost(cost);
	}

	@Override
	public Point[] GetChildren(Point point) {
		return (Point[]) graph.toArray();
	}

	@Override
	public int Costing(Point current, Point c) {
		return GetPoint(current).GetCost() + GetPoint(current).GetWeight(c);
	}

	@Override
	public int GetCost(Point current) {
		return GetPoint(current).GetCost();
	}

	@Override
	public void SetParent(Point c, Point current) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Point> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compare(Point arg0, Point arg1) {
		if (GetPoint(arg0).GetCost() > GetPoint(arg1).GetCost()) {
			return 0;
		}
		return 1;
	}

	@Override
	public Vertex GetPoint(Point point) {
		for (Vertex v : graph) {
			if (v.equals(point)) {
				return v;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String toString = "[";
		for (Vertex v : graph) {
			toString += v.toString();
		}
		toString += "]";
		return toString;	
	}
}
