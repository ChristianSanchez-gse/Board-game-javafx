import java.util.Stack;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameGrid {
	Stack<CheckerPiece> playHistory = new Stack<CheckerPiece>();
	GridPane gameGrid;
	Label moveInfo;
	private final CheckerPiece[][] checkerArr = new CheckerPiece[6][7];
	private int theme;
	String p1color;
	String p2color;
	
	// constructor
	GameGrid() {
		moveInfo = new Label();
		moveInfo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		p1color = "-fx-background-color: #00b3ff";
		p2color = "-fx-background-color: #00ff99";
		gameGrid = new GridPane();
	}
	
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
			checkerArr[checker.getRow()][checker.getCol()].setStyle(p1color);
			checkerArr[checker.getRow()][checker.getCol()].setPlayer(2);
			System.out.println("it is now player 1's turn");
		} else {
				checkerArr[checker.getRow()][checker.getCol()].setStyle(p2color);
				checkerArr[checker.getRow()][checker.getCol()].setPlayer(1);
				System.out.println("it is now player 2's turn");
		}
		System.out.println("added player to the stack: " + checker.getPlayer());
		playHistory.add(checker);
		checkerArr[checker.getRow()][checker.getCol()].setOccupied(true);
		
	}

	
	// The algorithm to check if the current move is a 
	// winning play is called after each turn.
	// It traverses the grid in every direction, starting from the
	// current piece that was placed. Once it reaches 4 in a row, true is returned.
	public boolean checkWinner(CheckerPiece checker) {
		System.out.println("THIS IS THE CURRENT PLAYER: ");
		moveInfo.setText("Player " + checker.getPlayer() + " moved to (" + checker.getRow() + " , " + checker.getCol() + ")");
		
		int validMoves = 1;
		CheckerPiece origin = checker;
		// traversing horizontally
		while(checker.getPlayer() == origin.getPlayer() && checker.getCol() > 0) {
			checker = checkerArr[checker.getRow()][checker.getCol()-1];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		//validMoves--;
		checker = origin;
		while(checker.getPlayer() == origin.getPlayer() &&  checker.getCol() < 6) {
			checker = checkerArr[checker.getRow()][checker.getCol()+1];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		validMoves = 1;
		System.out.println("THIS SHOULD BE 1" + validMoves);
		// traversing diagonal left
		System.out.println("WE ARE IN HORIZONTAL, VALID COUNTER: " + validMoves);
		while(checker.getPlayer() == origin.getPlayer() && checker.getRow() > 0 && checker.getCol() > 0) {
			checker = checkerArr[checker.getRow()-1][checker.getCol()-1];
			System.out.println("TRAVERSING TO " + checker.getRow() + " , " + checker.getCol() + "    COUNTER: " + validMoves);
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		//validMoves--;
		checker = origin;
		while(checker.getPlayer() == origin.getPlayer() && checker.getRow() < 5 && checker.getCol() < 6) {
			checker = checkerArr[checker.getRow()+1][checker.getCol()+1];
			System.out.println("TRAVERSING TO " + checker.getRow() + " , " + checker.getCol() + "    COUNTER: " + validMoves);
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		validMoves = 1;
		
		// traversing downward
		while(checker.getPlayer() == origin.getPlayer() && checker.getRow() > 0) {
			checker = checkerArr[checker.getRow()-1][checker.getCol()];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		//validMoves--;
		checker = origin;
		while(checker.getPlayer() == origin.getPlayer() && checker.getRow() < 5) {
			checker = checkerArr[checker.getRow()+1][checker.getCol()];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		validMoves = 1;
		
		// traversing diagonal right
		while(checker.getPlayer() == origin.getPlayer() && checker.getRow() > 0 && checker.getCol() > 6) {
			checker = checkerArr[checker.getRow()-1][checker.getCol()+1];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		checker = origin;
		while(checker.getPlayer() == origin.getPlayer() && checker.getRow() < 5 && checker.getCol() > 0) {
			checker = checkerArr[checker.getRow()+1][checker.getCol()-1];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		return false;
	}
	
	// Builds the array (checkerArr) that will hold the CheckerPiece objects.
	// This function also adds the objects in the array into the
	// GridPane called GameGrid. The reason for this that the CheckerPiece buttons in
	// the GridPane can be altered by calling checkerArr[x][y].
	public void buildArray() {	
		gameGrid.setVgap(20);
		gameGrid.setHgap(20);
		for(int row = 0; row < 6; row++) {
			for(int col = 0; col < 7; col++) {
				CheckerPiece temp = new CheckerPiece(row+", "+col, row , col);
				temp.setPrefSize(70, 70);
				gameGrid.add(temp,col,row);
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
			if (checkWinner(checker)) {System.out.println("FUCKIN WINNING MOVE RIGHT THERE");}
		} else {
			System.out.println("invalid move, do nothing.");
			moveInfo.setText("Invalid move. Try a different play");
		}
		
		
	}
	
	// returns the GridPane to be 
	// displayed on the screen.
	public GridPane getGrid() {
		return gameGrid;
	}
	
	// Returns the label that displays
	// the current moves in the game.
	public Label getInfo() {
		return moveInfo;
	}
	
	// Changes the theme to the corresponding color
	public void changeTheme1() {
		this.theme = 1;	
		//buildArray();
		p1color ="-fx-background-color: #A52A2A";
		p2color = "-fx-background-color: #FFD700";
		themeHelper();
		
		
		System.out.println("The theme has been changed1");
		
	}
	public void changeTheme2() {
		this.theme = 2;
		//buildArray();
		
		p1color ="-fx-background-color: #FF69B4";
		p2color = "-fx-background-color: #7CFC00";
		themeHelper();
		
		System.out.println("The theme has been changed2");
		
	}
	public void changeTheme3() {
		this.theme = 3;

		
		p1color ="-fx-background-color: #F08080";
		p2color = "-fx-background-color: #ADD8E6";
		themeHelper();
		System.out.println("The theme has been changed3");
		
	}
	
	public void themeHelper() {
		

		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				if (theme == 1) {
					checkerArr[r][c].setShape(new Text("BRUH"));
					if (checkerArr[r][c].getPlayer() == 1) {
						checkerArr[r][c].setStyle(p1color);
					} else if (checkerArr[r][c].getPlayer() == 2) {
						checkerArr[r][c].setStyle(p2color);
					}
					
				} else if (theme ==2) {
					checkerArr[r][c].setShape(new Circle(1.5));
					if (checkerArr[r][c].getPlayer() == 1) {
						checkerArr[r][c].setStyle(p1color);
					} else if (checkerArr[r][c].getPlayer() == 2) {
						checkerArr[r][c].setStyle(p2color);
					}
				} else if (theme == 3) {
					Polygon polygon = new Polygon();
					polygon.getPoints().addAll(new Double[]{
					    4.0, 5.5,
					    20.0, 10.0,
					    10.0, 20.0 });
					checkerArr[r][c].setShape(polygon);
					if (checkerArr[r][c].getPlayer() == 1) {
						checkerArr[r][c].setStyle(p1color);
					} else if (checkerArr[r][c].getPlayer() == 2) {
						checkerArr[r][c].setStyle(p2color);
					}
				}
				
			}
		}
		
		
	}
	
	// Pops a CheckerPiece from the stack of
	// game moves and sets those values on the grid
	// back to their defaults in order to simulate
	// "reversing" a move.
	public void Undo() {
		if (playHistory.isEmpty() == true) {
			System.out.println("The stack is empty!!");
			moveInfo.setText("Cant reverse any more moves!");
			return;
		}
		CheckerPiece temp = playHistory.pop();
		checkerArr[temp.getRow()][temp.getCol()].setStyle(null);
		checkerArr[temp.getRow()][temp.getCol()].setPlayer(0);
		checkerArr[temp.getRow()][temp.getCol()].setOccupied(false);
	}

	// reverses all the moves to begin the game once again.
	public void newGame() {
		while (playHistory.isEmpty() != true) {
			Undo();
		}
	}

	public void setInfoDefault() {
		moveInfo.setText("");
		
	}
}

