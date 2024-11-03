package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public final class Parser {

    ObjectMapper objectMapper = new ObjectMapper();
    private static ArrayNode arrayNodeOutput;

    private int playerOneWins;
    private int playerTwoWins;

    public Parser() {
        arrayNodeOutput = objectMapper.createArrayNode();
    }

    public void parseGames(final Input input) {
        for (int i = 0; i < input.getGames().size(); i++) {
            Game game = new Game(this);
            game.startGame(input, i);
        }
    }

    public static ArrayNode getArrayNodeOutput() {
        return arrayNodeOutput;
    }

}
