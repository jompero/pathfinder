package graph;

import java.awt.Point;
import java.util.HashMap;

public class Vertex extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Vertex parent;							// Parent Vertex for Pathfinding purposes
	HashMap<Vertex, Integer> children;
	int cost;

	public Vertex(int x, int y) {
		children = new HashMap<Vertex, Integer>();
		this.x = x;
		this.y = y;
	}
	
	public void Connect(Vertex vertex, int cost) {
		children.put(vertex, cost);
	}
	
	public void SetParent(Vertex parent) {
		this.parent = parent;
	}
	
	public Vertex GetParent() {
		return parent;
	}
	
	public Vertex[] GetChildren() {
		Vertex[] vArray = children.keySet().toArray(new Vertex[0]);
		return vArray;
	}
	
	public int GetWeight(Point point) {
		return children.get(point);
	}
	
	public int GetCost() {
		return cost;
	}
	
	public void SetCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		String toString = String.format("(%d, %d)", x, y);
		return toString;
	}
}
