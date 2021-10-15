import java.util.Stack;

import javafx.scene.layout.GridPane;

public class GameGrid {
	Stack playHistory = new Stack();
	GridPane gameGrid = new GridPane();
	private final CheckerPiece[][] checkerArr = new CheckerPiece[7][6];
	// returns true if the current move is valid
	// else return false
	public boolean isValid(CheckerPiece checker) {
		if (checker.getColor == "red" || "blue" || ) {
			return false;
		}
		return false;
	}
	
	// Moves the checkerpiece down if it is clicked above
	// a an empty spot.
	public void moveDown() {
		
	}
	
	// updates all of the variables within the checkerpiece object
	// after it has been determined that the current move is a valid move
	public void updateChecker() {
		
	}
	
	// Adds the current checkerpiece to the stack playHistory to be used
	// when the undo button is pressed.
	public void addToStack(){
		
	}
	
	// gets called whenever a move is made in order to stop the game
	// if a winner is found
	public void checkWinner() {
		
	}
	
	public void buildArray() {
		//gameGrid.setPadding(new Insets(50, 50, 50, 50));
		
		// make the array global as before
		// inside the j loop, create a temp game button, with I, J in the constructor
		// temp.setonaction( e -> pressbutton())
		// pass temp in that pressButton() method.
		// 
		
		gameGrid.setVgap(20);
		gameGrid.setHgap(20);
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				CheckerPiece temp = new CheckerPiece(i+", "+j, i , j);
				
				gameGrid.add(temp,i,j);
				// new CheckerPiece(i+", "+j, i , j), i , j
				temp.setOnAction(e -> pressButton(temp));
				checkerArr[i][j] = temp;
				
				
				
				
				
			}
		}
	}
	
	
	// changes all the values depending on what player clicks the button.
	// it will add the checkerpiece to the stack in order to keep track of the history.
	public void pressButton(CheckerPiece checker) {
		// if the play is odd, player 1, otherwise player 2 is the current move
		// change the checkerpiece to the correct color
		// add the checkerpiece to the stack
		System.out.println("You clicked: " + checker.getRow() + ", " + checker.getCol());
		isValid(checker);
		
	}
	
	
	public GridPane getGrid() {
		return gameGrid;
	}
}
