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
	
	
	// The gridpane and array that will be used for buttons
	
//	public void setTheme(int themeChosen) {
//		if (themeChosen == 1) {
//			theme = 1;
//		} else if (themeChosen == 2) {
//			theme = 2;
//			
//		}else if (themeChosen == 3) {
//			theme = 3;
//		}
//	}
//	
//	public static int getTheme() {
//		return theme;
//	}
	
	
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
		welcomeScreen = new Scene(box, 700,700);
		
		
		
		///////////////////////////////////////
		// setting up the In Game screen
		///////////////////////////////////////
		Button optionsButton = new Button("Options");
		Button themesButton = new Button("Themes");
		Button undoButton = new Button("Undo");
		Button backButton = new Button("back (this button will be deleted)");
		
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("Theme 1", "Theme 2", "Theme 3");
		choiceBox.setValue("Theme 3");
		
		//choiceBox.addEventHandler(, null);
		
		HBox topButtons = new HBox(optionsButton, choiceBox, undoButton, backButton);
		topButtons.setAlignment(Pos.TOP_CENTER);
		BorderPane inGamePane = new BorderPane();
		// create grid object
		GameGrid gameGrid = new GameGrid();
		
		
		if (choiceBox.getValue() == "Theme 1") {
			choiceBox.setOnAction(e -> gameGrid.changeTheme(1));
			//gameGrid.changeTheme(1);
			gameGrid.buildArray();
		} else if (choiceBox.getValue() == "Theme 2") {
			choiceBox.setOnAction(e -> gameGrid.changeTheme(2));
			//gameGrid.changeTheme(2);
			gameGrid.buildArray();
		}else if (choiceBox.getValue() == "Theme 3") {
			choiceBox.setOnAction(e -> gameGrid.changeTheme(3));
			//gameGrid.changeTheme(3);
			gameGrid.buildArray();
		}
		
		gameGrid.getGrid().setAlignment(Pos.CENTER);
		
		// My attempt at using a borderpane to set the options at the very top and the game grid in the center
		// of the screen... didnt work but we definitely should use borderpane for this.
		inGamePane.setTop(topButtons);
		inGamePane.setCenter(gameGrid.getGrid());
		backButton.setOnAction(e -> primaryStage.setScene(welcomeScreen));
		
		
		// Creating an array of buttons that will be used in the gridpane
		
		gameGrid.buildArray();
		//setCheckerActions();
		ingameScreen = new Scene(inGamePane, 700,700);
		
		///////////////////////////////////
		// showing the initial screen
		///////////////////////////////////
		primaryStage.setScene(welcomeScreen);
		//primaryStage.setFullScreen(true);
		primaryStage.show();
	}
	
	// This function adds buttons to both buttonArr
	// and gameGrid which are declared at the top of this class.

	

	
}
