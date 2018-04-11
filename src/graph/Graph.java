package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Graph implements Comparator<Point> {

	public abstract ArrayList<? extends Point> getPoints();
	
	public abstract Point getPoint(Point point);
	
	public abstract Point[] getChildren(Point point);

	public abstract double getCost(Point point);
	
	public abstract void setCost(Point p, double maxValue);

	public abstract double costing(Point point, Point c);

	public abstract void setParent(Point c, Point point);
	
	@Override
	public abstract int compare(Point arg0, Point arg1);
}
