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

	
	// gets called whenever a move is made in order to stop the game
	// if a winner is found
	public boolean checkWinner(CheckerPiece checker) {
		// keeps track of the valid moves that the algorithm finds.
		// when it reaches 4, the winner is found.
		System.out.println("THIS IS THE CURRENT PLAYER: ");
		System.out.println(checker.getPlayer());
		moveInfo.setText("Player " + checker.getPlayer() + " moved to (" + checker.getRow() + " , " + checker.getCol() + ")");
		
		int validMoves = 1;
		CheckerPiece origin = checker;
		// traversing horizontally
		while(checker.getPlayer() == origin.getPlayer() && checker.getCol() > 0) {
			checker = checkerArr[checker.getRow()][checker.getCol()-1];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		validMoves--;
		checker = origin;
		while(checker.getPlayer() == origin.getPlayer() &&  checker.getCol() < 6) {
			checker = checkerArr[checker.getRow()][checker.getCol()+1];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		validMoves = 1;
		
		// traversing diagonal left
		while(checker.getPlayer() == origin.getPlayer() && checker.getRow() > 0 && checker.getCol() > 0) {
			checker = checkerArr[checker.getRow()-1][checker.getCol()-1];
			validMoves++;
			if(validMoves == 4) {return true;}
		}
		validMoves--;
		checker = origin;
		while(checker.getPlayer() == origin.getPlayer() && checker.getRow() < 5 && checker.getCol() < 6) {
			checker = checkerArr[checker.getRow()+1][checker.getCol()+1];
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
		validMoves--;
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
	
	
	public GridPane getGrid() {
		return gameGrid;
	}
	
	public Label getList() {
		return moveInfo;
	}
	
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
	

//		buildArray();
//		System.out.println("The theme has been changed");

	
	// Pops a play from the stack and resets the specific
	// Button back to its default values.
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
}

