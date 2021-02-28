package checkers;

public enum Cell
{
	BLACK('B'),WHITE('W'),EMPTY('_');
	
	private final char c;
	private Cell(char c) {this.c=c;}
	
	@Override public String toString() {return String.valueOf(c);}
}
