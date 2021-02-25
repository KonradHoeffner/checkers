package checkers;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Board board = new Board();
		Scanner in = new Scanner(System.in);
		while(true)
		{
			System.out.println(board);
			System.out.print(board.activePlayerCell()+" make your move: ");
			try
			{
				new Move(in.nextLine().trim(),board);
			}
			catch(IllegalArgumentException ex)
			{
				System.err.println("Illegal move: "+ex.getMessage());
			}
		}
	}
}
