import java.util.Stack;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Text;

public class GameGrid {
	Stack<CheckerPiece> playHistory = new Stack<CheckerPiece>();
	GridPane gameGrid = new GridPane();
	private final CheckerPiece[][] checkerArr = new CheckerPiece[6][7];
	
	private int theme;
	
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
	public CheckerPiece moveDown(CheckerPiece checker) {
		// traverse the all the way down until 1-an occupied node
		while(checkerArr[checker.getRow()][checker.getCol()].isOccupied() == false && checker.getRow() != 5) {
			checker  = checkerArr[checker.getRow() + 1][checker.getCol()];
		}
		// if we found a node that is occupied, we move the current play to row-1
		if(checkerArr[checker.getRow()][checker.getCol()].isOccupied() == true) {
			checker = checkerArr[checker.getRow()-1][checker.getCol()];
		}
		System.out.println("We moved the node down to " + checker.getRow() + ", " + checker.getCol());
		// Make all the changes 
		makePlay(checker);
		return checker;
	}
	
	// Changes the color to the corresponding player
	// sets the checkerpiece to occupied
	// adds the play to the stack.
	private void makePlay(CheckerPiece checker) {		
		// if the stack is empty or the last move was player 1, make changes for player two
		if (playHistory.isEmpty() == true || playHistory.peek().getPlayer() == 1){
			checkerArr[checker.getRow()][checker.getCol()].setStyle("-fx-background-color: #00b3ff");
			checkerArr[checker.getRow()][checker.getCol()].setPlayer(2);
			System.out.println("it is now player 1's turn");
		} else {
				checkerArr[checker.getRow()][checker.getCol()].setStyle("-fx-background-color: #00ff99");
				checkerArr[checker.getRow()][checker.getCol()].setPlayer(1);
				System.out.println("it is now player 2's turn");
		}
		System.out.println("added player to the stack: " + checker.getPlayer());
		playHistory.add(checker);
		checkerArr[checker.getRow()][checker.getCol()].setOccupied(true);
		
	}

	
	// gets called whenever a move is made in order to stop the game
	// if a winner is found
	public boolean checkWinner(CheckerPiece checker) {
		// keeps track of the valid moves that the algorithm finds.
		// when it reaches 4, the winner is found.
		System.out.println("We are checking the winner");
		System.out.println(checker);
		
		int validMoves = 1;
		CheckerPiece origin = checker;
		// traversing horizontally
		while(checker.getPlayer() == origin.getPlayer() && checker.getCol() > 0) {
			checker = checkerArr[checker.getRow()][checker.getCol()-1];
			System.out.println("We have traversed into Node: " + checker.getRow() + ", " + checker.getCol());
			if(validMoves++ == 4) {return true;}
		}
		checker = origin;
		while(checker.getPlayer() == origin.getPlayer() &&  checker.getCol() < 6) {
			checker = checkerArr[checker.getRow()][checker.getCol()+1];
			System.out.println("We have traversed into Node: " + checker.getRow() + ", " + checker.getCol());
			if(validMoves++ == 4) {return true;}
		}
		validMoves = 1;
		
		// traversing diagonal left
		while(checker.getPlayer() == origin.getPlayer() && checker.getCol() > 0 && checker.getRow() > 0) {
			checker = checkerArr[checker.getRow()-1][checker.getCol()-1];
			System.out.println("We have traversed into Node: " + checker.getRow() + ", " + checker.getCol());
			if(validMoves++ == 4) {return true;}
		}
		checker = origin;
		while(checker.getPlayer() == origin.getPlayer() && checker.getCol() < 6 && checker.getRow() < 5) {
			checker = checkerArr[checker.getRow()+1][checker.getCol()+1];
			System.out.println("We have traversed into Node: " + checker.getRow() + ", " + checker.getCol());
			if(validMoves++ == 4) {return true;}
		}
		// traversing downward
		
		// traversing diagonal right
		
		return false;
	}
	
	public void buildArray() {	
		gameGrid.setVgap(20);
		gameGrid.setHgap(20);
		for(int row = 0; row < 6; row++) {
			for(int col = 0; col < 7; col++) {
				CheckerPiece temp = new CheckerPiece(row+", "+col, row , col);
				
				if(theme == 3) {
					temp.setShape(new Text("BRUH"));
				}else if (theme == 2) {
					temp.setShape(new Circle(1.5));
				}else if (theme == 1) {
					Polygon polygon = new Polygon();
					polygon.getPoints().addAll(new Double[]{
					    4.0, 5.5,
					    20.0, 10.0,
					    10.0, 20.0 });
					temp.setShape(polygon);
				}
				
				temp.setPrefSize(70, 70);
				
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
			checker = moveDown(checker);
			System.out.println("we moved down");
			if (checkWinner(checker)) {System.out.println("FUCKIN WINNING MOVE RIGHT THERE");}
		} else {
			System.out.println("invalid move, do nothing.");
		}
		
		
	}
	
	
	public GridPane getGrid() {
		return gameGrid;
	}
	

	
	public void changeTheme(int i) {
		this.theme = i;
		
		System.out.println("The theme has been changed");
		
	}
}
