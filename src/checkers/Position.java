package checkers;


public class Position
{
	public final int col, row;
	public final String s;
	
	public Position(int col,int row) {this.col=col;this.row=row;s=""+(char)('A'+col)+(row+1);}
	
	public Position(String s)
	{
		if(s.length()!=2) throw new IllegalArgumentException("position '"+s+"' length != 2");
		col = s.charAt(0)-'A';
		row = s.charAt(1)-'0'-1;
		if(col<0||col>=Board.COLS) {throw new IllegalArgumentException("Illegal column value: "+col);}
		if(row<0||row>=Board.ROWS) {throw new IllegalArgumentException("Illegal row value: "+row);}
		if((row+col)%2!=0) {throw new IllegalArgumentException("Not a black square: "+s);}
		this.s=s;
	}
	
	@Override public String toString() {return s+" col="+col+", row="+row;}
}
