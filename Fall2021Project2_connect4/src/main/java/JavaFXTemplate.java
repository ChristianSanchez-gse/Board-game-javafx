import java.awt.Insets;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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
		
		Button play = new Button("Welcome Screen. Press this to move on");
		VBox box = new VBox(play);
		play.setOnAction(e -> primaryStage.setScene(ingameScreen));
		box.setAlignment(Pos.CENTER);
		welcomeScreen = new Scene(box, 800,800);
		
		
		
		///////////////////////////////////////
		// setting up the In Game screen
		///////////////////////////////////////
		Button optionsButton = new Button("Options");
		Button themesButton = new Button("Themes");
		Button undoButton = new Button("Undo");
		Button backButton = new Button("back (this button will be deleted)");
		
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("Theme 1", "Theme 2", "Theme 3");
		choiceBox.setValue("Theme 1");
		
		//choiceBox.addEventHandler(, null);
		
		
		HBox topButtons = new HBox(optionsButton, choiceBox, undoButton, backButton);
		topButtons.setAlignment(Pos.TOP_CENTER);
		BorderPane inGamePane = new BorderPane();
		// create grid object
		GameGrid gameGrid = new GameGrid();

		
		

		
		choiceBox.setOnAction((event) -> {
		    //int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
		    Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();
		    if (selectedItem == "Theme 1") {
		    	gameGrid.changeTheme1();
		    } else if (selectedItem == "Theme 2"){
		    	gameGrid.changeTheme2();
		    } else if (selectedItem == "Theme 3") {
		    	gameGrid.changeTheme3();
		    }
		    
		    
		});



		
		// Setting action for buttons
		undoButton.setOnAction(e -> gameGrid.Undo());
		gameGrid.getGrid().setAlignment(Pos.CENTER);
		
		// My attempt at using a borderpane to set the options at the very top and the game grid in the center
		// of the screen... didnt work but we definitely should use borderpane for this.
		inGamePane.setTop(topButtons);
		inGamePane.setCenter(gameGrid.getGrid());
		backButton.setOnAction(e -> primaryStage.setScene(welcomeScreen));
		
		
		// Creating an array of buttons that will be used in the gridpane
		
		gameGrid.buildArray();
		gameGrid.changeTheme1();// initializing the theme
		//setCheckerActions();
		ingameScreen = new Scene(inGamePane, 800,800);
		
		///////////////////////////////////
		// showing the initial screen
		///////////////////////////////////
		primaryStage.setScene(welcomeScreen);
		//primaryStage.setFullScreen(true);
		primaryStage.show();
	}
	

	
}
