package mapandhelpers;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Map extends Application{
	
	final public static int viewSizeX = 1000;
	final public static int viewSizeY = 800;
	public static void main(String[] args){
		//System.out.println(javafx.scene.text.Font.getFamilies());
		launch(args);
	}
	
	public void start(Stage primaryStage){
		titleScreen(primaryStage);
		
		
//		pa.getShape().setOnKeyPressed(e -> {
//			switch (e.getCode()){
//			case DOWN:
//				pa.DOWN();
//				break;
//			case UP:
//				pa.UP();
//				break;
//			case RIGHT:
//				pa.RIGHT();
//				break;
//			case LEFT:
//				pa.LEFT();
//				break;
//			default:
//				break;
//			}	
//		});
	}
	
	public void titleScreen(Stage primaryStage){
		StackPane pane = new StackPane();
		
		//Background
		BackgroundFill a = new BackgroundFill(Color.GREY, null, null);
		Background background = new Background(new BackgroundFill[] {a});
		pane.setBackground(background);
		
		//Setting view sizes and instantiating the scene object for title screen
	    Scene scene = new Scene(pane, viewSizeX, viewSizeY);
	    
	    //Logo images
	    File file1 = new File("/Users/Beta/Documents/workspace/GWZ/Resources/galaxy-string.png");
	    File file2 = new File("/Users/Beta/Documents/workspace/GWZ/Resources/magic_wizard_hat.png");
	    File file3 = new File("/Users/Beta/Documents/workspace/GWZ/Resources/AK47.png");
	    ImageView iv1 = new ImageView(new Image(file1.toURI().toString(),1000,800,false,false));
	    ImageView iv2 = new ImageView(new Image(file2.toURI().toString(), 500, 500, false, false));
	    ImageView iv3 = new ImageView(new Image(file3.toURI().toString(),500,500,false,false));
	    pane.getChildren().addAll(iv1, iv2, iv3);
	    
	    //Logo
	    Text wizLogo = new Text(500,400,"GUNWIZARD");
	    wizLogo.setFont(Font.font("Comic Sans MS", 100));
		wizLogo.setFill(Color.RED);
		wizLogo.setStroke(Color.ANTIQUEWHITE);
		pane.getChildren().add(wizLogo);
		StackPane.setAlignment(wizLogo, Pos.CENTER);
		
		Button b = new Button();
		b.setText("Enter");
		b.setTranslateX(0);
		b.setTranslateY(300);
		b.setStyle("-fx-font-size:40; -fx-background-color:red;");
//		b.setMaxWidth(200);
//		b.setMaxHeight(200);
		pane.getChildren().add(b);
		
		String musicFile = "/Users/Beta/Documents/workspace/GWZ/Resources/Swords_Collide-Sound_Explorer-2015600826.mp3";     // For example

		Media sound = new Media(new File(musicFile).toURI().toString());
		final MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		// Button press
		b.setOnAction((event) -> {
			
			mediaPlayer.play();
		    mainMenu(primaryStage);
		});
		
		//Finalizing view
	    primaryStage.setTitle("Gunwizard");
	    primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void mainMenu(Stage primaryStage){
		StackPane pane = new StackPane();
		
		//Background
		BackgroundFill a = new BackgroundFill(Color.GREY, null, null);
		Background background = new Background(new BackgroundFill[] {a});
		pane.setBackground(background);
		
		//Setting view sizes and instantiating the scene object for title screen
	    Scene scene = new Scene(pane, viewSizeX, viewSizeY);
	    primaryStage.setScene(scene);
		primaryStage.show();
		
		Button b = new Button();
		b.setText("Enter");
		b.setTranslateX(0);
		b.setTranslateY(200);
		b.setStyle("-fx-font-size:40; -fx-background-color:red;");
//		b.setMaxWidth(200);
//		b.setMaxHeight(200);
		pane.getChildren().add(b);
		
		String musicFile = "/Users/Beta/Documents/workspace/GWZ/Resources/Swords_Collide-Sound_Explorer-2015600826.mp3";

		Media sound = new Media(new File(musicFile).toURI().toString());
		final MediaPlayer mediaPlayer = new MediaPlayer(sound);
		// Button press
		b.setOnAction((event) -> {
			mediaPlayer.play();
		    titleScreen(primaryStage);
		});
	}
}
