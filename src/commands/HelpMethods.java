package commands;

import game.Game;

public final class HelpMethods {
    private HelpMethods() { }

    /**
     *
     * @param game
     * @return
     */
    public static int getTurn(final Game game) {
        if (game.getPlayerOne().isHisTurn()) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     *
     * @param game
     */
    public static void switchTurns(final Game game) {
        game.getPlayerOne().setHisTurn(!game.getPlayerOne().isHisTurn());
        game.getPlayerTwo().setHisTurn(!game.getPlayerTwo().isHisTurn());
    }
}
