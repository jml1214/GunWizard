package mapandhelpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Objects.Abilities;
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

		//player bullets
		ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
		ArrayList<ProjectileObject> bulletRef = new ArrayList<ProjectileObject>();
		ArrayList<Integer> bulletLife = new ArrayList<Integer>();

		//enemy bullets
		ArrayList<Rectangle> eBullets = new ArrayList<Rectangle>();
		ArrayList<ProjectileObject> eBulletRef = new ArrayList<ProjectileObject>();

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

		//Start of this push
		Rectangle healthBar = new Rectangle(evilRef.getHealth(), 15);
		ActorObject healthBarRef = new ActorObject();
		healthBar.setFill(Color.RED);
		healthBar.setStroke(Color.BLACK);
		healthBarRef.setPosX(250);
		healthBarRef.setPosY(350);
		healthBar.setTranslateX(healthBarRef.getPosX());
		healthBar.setTranslateY(healthBarRef.getPosY());


		Rectangle healthBack = new Rectangle(evilRef.getHealth(), 15);
		ActorObject healthBackRef = new ActorObject();
		healthBack.setFill(Color.GREY);
		healthBack.setStroke(Color.BLACK);
		healthBackRef.setPosX(250);
		healthBackRef.setPosY(350);
		healthBack.setTranslateX(healthBackRef.getPosX());
		healthBack.setTranslateY(healthBackRef.getPosY());

		Rectangle healthBarP = new Rectangle(userChar.getHealth(), 15);
		ActorObject healthBarRefP = new ActorObject();
		healthBarP.setFill(Color.RED);
		healthBarP.setStroke(Color.BLACK);
		healthBarRefP.setPosX(-250);
		healthBarRefP.setPosY(350);
		healthBarP.setTranslateX(healthBarRefP.getPosX());
		healthBarP.setTranslateY(healthBarRefP.getPosY());


		Rectangle healthBackP = new Rectangle(userChar.getHealth(), 15);
		ActorObject healthBackRefP = new ActorObject();
		healthBackP.setFill(Color.GREY);
		healthBackP.setStroke(Color.BLACK);
		healthBackRefP.setPosX(-250);
		healthBackRefP.setPosY(350);
		healthBackP.setTranslateX(healthBackRefP.getPosX());
		healthBackP.setTranslateY(healthBackRefP.getPosY());

		pane.getChildren().add(healthBack);
		pane.getChildren().add(healthBar);

		pane.getChildren().add(healthBackP);
		pane.getChildren().add(healthBarP);
		//End this push

		//Buttons for the Skill Screen
		Button GALevel1 = new Button();
		GALevel1.setTranslateX(-75);
		GALevel1.setTranslateY(-220);
		GALevel1.setStyle("-fx-font-size:40; -fx-background-color:none;");
		GALevel1.setPrefWidth(60);
		GALevel1.setPrefHeight(60);
		GALevel1.setMaxHeight(60);
		GALevel1.setMinHeight(60);
		GALevel1.setMaxWidth(60);
		GALevel1.setMinWidth(60);
		GALevel1.setVisible(true);

		Button GALevel2 = new Button();
		GALevel2.setTranslateX(75);
		GALevel2.setTranslateY(-220);
		GALevel2.setStyle("-fx-font-size:40; -fx-background-color:none;");
		GALevel2.setPrefWidth(60);
		GALevel2.setPrefHeight(60);
		GALevel2.setMaxHeight(60);
		GALevel2.setMinHeight(60);
		GALevel2.setMaxWidth(60);
		GALevel2.setMinWidth(60);
		GALevel2.setVisible(true);

		Button GALevel3 = new Button();
		GALevel3.setTranslateX(235);
		GALevel3.setTranslateY(-220);
		GALevel3.setStyle("-fx-font-size:40; -fx-background-color:none;");
		GALevel3.setPrefWidth(60);
		GALevel3.setPrefHeight(60);
		GALevel3.setMaxHeight(60);
		GALevel3.setMinHeight(60);
		GALevel3.setMaxWidth(60);
		GALevel3.setMinWidth(60);
		GALevel3.setVisible(true);

		Button MALevel1 = new Button();
		MALevel1.setTranslateX(-75);
		MALevel1.setTranslateY(-100);
		MALevel1.setStyle("-fx-font-size:40; -fx-background-color:none;");
		MALevel1.setPrefWidth(60);
		MALevel1.setPrefHeight(60);
		MALevel1.setMaxHeight(60);
		MALevel1.setMinHeight(60);
		MALevel1.setMaxWidth(60);
		MALevel1.setMinWidth(60);
		MALevel1.setVisible(true);

		Button MALevel2 = new Button();
		MALevel2.setTranslateX(75);
		MALevel2.setTranslateY(-100);
		MALevel2.setStyle("-fx-font-size:40; -fx-background-color:none;");
		MALevel2.setPrefWidth(60);
		MALevel2.setPrefHeight(60);
		MALevel2.setMaxHeight(60);
		MALevel2.setMinHeight(60);
		MALevel2.setMaxWidth(60);
		MALevel2.setMinWidth(60);
		MALevel2.setVisible(true);

		Button MALevel3 = new Button();
		MALevel3.setTranslateX(235);
		MALevel3.setTranslateY(-100);
		MALevel3.setStyle("-fx-font-size:40; -fx-background-color:none;");
		MALevel3.setPrefWidth(60);
		MALevel3.setPrefHeight(60);
		MALevel3.setMaxHeight(60);
		MALevel3.setMinHeight(60);
		MALevel3.setMaxWidth(60);
		MALevel3.setMinWidth(60);
		MALevel3.setVisible(true);

		Button RSLevel1 = new Button();
		RSLevel1.setTranslateX(-75);
		RSLevel1.setTranslateY(25);
		RSLevel1.setStyle("-fx-font-size:40; -fx-background-color:none;");
		RSLevel1.setPrefWidth(60);
		RSLevel1.setPrefHeight(60);
		RSLevel1.setMaxHeight(60);
		RSLevel1.setMinHeight(60);
		RSLevel1.setMaxWidth(60);
		RSLevel1.setMinWidth(60);
		RSLevel1.setVisible(true);

		Button RSLevel2 = new Button();
		RSLevel2.setTranslateX(75);
		RSLevel2.setTranslateY(25);
		RSLevel2.setStyle("-fx-font-size:40; -fx-background-color:none;");
		RSLevel2.setPrefWidth(60);
		RSLevel2.setPrefHeight(60);
		RSLevel2.setMaxHeight(60);
		RSLevel2.setMinHeight(60);
		RSLevel2.setMaxWidth(60);
		RSLevel2.setMinWidth(60);
		RSLevel2.setVisible(true);

		Button RSLevel3 = new Button();
		RSLevel3.setTranslateX(235);
		RSLevel3.setTranslateY(25);
		RSLevel3.setStyle("-fx-font-size:40; -fx-background-color:none;");
		RSLevel3.setPrefWidth(60);
		RSLevel3.setPrefHeight(60);
		RSLevel3.setMaxHeight(60);
		RSLevel3.setMinHeight(60);
		RSLevel3.setMaxWidth(60);
		RSLevel3.setMinWidth(60);
		RSLevel3.setVisible(true);

		Button HPLevel1 = new Button();
		HPLevel1.setTranslateX(-75);
		HPLevel1.setTranslateY(150);
		HPLevel1.setStyle("-fx-font-size:40; -fx-background-color:none;");
		HPLevel1.setPrefWidth(60);
		HPLevel1.setPrefHeight(60);
		HPLevel1.setMaxHeight(60);
		HPLevel1.setMinHeight(60);
		HPLevel1.setMaxWidth(60);
		HPLevel1.setMinWidth(60);
		HPLevel1.setVisible(true);

		Button HPLevel2 = new Button();
		HPLevel2.setTranslateX(75);
		HPLevel2.setTranslateY(150);
		HPLevel2.setStyle("-fx-font-size:40; -fx-background-color:none;");
		HPLevel2.setPrefWidth(60);
		HPLevel2.setPrefHeight(60);
		HPLevel2.setMaxHeight(60);
		HPLevel2.setMinHeight(60);
		HPLevel2.setMaxWidth(60);
		HPLevel2.setMinWidth(60);
		HPLevel2.setVisible(true);

		Button HPLevel3 = new Button();
		HPLevel3.setTranslateX(235);
		HPLevel3.setTranslateY(150);
		HPLevel3.setStyle("-fx-font-size:40; -fx-background-color:none;");
		HPLevel3.setPrefWidth(60);
		HPLevel3.setPrefHeight(60);
		HPLevel3.setMaxHeight(60);
		HPLevel3.setMinHeight(60);
		HPLevel3.setMaxWidth(60);
		HPLevel3.setMinWidth(60);
		HPLevel3.setVisible(true);

		Button MPLevel1 = new Button();
		MPLevel1.setTranslateX(-75);
		MPLevel1.setTranslateY(275);
		MPLevel1.setStyle("-fx-font-size:40; -fx-background-color:none;");
		MPLevel1.setPrefWidth(60);
		MPLevel1.setPrefHeight(60);
		MPLevel1.setMaxHeight(60);
		MPLevel1.setMinHeight(60);
		MPLevel1.setMaxWidth(60);
		MPLevel1.setMinWidth(60);
		MPLevel1.setVisible(true);

		Button MPLevel2 = new Button();
		MPLevel2.setTranslateX(75);
		MPLevel2.setTranslateY(275);
		MPLevel2.setStyle("-fx-font-size:40; -fx-background-color:none;");
		MPLevel2.setPrefWidth(60);
		MPLevel2.setPrefHeight(60);
		MPLevel2.setMaxHeight(60);
		MPLevel2.setMinHeight(60);
		MPLevel2.setMaxWidth(60);
		MPLevel2.setMinWidth(60);
		MPLevel2.setVisible(true);

		Button MPLevel3 = new Button();
		MPLevel3.setTranslateX(235);
		MPLevel3.setTranslateY(275);
		MPLevel3.setStyle("-fx-font-size:40; -fx-background-color:none;");
		MPLevel3.setPrefWidth(60);
		MPLevel3.setPrefHeight(60);
		MPLevel3.setMaxHeight(60);
		MPLevel3.setMinHeight(60);
		MPLevel3.setMaxWidth(60);
		MPLevel3.setMinWidth(60);
		MPLevel3.setVisible(true);

		Button skillButtons[] = {GALevel1, GALevel2, GALevel3, MALevel1, MALevel2, MALevel3, RSLevel1, RSLevel2, RSLevel3, HPLevel1, HPLevel2, HPLevel3, MPLevel1, MPLevel2, MPLevel3};

		//Skill Screen button actions
		GALevel1.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("GA",0);
			userChar.setAbilities(abils);
		});
		GALevel2.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("GA",1);
			userChar.setAbilities(abils);
		});
		GALevel3.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("GA",2);
			userChar.setAbilities(abils);
		});
		MALevel1.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("MA",0);
			userChar.setAbilities(abils);
		});
		MALevel2.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("MA",1);
			userChar.setAbilities(abils);
		});
		MALevel3.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("MA",2);
			userChar.setAbilities(abils);
		});
		RSLevel1.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("RS",0);
			userChar.setAbilities(abils);
		});
		RSLevel2.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("RS",1);
			userChar.setAbilities(abils);
		});
		RSLevel3.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("RS",2);
			userChar.setAbilities(abils);
		});
		HPLevel1.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("HP",0);
			userChar.setAbilities(abils);
		});
		HPLevel2.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("HP",1);
			userChar.setAbilities(abils);
		});
		HPLevel3.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("HP",2);
			userChar.setAbilities(abils);
		});
		MPLevel1.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("MP",0);
			userChar.setAbilities(abils);
		});
		MPLevel2.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("MP",1);
			userChar.setAbilities(abils);
		});
		MPLevel3.setOnAction((event) -> {
			Abilities abils = userChar.getAbilities();
			abils.unlockAbility("MP",2);
			userChar.setAbilities(abils);
		});

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

		//Start of this push
		//resume, save, options, quit
		//in-game menu buttons
		Button save = new Button();
		Button options = new Button();
		Button quit = new Button();
		Button resume = new Button();

		//save
		save.setTranslateX(0);
		save.setTranslateY(-65);
		save.setStyle("-fx-font-size:40; -fx-background-color:none;");
		save.setMaxWidth(315);
		save.setMaxHeight(115);
		save.setVisible(true);
		save.setOnAction((event) -> {
			pane.getChildren().remove(pView);
			pane.getChildren().removeAll(resume, save, options, quit);
		});
		//options
		options.setTranslateX(0);
		options.setTranslateY(85);
		options.setStyle("-fx-font-size:40; -fx-background-color:none;");
		options.setMaxWidth(315);
		options.setMaxHeight(115);
		options.setVisible(true);
		options.setOnAction((event) -> {
			pane.getChildren().remove(pView);
			pane.getChildren().removeAll(resume, save, options, quit);
		});
		//quit
		quit.setTranslateX(0);
		quit.setTranslateY(255);
		quit.setStyle("-fx-font-size:40; -fx-background-color:none;");
		quit.setMaxWidth(315);
		quit.setMaxHeight(115);
		quit.setVisible(true);
		quit.setOnAction((event) -> {
			titleScreen(primaryStage);
		});
		//resume
		resume.setTranslateX(0);
		resume.setTranslateY(-210);
		resume.setStyle("-fx-font-size:40; -fx-background-color:none;");
		resume.setMaxWidth(310);
		resume.setMaxHeight(110);
		resume.setVisible(true);
		resume.setOnAction((event) -> {
			pane.getChildren().remove(pView);
			pane.getChildren().removeAll(resume, save, options, quit);
		});
		//end of this push

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
                    for (int i = 0; i < skillButtons.length; i++){
                        pane.getChildren().remove(skillButtons[i]);
                    }
                }
                else{
                    pane.getChildren().add(ssIv);
                    for (int i = 0; i < skillButtons.length; i++){
                        pane.getChildren().add(skillButtons[i]);
                    }
                }
                break;
				//Start of this push
			case ESCAPE:
				if(pane.getChildren().contains(pView)){
					pane.getChildren().remove(pView);
					pane.getChildren().removeAll(resume, save, options, quit);
				}
				else{
					pane.getChildren().add(pView);
					pane.getChildren().addAll(resume, save, options, quit);
				}
				break;
				//end of this push
			default:
				break;
			}	
		});

		primaryStage.setScene(scene);
		primaryStage.show();

		//update projetiles
		new AnimationTimer(){
			private long lastUpdate = 0;
			private long lastDamageTaken = 0;
			@Override
			public void handle(long now){
				if (now - lastUpdate >= 10_000_000) {
					lastUpdate = now ;

					//player bullets
					for(int i= 0; i < bullets.size(); i++){
						double dy = bulletRef.get(i).getDestY() - bulletRef.get(i).getOrigY();
						double dx = bulletRef.get(i).getDestX() - bulletRef.get(i).getOrigX();
						double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
						double speedMult = 5 / distance;

						//scaled to be same speed
						dy = dy * speedMult;
						dx = dx * speedMult;

						double newY = bulletRef.get(i).getPosY() + dy;
						//ensure projectile moves to end of screen
						if((newY >= 400)){
							bulletRef.get(i).setPosY(400);
						} else if(newY <= -400){
							bulletRef.get(i).setPosY(-400);
						} else {
							bulletRef.get(i).setPosY(newY);
						}

						double newX = bulletRef.get(i).getPosX() + dx;
						if((newX >= 500)){
							bulletRef.get(i).setPosX(500);
						} else if(newX <= -500){
							bulletRef.get(i).setPosX(-500);
						} else {
							bulletRef.get(i).setPosX(newX);
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

					//enemy bullets
					for(int i = 0; i < eBullets.size(); i++){
						double dy = eBulletRef.get(i).getDestY() - eBulletRef.get(i).getOrigY();
						double dx = eBulletRef.get(i).getDestX() - eBulletRef.get(i).getOrigX();
						double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
						double speedMult = 5 / distance;

						//scaled to be same speed
						dy = dy * speedMult;
						dx = dx * speedMult;

						double newY = eBulletRef.get(i).getPosY() + dy;
						//ensure projectile moves to end of screen
						if((newY >= 400)){
							eBulletRef.get(i).setPosY(400);
						} else if(newY <= -400){
							eBulletRef.get(i).setPosY(-400);
						} else {
							eBulletRef.get(i).setPosY(newY);
						}

						double newX = eBulletRef.get(i).getPosX() + dx;
						if((newX >= 500)){
							eBulletRef.get(i).setPosX(500);
						} else if(newX <= -500){
							eBulletRef.get(i).setPosX(-500);
						} else {
							eBulletRef.get(i).setPosX(newX);
						}

						eBullets.get(i).setTranslateY(eBulletRef.get(i).getPosY());
						eBullets.get(i).setTranslateX(eBulletRef.get(i).getPosX());

						//check if player was hit
						//player hitbox (square with side of length 70 (fits in circle))
						double leftBound = userChar.getPosX() - (35);
						double rightBound = userChar.getPosX() + (35);
						double topBound = userChar.getPosY() - (35);
						double bottomBound = userChar.getPosY() + (35);
						ProjectileObject bullet_ = eBulletRef.get(i);
						if(bullet_.getPosX() >= leftBound && bullet_.getPosX() <= rightBound){
							if(bullet_.getPosY() >= topBound && bullet_.getPosY() <= bottomBound){
								if(now -lastDamageTaken >= 1000_000_000){
									lastDamageTaken = now;
									userChar.reduceHealth(10);
									healthBarRefP.setPosX(healthBarRefP.getPosX()-5);
									healthBarP.setTranslateX(healthBarRefP.getPosX());
									healthBarP.setWidth(userChar.getHealth());
									System.out.println("Ouch!");
									if(userChar.getHealth() <= 0){
										System.out.println("END GAME!");
									}
								}
							}
						}
					}

					for(int i = eBullets.size() - 1; i >= 0; i--){
						ProjectileObject ref = eBulletRef.get(i);
						if(ref.getPosX() <= -500 || ref.getPosX() >= 500 || ref.getPosY() >= 400 || ref.getPosY() <= -400){
							pane.getChildren().remove(eBullets.get(i));
							eBullets.remove(i);
							eBulletRef.remove(i);
						}
					}
				}
			}
		}.start();


		//control of enemies
		new AnimationTimer(){
			private long lastUpdate = 0;
			private long lastHit = 0;
			private long lastShot = 0;
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

					//shoot
					if(now - lastShot >= 1200_000_000){
						lastShot = now;
						Rectangle rect1 = new Rectangle();
						ProjectileObject shot = new ProjectileObject(0, evilRef, userChar.getPosX(), userChar.getPosY(), evilRef.getPosX(), evilRef.getPosY());
						shot.setPosX(evilRef.getPosX());
						shot.setPosY(evilRef.getPosY());
						rect1.setWidth(5);
						rect1.setHeight(5);
						rect1.setFill(Color.RED);
						rect1.setStroke(Color.BLACK);
						rect1.setTranslateX(shot.getPosX());
						rect1.setTranslateY(shot.getPosY());
						pane.getChildren().add(rect1);
						rect1.requestFocus();
						eBullets.add(rect1);
						eBulletRef.add(shot);

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
									healthBarRef.setPosX(healthBarRef.getPosX()-5);
									healthBar.setTranslateX(healthBarRef.getPosX());
									healthBar.setWidth(evilRef.getHealth());
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