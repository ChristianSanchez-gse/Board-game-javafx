import java.awt.Insets;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {
	// The different scenes that will be
	// used within the program.
	Scene welcomeScreen;
	Scene ingameScreen;
	Scene optionsScreen;
	Scene rulesScreen;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Setting up the program details and advancing to the welcome screen.
		primaryStage.setTitle("Connect 4 by Christian Sanchez and Andrew Mina");
		
		//////////////////////////////////////
		// setting up the Welcome screen
		//////////////////////////////////////
		Image image = new Image(getClass().getResourceAsStream("playButton.png"));
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		Button play = new Button();
		play.setGraphic(imageView);
		VBox box = new VBox(play);
		play.setOnAction(e -> primaryStage.setScene(ingameScreen));
		box.setAlignment(Pos.CENTER);
		welcomeScreen = new Scene(box, 1000,1000);
		
		
		
		Button undoButton = new Button("Undo");
		
		//Drpdown for options
		ChoiceBox<String> optionsDrop = new ChoiceBox<>();
		optionsDrop.getItems().addAll("Options", "How To Play", "New Game", "Exit");
		optionsDrop.setValue("Options");
		

		
		// rules screen info
		Text rules = new Text();
		rules.setText("These are the rules");
		Image image2 = new Image(getClass().getResourceAsStream("c4Rules.png"));
		ImageView imageView2 = new ImageView();
		imageView2.setImage(image2);
		imageView2.setFitWidth(700);
		imageView2.setFitHeight(600);
		rules.setX(100);
		rules.setY(100);
		
		
		
		Button rulesback = new Button("Back");
		VBox backbox = new VBox(rulesback, imageView2);
		rulesback.setOnAction((event) -> {
			optionsDrop.setValue("Options");
			primaryStage.setScene(ingameScreen);	
		});
		rulesScreen = new Scene(backbox, 1000,1000);		
		
		
		// Dropdown for themes
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("Default", "Triangle", "Bruh");
		choiceBox.setValue("Default");
		
		//choiceBox.addEventHandler(, null);
		
		
		HBox topButtons = new HBox(optionsDrop, choiceBox, undoButton);
		topButtons.setAlignment(Pos.TOP_CENTER);
		BorderPane inGamePane = new BorderPane();
		// create grid object
		GameGrid gameGrid = new GameGrid();
		

		
		

		
		choiceBox.setOnAction((event) -> {
		    //int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
		    Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();
		    if (selectedItem == "Default") {
		    	// change the window color
		    	gameGrid.changeTheme2();
		    	//welcomeScreen.setFill(Color.GREEN);
//		    	ingameScreen.setFill(Color.GREEN);
//		    	optionsScreen.setFill(Color.GREEN);
//		    	rulesScreen.setFill(Color.GREEN);
		    	
		    } else if (selectedItem == "Triangle"){
		    	gameGrid.changeTheme3();
//		    	welcomeScreen.setFill(Color.AQUAMARINE);
//		    	ingameScreen.setFill(Color.AQUAMARINE);
//		    	optionsScreen.setFill(Color.AQUAMARINE);
//		    	rulesScreen.setFill(Color.AQUAMARINE);
		    } else if (selectedItem == "Bruh") {
		    	gameGrid.changeTheme1();
//		    	welcomeScreen.setFill(Color.BLUEVIOLET);
//		    	ingameScreen.setFill(Color.BLUEVIOLET);
//		    	optionsScreen.setFill(Color.BLUEVIOLET);
//		    	rulesScreen.setFill(Color.BLUEVIOLET);
		    }
		    
		    
		});

		//Options procedures
		optionsDrop.setOnAction((event)-> {
			Object selectedItem = optionsDrop.getSelectionModel().getSelectedItem();
			if (selectedItem == "How To Play") {
		    	primaryStage.setScene(rulesScreen);
		    	
		    } else if (selectedItem == "New Game"){
		    	optionsDrop.setValue("Options");
		    	gameGrid.newGame();
		    	primaryStage.setScene(welcomeScreen);
		    	gameGrid.setInfoDefault();
		    } else if (selectedItem == "Exit") {
		    	Platform.exit();
		    }
		});

		
		// Setting action for buttons
		undoButton.setOnAction(e -> gameGrid.Undo());
		gameGrid.getGrid().setAlignment(Pos.CENTER);
		
		// borderpane set up
		inGamePane.setTop(topButtons);
		inGamePane.setCenter(gameGrid.getGrid());
		VBox bottom = new VBox();
		bottom.getChildren().add(gameGrid.getInfo());
		bottom.setAlignment(Pos.CENTER);
		inGamePane.setBottom(bottom);
		
		
		
		
		// Creating an array of buttons that will be used in the gridpane
		
		gameGrid.buildArray();
		gameGrid.changeTheme2();// initializing the theme
		ingameScreen = new Scene(inGamePane, 1000,1000);
		
		///////////////////////////////////
		// showing the initial screen
		///////////////////////////////////
		primaryStage.setScene(welcomeScreen);
		//primaryStage.setMaximized(true);
		primaryStage.show();
	}
	

	
}
