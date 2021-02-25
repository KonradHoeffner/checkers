package checkers;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Board board = new Board();
		Scanner in = new Scanner(System.in);
		System.out.print("Welcome to checkers. _ to go back.");
		while(true)
		{
			System.out.println(board);
			System.out.print(board.activePlayerCell()+" make your move: ");
			String input = in.nextLine().trim();
			if(input.equals("_"))
			{
				System.out.println("Your opponent lets you take your last move.");
				board = board.previous;
				continue;
			}
			try
			{
				board = board.move(input);
			}
			catch(IllegalArgumentException ex)
			{
				System.err.println("Illegal move: "+ex.getMessage());
			}
		}
	}
}
