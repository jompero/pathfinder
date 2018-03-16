package graph;

import java.awt.Point;
import java.util.HashMap;

public class Vertex {
	Point point;
	HashMap<Point, Integer> children;

	public Vertex(int x, int y) {
		this.point = new Point(x, y);
	}
}
