package checkers;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testPosition()
	{
		new Position("A1");
		new Position("H8");
		
		assertThrows(IllegalArgumentException.class,()->new Position("AAAA1")); // too long
		assertThrows(IllegalArgumentException.class,()->new Position("A9")); // row out of bounds 
		assertThrows(IllegalArgumentException.class,()->new Position("Z1")); // col out of bounds
		assertThrows(IllegalArgumentException.class,()->new Position("A2")); // not a black square
	}

	@Test
	void testMove()
	{
		Board board = new Board();
		assertThrows(IllegalArgumentException.class,()->new Move("A1B2AAA1",board)); // too long
		assertThrows(IllegalArgumentException.class,()->new Move("A1Z1",board)); // left position invalid 
		assertThrows(IllegalArgumentException.class,()->new Move("Z1A1",board)); // right position invalid
		assertThrows(IllegalArgumentException.class,()->new Move("A1H8",board)); // valid positions but invalid move
		assertThrows(IllegalArgumentException.class,()->new Move("A1B2",board)); // board not empty
	}
	
	@Test
	void testGame()
	{
		Board board = new Board();
		new Move("B2C3",board);
		new Move("G7F6",board);
		new Move("C3D4",board);
		new Move("F6E5",board);
		new Move("D4F6",board); // kill E6
		assertEquals(Cell.EMPTY, board.at(new Position("D4")));
		assertEquals(Cell.EMPTY, board.at(new Position("E5")));
		assertEquals(Cell.BLACK, board.at(new Position("F6")));
	}
}
