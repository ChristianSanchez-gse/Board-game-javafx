import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
public class CheckerPiece extends Button{
	private int col;
	private int row;
	private boolean occupied;
	private int player;
	
	
	public CheckerPiece(String string, int row, int col) {
		this.setText(string);
		this.col = col;
		this.row = row;
		this.occupied = false;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col; 
	} 
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	/*
	EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>() {
		public void handle (ActionEvent e) {
			colors.setStyle("-fx-background-color: #00ff00");
		}
	};
*/
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean b) {
		this.occupied = b;
		
	}
	public void setPlayer(int i) {
		this.player = i;
		
	}
	public int getPlayer() {
		return this.player;
	}
}
