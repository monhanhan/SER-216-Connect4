package ui;

import java.util.Scanner;

import core.Connect4;

/**
 * 
 * @author Ryan Munin
 * @version 1.0
 *
 */
public class Connect4TextConsole {
    /**
     * This is main. It calls all other methods in order.
     * 
     * @param args is unused.
     */
    public static void main(String[] args) {
	// Create a new instance of the game.
	Connect4 myGame = new Connect4();

	printBoard(myGame);
	printGameStart();

	char player = 'Q';
	Scanner myScanner = new Scanner(System.in);

	boolean isGameOver = false;
	boolean victory = false;

	// Until an end game condition is met, swap turns and allow the players to play
	// the game.
	while (!isGameOver) {
	    if (player != 'X') {
		player = 'X';
	    } else {
		player = 'O';
	    }

	    takeTurn(player, myScanner, myGame);

	    // Check end game conditions.
	    victory = myGame.checkVictory();
	    boolean tie = myGame.checkTie();
	    isGameOver = (victory || tie);

	    printBoard(myGame);

	}

	// Print results
	printGameEnd(victory, player);

	// Close down the game.
	myScanner.close();
	System.exit(0);

    }

    /**
     * This prints the opening message of the game.
     */
    private static void printGameStart() {
	System.out.println("Begin Game.");

    }

    /**
     * This prints the game board that has been passed from myGame
     * 
     * @param myGame is a Connect4 object.
     */
    private static void printBoard(Connect4 myGame) {
	char[][] board = myGame.getBoard();
	for (char[] subArray : board) {
	    System.out.print("|");

	    for (char currChar : subArray) {
		System.out.print(currChar);
		System.out.print("|");
	    }
	    System.out.println();
	}

    }

    /**
     * This prints the end of game messages indicating victory/winner or a tie.
     * 
     * @param victory is a boolean indicating if there has been a victory. If false,
     *                that indicates a tie.
     * @param player  is the player (X or O) that won the match.
     */
    private static void printGameEnd(boolean victory, char player) {
	if (victory) {
	    System.out.print("Player ");
	    System.out.print(player);
	    System.out.print(" Won the Game");

	} else {
	    System.out.print("The Game was a Tie!");

	}
    }

    /**
     * This allows players to play their turn. It takes input and does error
     * checking for invalid input before adding pieces to the board. If a column if
     * full or the move is invalid the player will be prompted again for new input.
     * 
     * @param player    is the player (X or O) who is taking their turn
     * @param myScanner is a scanner object taking input from the console.
     * @param myGame    is a Connect4 object.
     */
    private static void takeTurn(char player, Scanner myScanner, Connect4 myGame) {
	System.out.print("Player");
	System.out.print(player);
	System.out.print(" - your turn. ");

	boolean validMove = false;

	while (!validMove) {
	    int col = parseInput(myScanner);
	    validMove = myGame.addPiece(col, player);
	}
    }

    /**
     * This is a helper function for takeTurn that specifically handles input and
     * error checking.
     * 
     * @param myScanner is a scanner object taking input from the console
     * @return is the int representation of player input.
     */
    private static int parseInput(Scanner myScanner) {
	boolean badInput = true;
	int inInt = -1;

	while (badInput) {
	    System.out.println("Choose a column number from 1-7.");

	    String input = myScanner.next();
	    try {
		inInt = Integer.parseInt(input);

	    } catch (Exception notValidInput) {
		continue;

	    }
	    if ((inInt >= 1) && (inInt <= 7)) {
		inInt = inInt - 1;
		badInput = false;
	    }

	}

	return inInt;

    }

}
