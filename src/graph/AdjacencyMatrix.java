package graph;

import java.awt.Point;
import java.util.ArrayList;

public class AdjacencyMatrix extends Graph {
	
	int[][] graph;

	@Override
	public ArrayList<Point> getPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCost(Point p, double maxValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point[] getChildren(Point point) {
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
	public double costing(Point current, Point c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCost(Point current) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setParent(Point c, Point current) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compare(Point arg0, Point arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getPoint(Point point) {
		// TODO Auto-generated method stub
		return null;
	}

}
