package mapandhelpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
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
import Objects.ProjectileObject;

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
		
		ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
		ArrayList<ProjectileObject> bulletRef = new ArrayList<ProjectileObject>();
		ArrayList<Integer> bulletLife = new ArrayList<Integer>();
		
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
				Rectangle rect1 = new Rectangle();
				ProjectileObject rectRef = new ProjectileObject(0, userChar);
				rectRef.setPosX(rectRef.getChar().getPosX());
				rectRef.setPosY(rectRef.getChar().getPosY());
				rect1.setWidth(5);
				rect1.setHeight(5);
				rect1.setFill(Color.YELLOW);
				rect1.setStroke(Color.BLACK);
				rect1.setTranslateX(rectRef.getPosX());
				rect1.setTranslateY(rectRef.getPosY());
				pane.getChildren().add(rect1);
				rect1.requestFocus();
				bullets.add(rect1);
				bulletRef.add(rectRef);
				bulletLife.add(0);
				break;
			case S:
				Rectangle rect2 = new Rectangle();
				ProjectileObject rectRef2 = new ProjectileObject(1, userChar);
				rectRef2.setPosX(rectRef2.getChar().getPosX());
				rectRef2.setPosY(rectRef2.getChar().getPosY());
				rect2.setWidth(5);
				rect2.setHeight(5);
				rect2.setFill(Color.YELLOW);
				rect2.setStroke(Color.BLACK);
				rect2.setTranslateX(rectRef2.getPosX());
				rect2.setTranslateY(rectRef2.getPosY());
				pane.getChildren().add(rect2);
				rect2.requestFocus();
				bullets.add(rect2);
				bulletRef.add(rectRef2);
				bulletLife.add(0);
				break;
			case A:
				Rectangle rect3 = new Rectangle();
				ProjectileObject rectRef3 = new ProjectileObject(2, userChar);
				rectRef3.setPosX(rectRef3.getChar().getPosX());
				rectRef3.setPosY(rectRef3.getChar().getPosY());
				rect3.setWidth(5);
				rect3.setHeight(5);
				rect3.setFill(Color.YELLOW);
				rect3.setStroke(Color.BLACK);
				rect3.setTranslateX(rectRef3.getPosX());
				rect3.setTranslateY(rectRef3.getPosY());
				pane.getChildren().add(rect3);
				rect3.requestFocus();
				bullets.add(rect3);
				bulletRef.add(rectRef3);
				bulletLife.add(0);
				break;
			case D:
				Rectangle rect4 = new Rectangle();
				ProjectileObject rectRef4 = new ProjectileObject(3, userChar);
				rectRef4.setPosX(rectRef4.getChar().getPosX());
				rectRef4.setPosY(rectRef4.getChar().getPosY());
				rect4.setWidth(5);
				rect4.setHeight(5);
				rect4.setFill(Color.YELLOW);
				rect4.setStroke(Color.BLACK);
				rect4.setTranslateX(rectRef4.getPosX());
				rect4.setTranslateY(rectRef4.getPosY());
				pane.getChildren().add(rect4);
				rect4.requestFocus();
				bullets.add(rect4);
				bulletRef.add(rectRef4);
				bulletLife.add(0);
				break;
			default:
				break;
			}	
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new AnimationTimer(){
			private long lastUpdate = 0;
			@Override
			public void handle(long now){
				if (now - lastUpdate >= 1000_000_000) {
                    lastUpdate = now ;
                    for(int i= 0; i < bullets.size(); i++){
                    	if(bulletRef.get(i).getOrient() == 0){
                    		bulletRef.get(i).setPosY(bulletRef.get(i).getPosY()-100);
                    		bullets.get(i).setTranslateY(bulletRef.get(i).getPosY());
                    		bulletLife.set(i, bulletLife.get(i) + 1); //increment life by 1
                    		System.out.println("Bullet Life is " + bulletLife.get(i));
                    	}
                    	else if(bulletRef.get(i).getOrient() == 1){
                    		bulletRef.get(i).setPosY(bulletRef.get(i).getPosY()+100);
                    		bullets.get(i).setTranslateY(bulletRef.get(i).getPosY());
                    		bulletLife.set(i, bulletLife.get(i) + 1); //increment life by 1
                    		System.out.println("Bullet Life is " + bulletLife.get(i));
                    	}
                    	else if(bulletRef.get(i).getOrient() == 2){
                    		bulletRef.get(i).setPosX(bulletRef.get(i).getPosX()-100);
                    		bullets.get(i).setTranslateX(bulletRef.get(i).getPosX());
                    		bulletLife.set(i, bulletLife.get(i) + 1); //increment life by 1
                    		System.out.println("Bullet Life is " + bulletLife.get(i));
                    	}
                    	else if(bulletRef.get(i).getOrient() == 3){
                    		bulletRef.get(i).setPosX(bulletRef.get(i).getPosX()+100);
                    		bullets.get(i).setTranslateX(bulletRef.get(i).getPosX());
                    		bulletLife.set(i, bulletLife.get(i) + 1); //increment life by 1
                    		System.out.println("Bullet Life is " + bulletLife.get(i));
                    	}
					}
                    
                    for(int i = bullets.size() - 1; i >= 0; i--){
                    	if(bulletLife.get(i) > 4){
                    		System.out.print("remove bullet at index " + i + "\n");
                    		pane.getChildren().remove(bullets.get(i));
                    		bullets.remove(i);
                    		bulletRef.remove(i);
                    		bulletLife.remove(i);
                    	}
                    }
                }
			}
		}.start();
		
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