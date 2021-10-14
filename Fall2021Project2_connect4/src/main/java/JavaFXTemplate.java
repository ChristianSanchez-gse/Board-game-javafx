import java.awt.Insets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	CheckerPiece[][] checkerArr = new CheckerPiece[7][6];
	GridPane gameGrid = new GridPane();
	
	
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
		
		HBox topButtons = new HBox(optionsButton, themesButton, undoButton, backButton);
		BorderPane inGamePane = new BorderPane();
		
		// My attempt at using a borderpane to set the options at the very top and the game grid in the center
		// of the screen... didnt work but we definitely should use borderpane for this.
		inGamePane.setTop(topButtons);
		inGamePane.setCenter(gameGrid);
		backButton.setOnAction(e -> primaryStage.setScene(welcomeScreen));
		
		// Creating an array of buttons that will be used in the gridpane
		buildArray();
		setCheckerActions();
		ingameScreen = new Scene(inGamePane, 700,700);
		
		
		///////////////////////////////////
		// showing the initial screen
		///////////////////////////////////
		primaryStage.setScene(welcomeScreen);
		primaryStage.show();
	}
	
	// This function adds buttons to both buttonArr
	// and gameGrid which are declared at the top of this class.
	public void buildArray() {
		//gameGrid.setPadding(new Insets(50, 50, 50, 50));
		gameGrid.setVgap(20);
		gameGrid.setHgap(20);
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				checkerArr[i][j] = new CheckerPiece( i + ", " + j);
				gameGrid.add(checkerArr[i][j],i,j);
			}
		}
		
	}
	public void setCheckerActions() {
		checkerArr[0][0].setOnAction(e -> checkerArr[0][0].setStyle("-fx-background-color: #ff0000; "));
	}
	
}
