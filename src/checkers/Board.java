package checkers;

import java.util.Arrays;

public class Board
{
	static final int ROWS = 8;
	static final int COLS = 8;
	
	int turn = 0;
	public int activePlayer() {return turn%2;}
	public int nonActivePlayer() {return (turn+1)%2;}
	public Cell activePlayerCell() {return new Cell[] {Cell.BLACK,Cell.WHITE}[activePlayer()];}
	public Cell nonActivePlayerCell() {return new Cell[] {Cell.BLACK,Cell.WHITE}[nonActivePlayer()];}
	
	private final Cell[][] cells = new Cell[COLS][ROWS];
	
	public Cell at(Position pos) {return cells[pos.row][pos.col];}
	
	public void set(Position pos, Cell cell) {cells[pos.row][pos.col]=cell;}
	
	public Board()
	{
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
	
	static private String rowString(Cell[] row) {return Arrays.stream(row).map(Cell::toString).reduce("", (a,b)->a+" "+b);} 
	
	@Override public String toString() {return Arrays.stream(cells).map(Board::rowString).reduce("",(a,b)->a+"\n"+b);}
}
