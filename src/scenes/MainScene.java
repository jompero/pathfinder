package scenes;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainScene extends Application {
	String imageSrc = "resources/turkumap.png";
	
    @Override
    public void start(Stage stage) throws Exception {
    		//new BasicGraph();
    		GraphUI gui = new GraphUI(new BasicGraph());
    		
    		Image image = new Image(imageSrc);
    		//gui.setImage(image);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}