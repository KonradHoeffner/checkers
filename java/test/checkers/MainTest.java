package checkers;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import checkers.Board.IllegalMoveException;

class MainTest {

	@Test
	void testPosition()
	{
		Position.fromString("A1");
		Position.fromString("H8");
		
		assertThrows(IllegalArgumentException.class,()->Position.fromString("AAAA1")); // too long
		assertThrows(IllegalArgumentException.class,()->Position.fromString("A9")); // row out of bounds 
		assertThrows(IllegalArgumentException.class,()->Position.fromString("Z1")); // col out of bounds
		assertThrows(IllegalArgumentException.class,()->Position.fromString("A2")); // not a black square
	}

	@Test
	void testMove()
	{
		Board board = new Board();
		assertThrows(IllegalMoveException.class,()->board.move("A1B2AAA1"));; // too long
		assertThrows(IllegalMoveException.class,()->board.move("A1Z1"));; // left position invalid 
		assertThrows(IllegalMoveException.class,()->board.move("Z1A1"));; // right position invalid
		assertThrows(IllegalMoveException.class,()->board.move("A1H8"));; // valid positions but invalid move
		assertThrows(IllegalMoveException.class,()->board.move("A1B2"));; // board not empty
	}
	
	@Test
	void testGame()
	{
		Board board = new Board();
		String[] moves = {"B2C3","G7F6","C3D4","F6E5","D4F6"}; // kill E6
		for(String m: moves)
		{
			System.out.println(board);
			board = board.move(m);
		}
		assertEquals(Cell.EMPTY, board.at(Position.fromString("D4")));
		assertEquals(Cell.EMPTY, board.at(Position.fromString("E5")));
		assertEquals(Cell.BLACK, board.at(Position.fromString("F6")));
	}
}
