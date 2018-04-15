import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import scenes.BasicGraph;
import scenes.GraphUI;
import scenes.MapDemo;

public class Main extends Application {
	String imageSrc = "turkumap.png";
	
    @Override
    public void start(Stage stage) throws Exception {
		// Create stage
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
		
        // Prepare graphs
		GraphUI rndgraph = new GraphUI(new BasicGraph());
		GraphUI mapdemo = new GraphUI(new MapDemo());
		
		Image image = new Image(imageSrc);
		mapdemo.setImage(image);
		
		// Create tabbed pane for random basic graph and map demo
    	TabPane tp = new TabPane();
    	tp.setSide(Side.RIGHT);
    	tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    	Tab basic = new Tab();
		basic.setText("Random Graph");
		basic.setContent(rndgraph);
		Tab map = new Tab();
		map.setText("Map Demo");
		map.setContent(mapdemo);
		tp.getTabs().addAll(basic, map);
		
		// Add it to a BorderPane
		BorderPane pane = new BorderPane();
		pane.prefHeightProperty().bind(scene.heightProperty());
		pane.prefWidthProperty().bind(scene.widthProperty());
		pane.setCenter(tp);
		
		// Populate stage
		root.getChildren().add(pane);

		// Display stage
        stage.setTitle("Dijkstra"); 
        stage.setFullScreen(true); 
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}