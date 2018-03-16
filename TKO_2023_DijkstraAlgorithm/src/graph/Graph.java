package graph;

import java.awt.Point;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public abstract class Graph implements Iterable<Point>, Comparator<Point> {
	
	public abstract Collection<Point> GetPoints();

	public abstract void SetCost(Point p, int maxValue);

	public abstract Point[] GetChildren(Point current);

	public abstract int Costing(Point current, Point c);

	public abstract int GetCost(Point current);

	public abstract void SetParent(Point c, Point current);
	
	@Override
	public abstract Iterator<Point> iterator();
	
	@Override
	public abstract int compare(Point arg0, Point arg1);
}
