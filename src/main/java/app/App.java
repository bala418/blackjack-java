package app;

import app.exception.HandException;
import app.game.Game;

public class App {
    public static void main(String[] args) throws HandException {
        Game game = new Game();
        game.start();
    }




}
