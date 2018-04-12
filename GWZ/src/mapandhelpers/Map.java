package mapandhelpers;

import java.io.File;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import Objects.ActorObject;
import Objects.CharacterObject;

public class Map extends Application{
	
	final public static int viewSizeX = 1000;
	final public static int viewSizeY = 800;
	public static void main(String[] args){
		//System.out.println(javafx.scene.text.Font.getFamilies());
		launch(args);
	}
	
	public void start(Stage primaryStage){
		titleScreen(primaryStage);
	}
	
	public void titleScreen(Stage primaryStage){
		Pane pane = new StackPane();
		
		//Background
		BackgroundFill a = new BackgroundFill(Color.GREY, null, null);
		Background background = new Background(new BackgroundFill[] {a});
		pane.setBackground(background);
		
		//Setting view sizes and instantiating the scene object for title screen
	    Scene scene = new Scene(pane, viewSizeX, viewSizeY);
	    
	    //Logo images
	    File file1 = new File("galaxy-string.png");
	    File file2 = new File("magic_wizard_hat.png");
	    File file3 = new File("AK47.png");
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
//		Pane. setAlignment(wizLogo, Pos.CENTER);
		
		//Button to enter main menu
		Button b = new Button();
		b.setText("Enter");
		b.setTranslateX(0);
		b.setTranslateY(300);
		b.setStyle("-fx-font-size:40; -fx-background-color:red;");
		pane.getChildren().add(b);
		
		//Sound effect
		String musicFile = "Swords_Collide-Sound_Explorer-2015600826.mp3";
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
		Pane pane = new StackPane();
		
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
		
		String musicFile = "Swords_Collide-Sound_Explorer-2015600826.mp3";

		Media sound = new Media(new File(musicFile).toURI().toString());
		final MediaPlayer mediaPlayer = new MediaPlayer(sound);
		// Button press
		b.setOnAction((event) -> {
			mediaPlayer.play();
		    gameScreen(primaryStage);
		});
	}
	
	public void gameScreen(Stage primaryStage){
		Pane pane = new StackPane();
		BackgroundFill a = new BackgroundFill(Color.WHITE, null, null);
		Background background = new Background(new BackgroundFill[] {a});
		pane.setBackground(background);
		
		ActorObject userChar = new CharacterObject();
		
		Circle userPlace = new Circle(50);
		userPlace.setTranslateX(0);
		userPlace.setTranslateY(0);
		userPlace.setFill(Color.RED);
		
		pane.getChildren().add(userPlace);
		
		File akFile = new File("AK47.png");
		ActorObject ak47 = new ActorObject();
	    ImageView akIv = new ImageView(new Image(akFile.toURI().toString(),ak47.getSizeX(),ak47.getSizeY(),false,false));
	    pane.getChildren().add(akIv);
	    ak47.setPosX(userChar.getPosX()+50);
	    akIv.setTranslateX(ak47.getPosX()+50);
		
		Button b = new Button();
		b.setText("Enter");
		b.setTranslateX(0);
		b.setTranslateY(300);
		b.setStyle("-fx-font-size:40; -fx-background-color:red;");
//		b.setMaxWidth(200);
//		b.setMaxHeight(200);
		pane.getChildren().add(b);
				
		b.setOnAction((event) -> {
			userPlace.setTranslateY(0);
			userPlace.setTranslateX(0);
			userChar.setPosY(0);
			userChar.setPosX(0);
		});
		
		Scene scene = new Scene(pane, viewSizeX, viewSizeY);
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()){
			case DOWN:
				userPlace.requestFocus();
				userChar.setPosY(userChar.getPosY()+100);
				userPlace.setTranslateY(userChar.getPosY());
				
				akIv.requestFocus();
				ak47.setPosY(userChar.getPosY()+50);
				akIv.setTranslateY(ak47.getPosY());
				break;
			case UP:
				userPlace.requestFocus();
				userChar.setPosY(userChar.getPosY()-100);
				userPlace.setTranslateY(userChar.getPosY());
				
				akIv.requestFocus();
				ak47.setPosY(userChar.getPosY()-50);
				akIv.setTranslateY(ak47.getPosY());
				break;
			case RIGHT:
				userPlace.requestFocus();
				userChar.setPosX(userChar.getPosX()+100);
				userPlace.setTranslateX(userChar.getPosX());
				
				akIv.requestFocus();
				ak47.setPosX(userChar.getPosX()+50);
				akIv.setTranslateX(ak47.getPosX());
				break;
			case LEFT:
				userPlace.requestFocus();
				userChar.setPosX(userChar.getPosX()-100);
				userPlace.setTranslateX(userChar.getPosX());
				
				akIv.requestFocus();
				ak47.setPosX(userChar.getPosX()-50);
				akIv.setTranslateX(ak47.getPosX());
				break;
			case W:
				Rectangle rect2 = new Rectangle();                 
				rect2.setX(500);
				rect2.setY(500);
				rect2.setWidth(100);
				rect2.setHeight(100);
				rect2.setFill(Color.BLUE);
				pane.getChildren().addAll(rect2);
				Path path = new Path();
				path.getElements().add(new MoveTo(0, 0));
				path.getElements().add(new LineTo(0, 100));

				PathTransition pathTransition = new PathTransition();
				pathTransition.setDuration(Duration.millis(10000));
				pathTransition.setNode(rect2);
				pathTransition.setPath(path);
				pathTransition.setOrientation(OrientationType.NONE);
				pathTransition.setAutoReverse(true);
				pathTransition.play();
			default:
				break;
			}	
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}