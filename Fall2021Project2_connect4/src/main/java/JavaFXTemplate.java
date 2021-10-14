import java.awt.Insets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
	Button[][] buttonArr = new Button[7][6];
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
		play.setOnAction(e -> primaryStage.setScene(ingameScreen));
		VBox box = new VBox(play);
		box.setAlignment(Pos.CENTER);
		welcomeScreen = new Scene(box, 700,700);
		
		
		
		///////////////////////////////////////
		// setting up the In Game screen
		///////////////////////////////////////
		Button back = new Button("This is the in game window, press this to go back");
		back.setOnAction(e -> primaryStage.setScene(welcomeScreen));
		
		// Creating an array of buttons that will be used in the gridpane
		buildArray();
		VBox box2 = new VBox(back, gameGrid);
		ingameScreen = new Scene(box2, 700,700);
		
		
		///////////////////////////////////
		// showing the initial screen
		///////////////////////////////////
		primaryStage.setScene(welcomeScreen);
		primaryStage.show();
	}
	public void buildArray() {
		//gameGrid.setPadding(new Insets(50, 50, 50, 50));
		gameGrid.setVgap(5);
		gameGrid.setHgap(5);
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				buttonArr[i][j] = new Button( i + ", " + j);
				gameGrid.add(new Button(i +", " + j), i, j);
			}
		}
	}
	
	
}
