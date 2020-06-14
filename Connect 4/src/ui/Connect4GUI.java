package ui;

import core.Connect4;
import javafx.application.Application;
import javafx.stage.Stage;

public class Connect4GUI extends Application {
    private Connect4 myBoard;
    private Stage primaryStage;
    private boolean computerPlayer;

    // TODO: these seem arbitrary. Be sure to check that these actually make sense
    // in practice.

    // These exist here so that I don't have any magic numbers and to make changing
    // the scale easier if I need to do that later.
    private final int PANESIZE = 384;
    private final int INSET = 8;
    private final int TILESIZE = 46;

    @Override
    public void start(Stage arg0) throws Exception {
	// TODO Auto-generated method stub

    }

}
