package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AdjacencyList extends Graph implements Iterable<Vertex> {
	
	protected ArrayList<Vertex> graph;
	
	
	/**
	 * Adjacency list is a list of vertices where each vertex contains the information of connected vertices and the cost of connection.
	 */
	public AdjacencyList() {
		graph = new ArrayList<Vertex>();
	}

	public void addPoint(Vertex... vertices) {
		for (Vertex v : vertices) {
			graph.add(v);
		}
	}
	
	public void addAllPoints(Collection<Vertex> points) {
		for (Vertex v : points) {
			addPoint(v);
		}
	}
	
	public String getPath(Vertex a, Vertex b) {		
		String path = b.toString();
		Vertex current = b;
		while (current != a && current != null) {
			path += current.GetParent().toString();
			current = current.GetParent();
		}
		return path;
	}
	
	@Override
	public ArrayList<Vertex> getPoints() {
		return graph;
	}

	@Override
	public void setCost(Point p, double cost) {
		getPoint(p).SetCost(cost);
	}

	@Override
	public Point[] getChildren(Point point) {
		return getPoint(point).GetChildren();
	}

	@Override
	public double costing(Point current, Point c) {
		return getPoint(current).GetCost() + getPoint(current).GetWeight(c);
	}

	@Override
	public double getCost(Point current) {
		return getPoint(current).GetCost();
	}

	@Override
	public void setParent(Point child, Point parent) {
		getPoint(child).SetParent(getPoint(parent));
	}

	@Override
	public Iterator<Vertex> iterator() {
		return graph.iterator();
	}

	@Override
	public int compare(Point arg0, Point arg1) {
		double cost0 = getPoint(arg0).GetCost();
		double cost1 = getPoint(arg1).GetCost();
		
		if (cost0 > cost1) {
			return 1;
		} else if (cost0 == cost1) {
			return 0;
		}
		return -1;
	}

	@Override
	public Vertex getPoint(Point point) {
		for (Vertex v : graph) {
			if (v.equals(point)) {
				return v;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String toString = "Graph: ";
		for (Vertex v : graph) {
			toString += v.toString();
		}
		return toString;
	}
}
