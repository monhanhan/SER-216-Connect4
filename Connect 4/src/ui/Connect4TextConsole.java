package ui;

import java.util.Scanner;

import core.Connect4;
import core.Connect4ComputerPlayer;

/**
 * 
 * @author Ryan Munin
 * @version 2.0
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

	boolean playComputer = playComputer(myScanner);

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

	    // This is if statement enables a computer turn if the player has opted to play
	    // against a computer and if it is the computer's turn.
	    if ((player == 'O') && playComputer) {
		Connect4ComputerPlayer.takeTurn(myGame, player);

	    } else {
		takeTurn(player, myScanner, myGame);

	    }

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
     * This method takes player input for whether or not a player wants to play
     * against a computer. The method will continue prompting until it receives
     * either a 'C' or a 'P'. Input can be upper or lower case with any number of
     * spaces.
     * 
     * @param myScanner is a scanner object taking input from the console.
     * @return is a boolean representing whether the player wants to play against a
     *         computer.
     */
    private static boolean playComputer(Scanner myScanner) {
	boolean badInput = true;
	boolean playComputer = false;

	while (badInput) {
	    System.out
		    .print("Enter 'P' if you want to play against another player; enter 'C' to play against computer.");
	    System.out.println();
	    System.out.println();
	    System.out.print(">>");

	    String myInput = myScanner.next();

	    System.out.println();
	    System.out.println();

	    // Cleaning string.
	    myInput = myInput.toUpperCase().trim();

	    if ((myInput.equals("C")) || (myInput.equals("P"))) {
		badInput = false;
		playComputer = (myInput.equals("C"));
	    }
	}

	return playComputer;

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
