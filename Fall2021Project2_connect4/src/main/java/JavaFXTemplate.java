import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
		// TODO Auto-generated method stub
		primaryStage.setTitle("Connect 4 by Christian Sanchez and Andrew Mina");
		Button back = new Button("Back to welcome");
		
		// Buttons for in game menu
		Button themeB = new Button("Themes");
		Button gamePlayB = new Button("Game Play");
		
		
		//Options screen stuff
		Button rulesB = new Button("rules screen button");
		Button NewGameB = new Button("New Game Button");
		Button ExitB = new Button("Exit Button");
		
		// Grid pane setup
		GridPane gameGrid = new GridPane();
		gameGrid.setPadding(new Insets(50, 50, 50, 50));
		gameGrid.setVgap(5);
		gameGrid.setHgap(5);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				gameGrid.add(new Button(i +", " + j), i, j);
			}
		}
		VBox grid = new VBox(gameGrid);
		grid.setAlignment(Pos.CENTER);
		
				
		
		// options dropdown button
		ObservableList<Button> options = 
		FXCollections.observableArrayList(rulesB, NewGameB, ExitB);
		final ComboBox Options = new ComboBox(options);
		Options.setPromptText("Options");	
		
		// Actions for buttons
		
		back.setOnAction(e -> primaryStage.setScene(welcomeScreen));
		rulesB.setOnAction(e -> primaryStage.setScene(rulesScreen));
		// Adding the buttons to a layout
		
		
		
		
		
		// Setting ingame box to HBox so its horizontal
		
		HBox box2 = new HBox(back, themeB, gamePlayB, Options);
		Pane ingamePane = new Pane(box2, grid);
	
		// setting the in game Menu to the top
		box2.setAlignment(Pos.TOP_CENTER);
		grid.setAlignment(Pos.CENTER);
		
		// building the scenes
		
		ingameScreen = new Scene(ingamePane, 700,700);
		rulesScreen = new Scene(rulesB, 700, 700);
		
		Welcome.makeWindow(primaryStage, ingameScreen);
		primaryStage.show();
		
	}
}
