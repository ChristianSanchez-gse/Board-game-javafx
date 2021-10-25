import javafx.scene.control.Button;
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
		//this.player = 0;
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
