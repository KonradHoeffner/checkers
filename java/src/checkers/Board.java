package checkers;

import java.util.Arrays;

public class Board
{
	static final int ROWS = 8;
	static final int COLS = 8;

	int turn = 0;
	private final Cell[][] cells = new Cell[COLS][ROWS];
	final Board previous;

	public int activePlayer() {return turn%2;}
	public int nonActivePlayer() {return (turn+1)%2;}
	public Cell activePlayerCell() {return new Cell[] {Cell.BLACK,Cell.WHITE}[activePlayer()];}
	public Cell nonActivePlayerCell() {return new Cell[] {Cell.BLACK,Cell.WHITE}[nonActivePlayer()];}

	public Cell at(Position pos) {return cells[pos.row][pos.col];}

	private void set(Position pos, Cell cell) {cells[pos.row][pos.col]=cell;}

	public Board()
	{
		this.previous=this;

		for(Cell[] row: cells) {Arrays.fill(row, Cell.EMPTY);}

		for(int row=0;row<2;row++)
		{
			for(int i=0;i<COLS;i+=2)
			{
				cells[row][i+row]=Cell.BLACK;								
				cells[ROWS-1-row][i+1-row]=Cell.WHITE;
			}
		}		
	}

	public Board(Board original)
	{
		this.turn=original.turn;
		this.previous=original;
		for(int row=0;row<ROWS;row++)
		{System.arraycopy(original.cells[row], 0, this.cells[row], 0, COLS);}
	}

	static private String rowString(Cell[] row) {return Arrays.stream(row).map(Cell::toString).reduce("", (a,b)->a+" "+b);} 

	@Override public String toString() {return Arrays.stream(cells).map(Board::rowString).reduce("",(a,b)->a+"\n"+b);}


	private int playerDirection(int player) {return new int[] {1,-1}[player];}

	@SuppressWarnings("serial")
	public static class IllegalMoveException extends RuntimeException {public IllegalMoveException(String move, String message) {super("Invalid move "+move+": "+message);}}

	public Board move(String s)
	{
		if(s.length()!=4) throw new IllegalMoveException(s,"move '"+s+"' length != 4");
		try
		{
			Position start = new Position(s.substring(0,2));
			Position end = new Position(s.substring(2,4));
			Cell player = this.activePlayerCell();

			if(this.at(start)==Cell.EMPTY) {throw new IllegalMoveException(s,"No game piece on move start position "+s+".");}
			if(this.at(start)!=player) {throw new IllegalMoveException(s,"Game piece "+this.at(start)+" does not belong to active player.");}

			if((Math.abs(end.col-start.col)==1)&&(end.row-start.row==playerDirection(this.activePlayer()))) // peaceful move
			{
				Board newBoard = new Board(this);
				if(newBoard.at(end)!=Cell.EMPTY) {throw new IllegalMoveException(s,"Board is not empty at end position "+s+".");}
				newBoard.set(start, Cell.EMPTY);
				newBoard.set(end, newBoard.activePlayerCell());
				newBoard.turn++;
				return newBoard;
			}
			if((Math.abs(end.col-start.col)==2)&&(end.row-start.row==2*playerDirection(this.activePlayer()))) // attack move
			{
				Board newBoard = new Board(this);
				if(newBoard.at(end)!=Cell.EMPTY) {throw new IllegalMoveException(s,"Board is not empty at end position "+s+".");}
				Position enemyPos = new Position((start.col+end.col)/2,(start.row+end.row)/2);
				Cell enemy = newBoard.nonActivePlayerCell();
				if(newBoard.at(enemyPos)!=enemy) {throw new IllegalMoveException(s,"Board does not have enemy player "+enemy+" at the kill position "+enemyPos+".");}
				newBoard.set(start, Cell.EMPTY);
				newBoard.set(enemyPos, Cell.EMPTY);
				newBoard.set(end, player);
				newBoard.turn++;
				return newBoard;
			}
			throw new IllegalMoveException(s,"move '"+s+"' not possible.");
		}
		catch(IllegalArgumentException e) {throw new IllegalMoveException(s, e.getMessage());}
	}

}
