package scenes;

import graph.AdjacencyList;
import graph.Vertex;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MapDemo extends AdjacencyList {
	
	
	public MapDemo() {
		jsonToGraph();
	}
	
	void jsonToGraph() {
		// Parse JSON and create AdjacencyList
		
		// Location of JSON file that contains the map data
		String absPath = new File("").getAbsolutePath();
		String jsonPath = absPath.concat("/src/resources/mapdata.json");

		// Parse the file and create necessary objects to define the graph
		JSONParser parser = new JSONParser();
		try {
			Object file = parser.parse(new FileReader(jsonPath));
			JSONObject json = (JSONObject) file;
			System.out.println("Map Data opened.");
			
			// Array of points parsed from the JSON file
			JSONArray jpoints = (JSONArray) json.get("points");
			
			// Generate vertices first
			ArrayList<Vertex> points = new ArrayList<Vertex>();
			
			for (int i = 0; i < jpoints.size(); i++) {
				JSONObject p = (JSONObject) jpoints.get(i);
				JSONArray jpoint = (JSONArray) p.get("point");
				Vertex point = new Vertex();
				point.setLocation((long) jpoint.get(0), (long) jpoint.get(1));
				points.add(point);
			}
			
			addAllPoints(points);
			
			// Connect the vertices
			for (int i = 0; i < jpoints.size(); i++) {
				JSONObject p = (JSONObject) jpoints.get(i);
				JSONArray neighbors = (JSONArray) p.get("neighbors");
				for (Object o : neighbors) {
					Long[] set = {(Long) ((JSONArray) o).get(0), (Long) ((JSONArray) o).get(1)};
					Vertex point = new Vertex();
					point.setLocation(set[0], set[1]);
					Vertex parent = getPoints().get(i);
					parent.Connect(point, parent.distance(point));
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for (Point p : this) {
			System.out.println(p);
		}
	}
}
