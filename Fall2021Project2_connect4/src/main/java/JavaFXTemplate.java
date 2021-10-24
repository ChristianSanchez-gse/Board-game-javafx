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
		
		
		
		///////////////////////////////////////
		// setting up the In Game screen
		///////////////////////////////////////
		//Button optionsButton = new Button("Options");
		Button themesButton = new Button("Themes");
		Button undoButton = new Button("Undo");
		Button backButton = new Button("back (this button will be deleted)");
		
		//Drpdown for options
		ChoiceBox<String> optionsDrop = new ChoiceBox<>();
		optionsDrop.getItems().addAll("Options", "how to play", "new game", "exit");
		optionsDrop.setValue("Options");
		
		//Options procedures
		optionsDrop.setOnAction((event)-> {
			Object selectedItem = optionsDrop.getSelectionModel().getSelectedItem();
			if (selectedItem == "how to play") {
		    	primaryStage.setScene(rulesScreen);
		    	
		    } else if (selectedItem == "new game"){
		    	
		    } else if (selectedItem == "exit") {
		    	Platform.exit();
		    }
		});
		
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
		rulesScreen = new Scene(backbox, 800,800);		
		
		
		// Dropdown for themes
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("Theme 1", "Theme 2", "Theme 3");
		choiceBox.setValue("Theme 1");
		
		//choiceBox.addEventHandler(, null);
		
		
		HBox topButtons = new HBox(optionsDrop, choiceBox, undoButton, backButton);
		topButtons.setAlignment(Pos.TOP_CENTER);
		BorderPane inGamePane = new BorderPane();
		// create grid object
		GameGrid gameGrid = new GameGrid();
		

		
		

		
		choiceBox.setOnAction((event) -> {
		    //int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
		    Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();
		    if (selectedItem == "Theme 1") {
		    	// change the window color
		    	gameGrid.changeTheme1();
		    	welcomeScreen.setFill(Color.GREEN);
//		    	ingameScreen.setFill(Color.GREEN);
//		    	optionsScreen.setFill(Color.GREEN);
//		    	rulesScreen.setFill(Color.GREEN);
		    	
		    } else if (selectedItem == "Theme 2"){
		    	gameGrid.changeTheme2();
//		    	welcomeScreen.setFill(Color.AQUAMARINE);
//		    	ingameScreen.setFill(Color.AQUAMARINE);
//		    	optionsScreen.setFill(Color.AQUAMARINE);
//		    	rulesScreen.setFill(Color.AQUAMARINE);
		    } else if (selectedItem == "Theme 3") {
		    	gameGrid.changeTheme3();
//		    	welcomeScreen.setFill(Color.BLUEVIOLET);
//		    	ingameScreen.setFill(Color.BLUEVIOLET);
//		    	optionsScreen.setFill(Color.BLUEVIOLET);
//		    	rulesScreen.setFill(Color.BLUEVIOLET);
		    }
		    
		    
		});



		
		// Setting action for buttons
		undoButton.setOnAction(e -> gameGrid.Undo());
		gameGrid.getGrid().setAlignment(Pos.CENTER);
		
		// My attempt at using a borderpane to set the options at the very top and the game grid in the center
		// of the screen... didnt work but we definitely should use borderpane for this.
		inGamePane.setTop(topButtons);
		inGamePane.setCenter(gameGrid.getGrid());
		VBox bottom = new VBox();
		bottom.getChildren().add(gameGrid.getInfo());
		bottom.setAlignment(Pos.CENTER);
		inGamePane.setBottom(bottom);
		backButton.setOnAction(e -> primaryStage.setScene(welcomeScreen));
		
		
		
		// Creating an array of buttons that will be used in the gridpane
		
		gameGrid.buildArray();
		gameGrid.changeTheme1();// initializing the theme
		ingameScreen = new Scene(inGamePane, 1000,1000);
		
		///////////////////////////////////
		// showing the initial screen
		///////////////////////////////////
		primaryStage.setScene(welcomeScreen);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
	

	
}
