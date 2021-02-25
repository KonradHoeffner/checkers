package checkers;

public class Move
{
	public final Position start,end;
	public final Board board;
	
	private int playerDirection(int player) {return new int[] {1,-1}[player];}
	
	public Move(String s, Board board)
	{
		if(s.length()!=4) throw new IllegalArgumentException("move '"+s+"' length != 4");
		start = new Position(s.substring(0,2));
		end = new Position(s.substring(2,4));
		this.board=board;
		Cell player = board.activePlayerCell();

		if(board.at(start)==Cell.EMPTY) {throw new IllegalArgumentException("No game piece on move start position "+s+".");}
		if(board.at(start)!=player) {throw new IllegalArgumentException("Game piece "+board.at(start)+" does not belong to active player.");}
		
		if((Math.abs(end.col-start.col)==1)&&(end.row-start.row==playerDirection(board.activePlayer()))) // peaceful move
		{
			if(board.at(end)!=Cell.EMPTY) {throw new IllegalArgumentException("Board is not empty at end position "+s+".");}
			board.set(start, Cell.EMPTY);
			board.set(end, board.activePlayerCell());
			board.turn++;
			return;
		}
		if((Math.abs(end.col-start.col)==2)&&(end.row-start.row==2*playerDirection(board.activePlayer()))) // attack move
		{
			if(board.at(end)!=Cell.EMPTY) {throw new IllegalArgumentException("Board is not empty at end position "+s+".");}
			Position enemyPos = new Position((start.col+end.col)/2,(start.row+end.row)/2);
			Cell enemy = board.nonActivePlayerCell();
			if(board.at(enemyPos)!=enemy) {throw new IllegalArgumentException("Board does not have enemy player "+enemy+" at the kill position "+enemyPos+".");}
			board.set(start, Cell.EMPTY);
			board.set(enemyPos, Cell.EMPTY);
			board.set(end, player);
			board.turn++;
			return;			
		}
		throw new IllegalArgumentException("move '"+s+"' not possible.");
	}	 
}
