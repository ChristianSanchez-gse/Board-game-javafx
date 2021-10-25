import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyTest {
	
	
	static CheckerPiece cp1, cp2, cp3, cp4, cp5, cp6;
	
	@BeforeEach
	void setup() {
		
		cp1 = new CheckerPiece("one", 1, 1);
	}

	@Test
	void checkerPiece1() {
		cp1 = new CheckerPiece("one", 1, 1);
		assertEquals(1, cp1.getRow(), "Incorrect!");
	}
	
	@Test
	void undoStackNotEmpty() {
		GameGrid grid = new GameGrid();
		grid.addToStack(cp1);
		grid.addToStack(cp1);
		grid.addToStack(cp1);
		grid.addToStack(cp1);
		assertEquals(false, grid.isStackEmpty());
	}
	@Test
	void undoStackEmpty() {
		GameGrid grid = new GameGrid();
		assertEquals(true, grid.isStackEmpty());
	}

}
