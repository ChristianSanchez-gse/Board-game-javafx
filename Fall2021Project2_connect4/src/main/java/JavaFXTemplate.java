import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		welcomeScreen = new Scene(box, 1000,900);
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
		imageView2.setFitWidth(1000);
		imageView2.setFitHeight(900);
		rules.setX(100);
		rules.setY(100);
		
		
		
		Button rulesback = new Button("Back");
		VBox backbox = new VBox(rulesback, imageView2);
		rulesback.setOnAction((event) -> {
			optionsDrop.setValue("Options");
			primaryStage.setScene(ingameScreen);	
		});
		rulesScreen = new Scene(backbox, 1000,900);		
		
		
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
		    Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();
		    if (selectedItem == "Default") {

		    	gameGrid.changeTheme2();

		    } else if (selectedItem == "Triangle"){
		    	gameGrid.changeTheme3();

		    } else if (selectedItem == "Bruh") {
		    	gameGrid.changeTheme1();

		    }
		    
		    
		});

		//Options procedures
		optionsDrop.setOnAction((event)-> {
			Object selectedItem = optionsDrop.getSelectionModel().getSelectedItem();
			if (selectedItem == "How To Play") {
		    	primaryStage.setScene(rulesScreen);
		    	
		    } else if (selectedItem == "new game"){

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
		bottom.getChildren().add(gameGrid.getNextPlayer());
		bottom.getChildren().add(gameGrid.getInfo());
		bottom.setAlignment(Pos.CENTER);
		inGamePane.setBottom(bottom);
		
		
		
		
		// Creating an array of buttons that will be used in the gridpane
		
		gameGrid.buildArray();
		gameGrid.changeTheme2();// initializing the theme
		ingameScreen = new Scene(inGamePane, 1000,900);
		
		///////////////////////////////////
		// showing the initial screen
		///////////////////////////////////
		primaryStage.setScene(welcomeScreen);
		//primaryStage.setMaximized(true);
		primaryStage.show();
	}
	

	
}
