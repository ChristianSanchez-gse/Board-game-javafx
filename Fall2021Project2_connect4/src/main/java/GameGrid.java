import java.util.Stack;

import javafx.scene.layout.GridPane;

public class GameGrid {
	Stack<CheckerPiece> playHistory = new Stack<CheckerPiece>();
	GridPane gameGrid = new GridPane();
	private final CheckerPiece[][] checkerArr = new CheckerPiece[6][7];
	
	// returns true if the current move is valid
	// else return false
	public boolean isValid(CheckerPiece checker) {
		if(checker.isOccupied() == true) {
			return false;
		}
		return true;
	}
	
	// Moves the checkerpiece down if it is clicked above
	// a an empty spot.
	public void moveDown(CheckerPiece checker) {
		// traverse the all the way down until 1-an occupied node
		while(checkerArr[checker.getRow()][checker.getCol()].isOccupied() == false && checker.getRow() != 5) {
			checker  = checkerArr[checker.getRow() + 1][checker.getCol()];
		}
		// if we found a node that is occupied, we move the current play to row-1
		if(checkerArr[checker.getRow()][checker.getCol()].isOccupied() == true) {
			checker = checkerArr[checker.getRow()-1][checker.getCol()];
		}
		
		// Make all the changes 
		makePlay(checker);
	}
	
	// Changes the color to the corresponding player
	// sets the checkerpiece to occupied
	// adds the play to the stack.
	private void makePlay(CheckerPiece checker) {

		// if the stack is empty or the last move was player 1, make changes for player two
		if (playHistory.isEmpty() == true || playHistory.peek().getPlayer() == 1){
			checkerArr[checker.getRow()][checker.getCol()].setStyle("-fx-background-color: #00b3ff");
			checkerArr[checker.getRow()][checker.getCol()].setPlayer(2);
		} else {
				checkerArr[checker.getRow()][checker.getCol()].setStyle("-fx-background-color: #00ff99");
				checkerArr[checker.getRow()][checker.getCol()].setPlayer(1);
		}
		System.out.println("added player to the stack: " + checker.getPlayer());
		playHistory.add(checker);
		checkerArr[checker.getRow()][checker.getCol()].setOccupied(true);
		
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
		for(int row = 0; row < 6; row++) {
			for(int col = 0; col < 7; col++) {
				CheckerPiece temp = new CheckerPiece(row+", "+col, row , col);
				
				gameGrid.add(temp,col,row);
				// new CheckerPiece(i+", "+j, i , j), i , j
				temp.setOnAction(e -> pressButton(temp));
				checkerArr[row][col] = temp;
			}
		}
	}
	
	
	// changes all the values depending on what player clicks the button.
	// it will add the checkerpiece to the stack in order to keep track of the history.
	public void pressButton(CheckerPiece checker) {
		// if the play is odd, player 1, otherwise player 2 is the current move
		// change the checkerpiece to the correct color
		// add the checkerpiece to the stack
		System.out.println("You clicked: ROW: " + checker.getRow() + ", COL: " + checker.getCol());
		System.out.println("Valid : " + isValid(checker));
		if (isValid(checker)) {
			moveDown(checker);
		} else {
			System.out.println("invalid move, do nothing.");
		}
		
		
	}
	
	
	public GridPane getGrid() {
		return gameGrid;
	}
}
