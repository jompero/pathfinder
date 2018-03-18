package graph;

import java.awt.Point;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public abstract class Graph implements Iterable<Point>, Comparator<Point> {

	public abstract Collection<? extends Point> GetPoints();
	
	public abstract Point GetPoint(Point point);
	
	public abstract Point[] GetChildren(Point point);

	public abstract int GetCost(Point point);
	
	public abstract void SetCost(Point p, int maxValue);

	public abstract int Costing(Point point, Point c);

	public abstract void SetParent(Point c, Point point);
	
	@Override
	public abstract Iterator<Point> iterator();
	
	@Override
	public abstract int compare(Point arg0, Point arg1);
}
