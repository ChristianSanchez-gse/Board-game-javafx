import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {
	
	static GameGrid testGrid;
	static CheckerPiece cp1, cp2, cp3, cp4, cp5, cp6;
	
	@BeforeAll
	static void setup() {
		testGrid = new GameGrid();
		testGrid.buildArray();
		cp1 = new CheckerPiece("one", 1, 1);
	}

	@Test
	void checkerPiece1() {
		assertEquals(1, cp1.getRow(), "Incorrect!");
	}

}
