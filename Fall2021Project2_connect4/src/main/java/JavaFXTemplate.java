import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		// Buttons
		Button play = new Button("Welcome Screen. Press this to move on");
		Button back = new Button("This is the in game window, press this to go back");
		// Actions for buttons
		play.setOnAction(e -> primaryStage.setScene(ingameScreen));
		back.setOnAction(e -> primaryStage.setScene(welcomeScreen));
		// Adding the buttons to a layout
		VBox box = new VBox(play);
		VBox box2 = new VBox(back);
		box2.setAlignment(Pos.CENTER);
		
		// building the scenes
		welcomeScreen = new Scene(box, 700,700);
		ingameScreen = new Scene(box2, 700,700);
		primaryStage.setScene(welcomeScreen);
		primaryStage.show();
		
	}
}
