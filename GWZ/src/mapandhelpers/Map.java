package mapandhelpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Objects.ActorObject;
import Objects.CharacterObject;
import Objects.ProjectileObject;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

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
		login.setTranslateX(-175);
		login.setTranslateY(275);
		login.setStyle("-fx-font-size:40; -fx-background-color:none;");
		login.setMaxWidth(200);
		login.setMaxHeight(50);
		login.setVisible(true);
		pane.getChildren().add(login);
		
		Button exit = new Button();
		exit.setTranslateX(295);
		exit.setTranslateY(275);
		exit.setStyle("-fx-font-size:40; -fx-background-color:none;");
		exit.setMaxWidth(200);
		exit.setMaxHeight(50);
		exit.setVisible(true);
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
		
		exit.setOnAction((event) -> {
		    titleScreen(primaryStage);
		});

		Scene scene = new Scene(pane, viewSizeX, viewSizeY);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void gameScreen(Stage primaryStage, CharacterObject userChar){
		Random rand = new Random();
		Pane pane = new StackPane();
		BackgroundFill a = new BackgroundFill(Color.WHITE, null, null);
		Background background = new Background(new BackgroundFill[] {a});
		pane.setBackground(background);
		
		ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
		ArrayList<ProjectileObject> bulletRef = new ArrayList<ProjectileObject>();
		ArrayList<Integer> bulletLife = new ArrayList<Integer>();
		
		//File and ImageView for the skill screen
		File ssFile = new File("skills_screen.png");
		ImageView ssIv = new ImageView(new Image(ssFile.toURI().toString(),viewSizeX,viewSizeY,false,false));
		ssIv.setTranslateX(0);
		ssIv.setTranslateY(0);
		
		//File and ImageView for Pause Menu
		File pFile = new File("Pause_Menu.png");
		ImageView pView = new ImageView(new Image(pFile.toURI().toString(),viewSizeX/2,viewSizeY,false,false));
		ssIv.setTranslateX(0);
		ssIv.setTranslateY(0);
		
//		ActorObject userChar = new CharacterObject();
		
		Circle userPlace = new Circle(50);
		userPlace.setTranslateX(0);
		userPlace.setTranslateY(0);
		userPlace.setFill(Color.BLUE);
		userPlace.setStroke(Color.BLACK);
		pane.getChildren().add(userPlace);
		
		Rectangle evil = new Rectangle(50,50);
		CharacterObject evilRef = new CharacterObject();
		evilRef.setPosX(0);
		evilRef.setPosY(-300);
		evilRef.setHealth(50);
		evil.setTranslateX(evilRef.getPosX());
		evil.setTranslateY(evilRef.getPosY());
		evil.setFill(Color.PURPLE);
		evil.setStroke(Color.BLACK);
		pane.getChildren().add(evil);
		
		
		
		
		File akFile = new File("AK47.png");
		ActorObject ak47 = new ActorObject();
	    ImageView akIv = new ImageView(new Image(akFile.toURI().toString(),ak47.getSizeX(),ak47.getSizeY(),false,false));
	    pane.getChildren().add(akIv);
	    ak47.setPosX(userChar.getPosX()+50);
	    akIv.setTranslateX(ak47.getPosX()+50);
	    
	    File hatFile = new File("magic_wizard_hat.png");
		ActorObject hatObject = new ActorObject();
	    ImageView hatIv = new ImageView(new Image(hatFile.toURI().toString(),100,100,false,false));
	    pane.getChildren().add(hatIv);
	    hatObject.setPosX(userChar.getPosX());
	    hatObject.setPosY(userChar.getPosY()-60);
	    hatIv.setTranslateX(hatObject.getPosX());
	    hatIv.setTranslateY(hatObject.getPosY());
	
		Scene scene = new Scene(pane, viewSizeX, viewSizeY);
		
		scene.setOnMousePressed(new EventHandler<MouseEvent>(){
			@Override public void handle(MouseEvent event) {
				Rectangle rect1 = new Rectangle();
				ProjectileObject rectRef = new ProjectileObject(0, userChar, event.getSceneX()-500, event.getSceneY()-400, userChar.getPosX(), userChar.getPosY());
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
		      }
		});

		scene.setOnKeyPressed(e -> {
			switch (e.getCode()){
			case S:
				userPlace.requestFocus();
				userChar.setPosY(userChar.getPosY()+100);
				userPlace.setTranslateY(userChar.getPosY());
				
				akIv.requestFocus();
				ak47.setPosY(userChar.getPosY()+50);
				akIv.setTranslateY(ak47.getPosY());
				
				hatIv.requestFocus();
				hatObject.setPosX(userChar.getPosX());
				hatObject.setPosY(userChar.getPosY()-60);
				hatIv.setTranslateX(hatObject.getPosX());
				hatIv.setTranslateY(hatObject.getPosY());
				
				break;
			case W:
				userPlace.requestFocus();
				userChar.setPosY(userChar.getPosY()-100);
				userPlace.setTranslateY(userChar.getPosY());
				
				akIv.requestFocus();
				ak47.setPosY(userChar.getPosY()-50);
				akIv.setTranslateY(ak47.getPosY());
				
				hatIv.requestFocus();
				hatObject.setPosX(userChar.getPosX());
				hatObject.setPosY(userChar.getPosY()-60);
				hatIv.setTranslateX(hatObject.getPosX());
				hatIv.setTranslateY(hatObject.getPosY());
				break;
			case D:
				userPlace.requestFocus();
				userChar.setPosX(userChar.getPosX()+100);
				userPlace.setTranslateX(userChar.getPosX());
				
				akIv.requestFocus();
				ak47.setPosX(userChar.getPosX()+50);
				akIv.setTranslateX(ak47.getPosX());
				
				hatIv.requestFocus();
				hatObject.setPosX(userChar.getPosX());
				hatObject.setPosY(userChar.getPosY()-60);
				hatIv.setTranslateX(hatObject.getPosX());
				hatIv.setTranslateY(hatObject.getPosY());
				break;
			case A:
				userPlace.requestFocus();
				userChar.setPosX(userChar.getPosX()-100);
				userPlace.setTranslateX(userChar.getPosX());
				
				akIv.requestFocus();
				ak47.setPosX(userChar.getPosX()-50);
				akIv.setTranslateX(ak47.getPosX());
				
				hatIv.requestFocus();
				hatObject.setPosX(userChar.getPosX());
				hatObject.setPosY(userChar.getPosY()-60);
				hatIv.setTranslateX(hatObject.getPosX());
				hatIv.setTranslateY(hatObject.getPosY());
				break;
			case P:
				if(pane.getChildren().contains(ssIv)){
					pane.getChildren().remove(ssIv);
				}
				else{
					pane.getChildren().add(ssIv);
				}
				break;
			case ESCAPE:
				if(pane.getChildren().contains(pView)){
					pane.getChildren().remove(pView);
				}
				else{
					pane.getChildren().add(pView);
				}
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
				if (now - lastUpdate >= 10_000_000) {
                    lastUpdate = now ;
                    for(int i= 0; i < bullets.size(); i++){
                    	
                    	double newY = bulletRef.get(i).getPosY()+(((bulletRef.get(i).getDestY() - bulletRef.get(i).getOrigY())/200)*5);
                    	//ensure projectile moves to end of screen
                    	if((newY >= 400)){
                    		bulletRef.get(i).setPosY(400);
                    	} else if(newY <= -400){
                    		bulletRef.get(i).setPosY(-400);
                    	} else {
                    		bulletRef.get(i).setPosY(bulletRef.get(i).getPosY()+(((bulletRef.get(i).getDestY() - bulletRef.get(i).getOrigY())/200)*5));
                    	}
                    	
                    	double newX = bulletRef.get(i).getPosX()+(((bulletRef.get(i).getDestX() - bulletRef.get(i).getOrigX())/200)*5);
                    	if((newX >= 500)){
                    		bulletRef.get(i).setPosX(500);
                    	} else if(newX <= -500){
                    		bulletRef.get(i).setPosX(-500);
                    	} else {
                    		bulletRef.get(i).setPosX(bulletRef.get(i).getPosX()+(((bulletRef.get(i).getDestX() - bulletRef.get(i).getOrigX())/200)*5));
                    	}
                        	
                    	bullets.get(i).setTranslateY(bulletRef.get(i).getPosY());
                    	bullets.get(i).setTranslateX(bulletRef.get(i).getPosX());
                    	bulletLife.set(i, bulletLife.get(i) + 1); //increment life by 1
					}
                    
                    for(int i = bullets.size() - 1; i >= 0; i--){
                    	ProjectileObject ref = bulletRef.get(i);
                    	if(bulletLife.get(i) > 200 || ref.getPosX() <= -500 || ref.getPosX() >= 500 || ref.getPosY() >= 400 || ref.getPosY() <= -400){
                    		pane.getChildren().remove(bullets.get(i));
                    		bullets.remove(i);
                    		bulletRef.remove(i);
                    		bulletLife.remove(i);
                    	}
                    }
                }
			}
		}.start();
		
		new AnimationTimer(){
			private long lastUpdate = 0;
			private long lastHit = 0;
			@Override
			public void handle(long now){
				if (now - lastUpdate >= 100_000_000) {
                    lastUpdate = now ;
                    evil.setFill(Color.PURPLE);
                    int n = rand.nextInt(4);
                    if(n == 0){
                		evilRef.setPosY(evilRef.getPosY()-10);
                		evil.setTranslateY(evilRef.getPosY());
                	}
                	else if(n == 1){
                		evilRef.setPosY(evilRef.getPosY()+10);
                		evil.setTranslateY(evilRef.getPosY());
                	}
                	else if(n == 2){
                		evilRef.setPosX(evilRef.getPosX()-10);
                		evil.setTranslateX(evilRef.getPosX());
                	}
                	else if(n == 3){
                		evilRef.setPosX(evilRef.getPosX()+10);
                		evil.setTranslateX(evilRef.getPosX());
                	}
                    for(int i = 0; i < bullets.size(); i++){
                    	double leftBound = evilRef.getPosX() - (evilRef.getSizeX()/2);
                    	double rightBound = evilRef.getPosX() + (evilRef.getSizeY()/2);
                    	double topBound = evilRef.getPosY() - (evilRef.getSizeY()/2);
                    	double bottomBound = evilRef.getPosY() + (evilRef.getSizeY()/2);
                    	ProjectileObject bullet_ = bulletRef.get(i);
                    	if(bullet_.getPosX() >= leftBound && bullet_.getPosX() <= rightBound){ //falls in between right and left bounds
                    		if(bullet_.getPosY() >= topBound && bullet_.getPosY() <= bottomBound){
                    			if(now - lastHit >= 300_000_000){ //invulnerable period
                    			lastHit = now;
                    			evil.setFill(Color.AQUA);
                    			evilRef.reduceHealth(10);
                    			}
                    		}
                    	}
                    	if(evilRef.getHealth() == 0){
                    		pane.getChildren().remove(evil);
                    		break;
                    	}
                    }
                }
			}
		}.start();
		
	}
	
	public void skillScreen(Stage primaryStage, Pane pane){
		File file = new File("skills_screen.png");
		ImageView iv = new ImageView(new Image(file.toURI().toString(),viewSizeX,viewSizeY,false,false));
		iv.setTranslateX(0);
		iv.setTranslateY(0);
		pane.getChildren().add(iv);
		System.out.println("Skill Screen open");
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