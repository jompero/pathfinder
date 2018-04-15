package scenes;

import java.awt.Point;
import java.util.ArrayList;

import graph.AdjacencyList;
import graph.Vertex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import pathfinder.Pathfinder;

public class GraphUI extends Pane {
	// Graph
	AdjacencyList graph;
	
	// Scaling for zoom in and out
	double scale = 1;
	// Mouse drag delta and origin on screen for mouse drag event
	double x;
	double y;
	double deltaX;
	double deltaY;
	
	// Background image
	Image image;
	
	// To hold the radio buttons for event handling
	ToggleGroup tg;
	
	// Store last clicked 2 Points
	Point prevPoint;
	Point currentPoint;
	
	//  Group to be updated with Dijkstra result
	ImageView background;
	Group edges;
	Group result;
	Group rbuttons;
	
	// The offset to draw edges to center of RadioButtons instead of top left
	int rbOffset = 8;
	
    public GraphUI(AdjacencyList graph) {
		// Initialize ImageView
    	background = new ImageView();
    	
    	this.graph = graph;
		
		// Predefine a random path to draw
		ArrayList<Vertex> vertices = graph.getPoints();
		prevPoint = vertices.get((int) (Math.random() * vertices.size()));
		currentPoint = vertices.get((int) (Math.random() * vertices.size()));
	
		// Create random graph to start with
		drawGraph();
		
		// Setup for mouse drag event (scene moving)
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
			    x = event.getSceneX();
			    y = event.getSceneY();
			}
			
		});
		
		// Scene moving mouse drag event
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				Node node = (Node) event.getSource();
				deltaX = event.getSceneX() - x;
				deltaY = event.getSceneY() - y;

	            node.setTranslateX(node.getTranslateX() + deltaX * 0.001);
	            node.setTranslateY(node.getTranslateY() + deltaY * 0.001);
			}
			
		});
		
		// Zoom effect on scroll
		this.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				scale = scale + event.getDeltaY() * 0.001;
				drawGraph();
			}
		});
    }
    
    // Set a background image for the graph
    public void setImage(Image image) {
    	this.image = image;
    	drawGraph();
    }
    
    // Draw the graph
	void drawGraph() {	
		getChildren().clear();
		
		if (image != null) background = drawBG();
		edges = drawEdges();
		result = drawDijkstra();
		rbuttons = radioButtons();
		
		getChildren().addAll(edges, background, result, rbuttons);
	}
	
	void refreshPath() {
		getChildren().remove(result);
		result = drawDijkstra();
		getChildren().add(getChildren().indexOf(rbuttons), result);
	}
	
	ImageView drawBG() {
		if (image == null) return null;
		ImageView img = new ImageView(image);
		img.setPreserveRatio(true);
		img.setFitHeight(image.getHeight() * scale);
		return img;
	}
	
	Group radioButtons() {
		// Draw vertices on Pane as Radio Buttons
		// The radio buttons serve as UI to select next destination
		Group buttons = new Group();
		tg = new ToggleGroup();
		
		for (Point parent : graph) {			
			RadioButton btn = new RadioButton(parent.toString());
			
			btn.setLayoutX(parent.getX() * scale - rbOffset);
			btn.setLayoutY(parent.getY() * scale - rbOffset);
			btn.setUserData(parent);
			btn.setToggleGroup(tg);
			buttons.getChildren().add(btn);
		}
		
	    tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	    	@Override
	    	public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
	    		if (tg.getSelectedToggle() != null) {
	    			prevPoint = currentPoint;
					currentPoint = (Point) tg.getSelectedToggle().getUserData();
					System.out.println(String.format("Running points %s and %s through Dijkstra's algorithm", prevPoint, currentPoint));
					refreshPath();
	    		}
	        }
	    });
		
		return buttons;
	}
	
	Group drawEdges() {
		// Display edges between vertices as grey lines
		Group edges = new Group();
		
		for (Point parent : graph) {
			for (Point child : graph.getPoint(parent).GetChildren()) {
				Line edge = new Line();
				edge.setStroke(Color.rgb(45, 45, 45));
				edge.setStrokeWidth(0.3);
				edge.setStartX(parent.getX() * scale);
				edge.setStartY(parent.getY() * scale);
				edge.setEndX(child.getX() * scale);
				edge.setEndY(child.getY() * scale);
				edges.getChildren().add(edge);
			}			
		}
		return edges;
	}
	
	Group drawDijkstra() {
		// Use Dijkstra to calculate path
		Point[] dijkstra = Pathfinder.dijkstra(graph, prevPoint, currentPoint);
		
		// Display path generated by Dijkstra as yellow lines
		Group path = new Group();
		
		for (int i = 0; i < dijkstra.length - 1; i++) {
			Line line = new Line();
			line.setStroke(Color.BLUE);
			line.setStrokeWidth(4);
			line.setStartX(dijkstra[i].getX() * scale);
			line.setStartY(dijkstra[i].getY() * scale);
			line.setEndX(dijkstra[i+1].getX() * scale);
			line.setEndY(dijkstra[i+1].getY() * scale);
			path.getChildren().add(line);
		}
		
		return path;
	}
}
