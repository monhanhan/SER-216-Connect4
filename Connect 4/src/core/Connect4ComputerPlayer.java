package core;

public class Connect4ComputerPlayer {
    public static void takeTurn(Connect4 myGame, char player){
	boolean validMove = false;
	int currCol = 0;

	while (!validMove){
		validMove = myGame.addPiece(currCol, player);
		currCol ++;
	}

}

}

}
