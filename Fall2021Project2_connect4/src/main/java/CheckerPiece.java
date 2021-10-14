import javafx.application.Application;
import javafx.scene.control.Button;
public class CheckerPiece extends Button{
	private int col;
	private int row;
	public CheckerPiece(String string, int row, int col) {
		this.setText(string);
		this.setCol(col);
		this.setRow(row);
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
	
}
