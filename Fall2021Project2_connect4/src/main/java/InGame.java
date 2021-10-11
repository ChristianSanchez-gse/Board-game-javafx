import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InGame {
	Button back = new Button("Back to welcome");
	Button themeB = new Button("Themes");
	Button gamePlayB = new Button("Game Play");
	
	public static void makeWindow(Stage primaryStage, Scene ingameScreen) {
		// Buttons
				Button play = new Button("Welcome Screen. Press this to move on");
				VBox box = new VBox(play);
				Scene welcomeScreen = new Scene(box, 700,700);
				
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
				
				
				
				// options dropdown button
				ObservableList<Button> options = 
				FXCollections.observableArrayList(rulesB, NewGameB, ExitB);
				final ComboBox Options = new ComboBox(options);
				Options.setPromptText("Options");
				
				
				
				VBox grid = new VBox(gameGrid);
				grid.setAlignment(Pos.CENTER);
				
				primaryStage.setScene(welcomeScreen);
				play.setOnAction(e -> primaryStage.setScene(ingameScreen));
	}
}
