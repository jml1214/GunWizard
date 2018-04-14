package mapandhelpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import javafx.scene.text.FontWeight;
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
		    loginScreen(primaryStage, 0);
		});
		
		//Finalizing view
	    primaryStage.setTitle("Gunwizard");
	    primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
//	public void mainMenu(Stage primaryStage){
//		Pane pane = new StackPane();
//		
//		//Background
//		BackgroundFill a = new BackgroundFill(Color.GREY, null, null);
//		Background background = new Background(new BackgroundFill[] {a});
//		pane.setBackground(background);
//		
//		//Setting view sizes and instantiating the scene object for title screen
//	    Scene scene = new Scene(pane, viewSizeX, viewSizeY);
//	    primaryStage.setScene(scene);
//		primaryStage.show();
//		
//		Button b = new Button();
//		b.setText("Enter");
//		b.setTranslateX(0);
//		b.setTranslateY(200);
//		b.setStyle("-fx-font-size:40; -fx-background-color:red;");
////		b.setMaxWidth(200);
////		b.setMaxHeight(200);
//		pane.getChildren().add(b);
//		
//		String musicFile = "Swords_Collide-Sound_Explorer-2015600826.mp3";
//
//		Media sound = new Media(new File(musicFile).toURI().toString());
//		final MediaPlayer mediaPlayer = new MediaPlayer(sound);
//		// Button press
//		b.setOnAction((event) -> {
//			mediaPlayer.play();
//		    loginScreen(primaryStage);
//		});
//	}
	
	public void loginScreen(Stage primaryStage, int state){
		Pane pane = new StackPane();
		
		// get james's image here
		File file1 = new File("gunwiz_login.png");
				
		ImageView iv1 = new ImageView(new Image(file1.toURI().toString(),1000,800,false,false));
		pane.getChildren().add(iv1);
		
		if(state == 1){
			Text wizLogo = new Text(500,100,"Invalid Username");
		    wizLogo.setFont(Font.font("Comic Sans MS", 20));
			wizLogo.setFill(Color.RED);
			pane.getChildren().add(wizLogo);
		}
		else if(state == 2){
			Text wizLogo = new Text(500,100,"Invalid Password");
		    wizLogo.setFont(Font.font("Comic Sans MS", 20));
			wizLogo.setFill(Color.RED);
			wizLogo.setTranslateY(150);
			pane.getChildren().add(wizLogo);
		}
		
	    TextField userName = new TextField();
	    userName.setMaxWidth(400);
	    userName.setMaxHeight(20);
	    userName.setPrefSize(80, 6);
	    userName.setTranslateX(23);
	    userName.setTranslateY(-40);
	    userName.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
	    pane.getChildren().add(userName);
	    
	    TextField passWord = new TextField();
	    passWord.setMaxWidth(400);
	    passWord.setMaxHeight(20);
	    passWord.setPrefSize(80, 6);
	    passWord.setTranslateX(23);
	    passWord.setTranslateY(105);
	    passWord.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
	    pane.getChildren().add(passWord);
	    
	    Button login = new Button();
		login.setText("null");
		login.setTranslateX(-175);
		login.setTranslateY(275);
		login.setStyle("-fx-font-size:40; -fx-background-color:red;");
		login.setMaxWidth(200);
		login.setMaxHeight(50);
		login.setVisible(true);
		pane.getChildren().add(login);
		
		Button exit = new Button();
		exit.setText("null");
		exit.setTranslateX(295);
		exit.setTranslateY(275);
		exit.setStyle("-fx-font-size:40; -fx-background-color:red;");
		exit.setMaxWidth(200);
		exit.setMaxHeight(50);
		exit.setVisible(false);
		pane.getChildren().add(exit);
		

		// Button press
		login.setOnAction((event) -> {
		    if(checkUser(userName.getText())){
		    	if(checkPass(userName.getText(), passWord.getText())){
		    		gameScreen(primaryStage, new CharacterObject());
		    	}
		    	else{
		    		loginScreen(primaryStage, 2);
		    	}
		    }
		    else{
		    	loginScreen(primaryStage, 1);
		    }
		    
		});

		Scene scene = new Scene(pane, viewSizeX, viewSizeY);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void gameScreen(Stage primaryStage, CharacterObject userChar){
		Pane pane = new StackPane();
		BackgroundFill a = new BackgroundFill(Color.WHITE, null, null);
		Background background = new Background(new BackgroundFill[] {a});
		pane.setBackground(background);
		
//		ActorObject userChar = new CharacterObject();
		
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
	
	/**
	* Checks if entered inputed username exists in userPass file.
	* @return If username is valid for login.
	*/
	private boolean checkUser(String input){
		File file = new File("userPass.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				String user = scanner.next();
				if(input.equals(user)){
					scanner.close();
					return true;
				}
				scanner.nextLine();
			}
			scanner.close();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
		
	/**
	* Check if password matches username
	 * @param user Username of the user loggging in.
	 * @return If the password entered is the correct password to the given username.
	*/
	private boolean checkPass(String user, String pass){
		File file = new File("userPass.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				String username = scanner.next();
				if(username.equals(user)){
					String password = scanner.next();
					if(password.equals(pass)){
						scanner.close();
						return true;
					}
				}else{
					scanner.nextLine();
				}
			}
			scanner.close();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}