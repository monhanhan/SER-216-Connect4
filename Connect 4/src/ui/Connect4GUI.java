package ui;

import core.Connect4;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Connect4GUI extends Application {
    Stage primaryStage;

    private Connect4 myBoard;
    private boolean computerPlayer;

    // TODO: these seem arbitrary. Be sure to check that these actually make sense
    // in practice.
    // These exist here so that I don't have any magic numbers and to make changing
    // the scale easier if I need to do that later.
    private final int PANESIZE = 384;
    private final int INSET = 8;
    private final int TILESIZE = 46;

    @Override
    public void start(Stage primaryStage) throws Exception {
	this.myBoard = new Connect4();

	// TODO: put in logic here to prompt the player whether they would like to play
	// vs a pc.
	this.computerPlayer = false;

	playGame(primaryStage);

    }

    // TODO: can this be folded into start? Why do I have it here specifically?
    public void playGame(Stage primaryStage) {
	BorderPane myBorders = makeBorders();

	Scene myScene = new Scene(myBorders);

	makeMoves(myScene);

	primaryStage.setTitle("Connect 4");
	primaryStage.setScene(myScene);
	this.primaryStage = primaryStage;
	primaryStage.show();

    }

    private BorderPane makeBorders() {
	TilePane myTiles = makePanes();
	myTiles.setMaxSize(PANESIZE, PANESIZE);
	myTiles.setMinSize(PANESIZE, PANESIZE);

	BorderPane myBorders = new BorderPane();

	myBorders.setCenter(myTiles);

	return myBorders;

    }

    private TilePane makePanes() {
	char[][] currBoard = myBoard.getBoard();

	TilePane myTiles = new TilePane();

	myTiles.setAlignment(Pos.CENTER);

	myTiles.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		StackPane tempPane = new StackPane();

		tempPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

		Circle myCirc = new Circle();

		if (currBoard[i][j] == 'B') {
		    myCirc.setFill(Color.BLACK);

		} else if (currBoard[i][j] == 'W') {
		    myCirc.setFill(Color.WHITE);

		} else {
		    myCirc.setFill(Color.TRANSPARENT);

		}

		myCirc.setRadius(20);

		tempPane.getChildren().add(myCirc);

		StackPane.setMargin(myCirc, new Insets(2, 2, 2, 2));

		myTiles.getChildren().add(tempPane);

	    }
	}
	return myTiles;

    }

    private void makeMoves(Scene myScene) {
	if (myController.canMove('W')) {

	    // This makes it so that when the player clicks a square they get to
	    // make a move.
	    myScene.setOnMouseClicked((event) -> {
		int x = (int) Math.round(event.getSceneX());
		int y = (int) Math.round(event.getSceneY());

		// This insures we have a valid position on which to click.
		if ((x > INSET) && (x < PANESIZE - INSET) && (y > MENUSIZE + INSET)
			&& (y < MENUSIZE + (PANESIZE - INSET))) {

		    // This converts the click from pixels to grid position.
		    int mathX = (x - INSET) / TILESIZE;
		    int mathY = (y - MENUSIZE - INSET) / TILESIZE;
		    takeHumanMove(mathX, mathY);
		}

	    });

	    // This is so the computer can move if the human can't.
	} else if (myController.canMove('B')) {
	    myController.computerTurn();
	    update(myModel, myBoard);

	    // If no one has a move, the game is over.
	} else {
	    Alert gameOver = new Alert(AlertType.INFORMATION);
	    gameOver.setTitle("Game Over");

	    int[] score = myController.getScore();

	    if (score[0] > score[1]) {
		gameOver.setHeaderText("You win");
		gameOver.setContentText("Even a broken watch is right twice a day.");

	    } else if (score[0] < score[1]) {
		gameOver.setHeaderText("You lose!");
		gameOver.setContentText("You always were a dissapointment");

	    } else {
		gameOver.setHeaderText("You tie");
		gameOver.setContentText("I am deeply unsatisfied with this outcome.");
	    }

	    gameOver.showAndWait();

	    this.gameIsOver = true;

	    File file = new File("save_data.dat");
	    if (file.exists()) {
		file.delete();
	    }

	    return;

	}

    }

}
