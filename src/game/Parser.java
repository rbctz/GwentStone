package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public final class Parser {

    private static ArrayNode arrayNodeOutput;

    private int playerOneWins;
    private int playerTwoWins;

    public Parser() {
        ObjectMapper objectMapper = new ObjectMapper();
        arrayNodeOutput = objectMapper.createArrayNode();
    }

    /**
     * Parses the input and starts the games.
     */
    public void parseGames(final Input input) {
        for (int i = 0; i < input.getGames().size(); i++) {
            Game game = new Game(this);
            game.startGame(input, i);
        }
    }

    public static ArrayNode getArrayNodeOutput() {
        return arrayNodeOutput;
    }

    public int getPlayerOneWins() {
        return playerOneWins;
    }

    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    /**
     * Increments the number of wins for player one.
     */
    public void incrementPlayerOneWins() {
        this.playerOneWins++;
    }

    /**
     * Increments the number of wins for player two.
     */
    public void incrementPlayerTwoWins() {
        this.playerTwoWins++;
    }

    /**
     * Returns the total number of games played.
     */
    public int getGamesPlayed() {
        return playerOneWins + playerTwoWins;
    }
}
