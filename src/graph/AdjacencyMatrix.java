package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AdjacencyMatrix extends Graph {
	
	int[][] graph;

	@Override
	public Collection<? extends Point> GetPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetCost(Point p, int maxValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point[] GetChildren(Point point) {
		int x = point.x;
		int y = point.y;
		int maxX = graph.length;
		int maxY = graph[0].length;
		
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

	@Override
	public Point GetPoint(Point point) {
		// TODO Auto-generated method stub
		return null;
	}

}
