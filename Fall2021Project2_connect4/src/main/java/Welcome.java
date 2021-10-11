import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Welcome {
	public static void makeWindow(Stage primaryStage, Scene ingameScreen) {
		// Buttons
				Button play = new Button("Welcome Screen. Press this to move on");
				VBox box = new VBox(play);
				Scene welcomeScreen = new Scene(box, 700,700);
				primaryStage.setScene(welcomeScreen);
				play.setOnAction(e -> primaryStage.setScene(ingameScreen));
	}
}
