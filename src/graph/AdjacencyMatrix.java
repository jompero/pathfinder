package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AdjacencyMatrix extends Graph {
	
	ArrayList<ArrayList<Integer>> graph;
	
	/**
	 * Adjacency matrix is a 2 dimensional matrix of integers where the integer value denotes the cost of arriving to the point (i,j).
	 * @param size Initial size of the generated matrix. Can be modified on runtime.
	 */
	public AdjacencyMatrix(int x, int y) {
		graph = new ArrayList<ArrayList<Integer>>(y);
		for (int i = 0; i < y; i++) {
			graph.add(new ArrayList<Integer>(x));
		}
	}
	
	@Override
	public Collection<Point> GetPoints() {
		ArrayList<Point> points = new ArrayList<Point>();
		for (int y = 0; y < graph.size(); y++) {
			for (int x : graph.get(y)) {
				points.add(new Point(x, y));
			}
		}
		return points;
	}

	@Override
	public void SetCost(Point p, int cost) {
		graph.get(p.y).set(p.x, cost);
	}

	@Override
	public Point[] GetChildren(Point point) {
		int x = point.x;
		int y = point.y;
		int maxX = graph.get(0).size();
		int maxY = graph.size() - 1;
		
		if (x < 0 || y < 0) {
			if (x > maxX || y > maxY) {
				ArrayList<Point> points = new ArrayList<Point>(4);
				points.add(new Point(x-1, y));
				points.add(new Point(x+1, y));
				points.add(new Point(x, y-1));
				points.add(new Point(x, y+1));
				
				for (Point p : points) {
					if (p.x < 0 || p.x > maxX || p.y < 0 || p.y > maxY) {
						points.remove(p);
					}
				}
				
				return points.toArray(new Point[points.size()]);
			}
		}
		return null;
	}

	@Override
	public int Costing(Point current, Point c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetCost(Point current) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

}
