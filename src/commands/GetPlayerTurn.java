package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Category;
import enums.Command;
import enums.Constants;
import game.Player;

public final class GetPlayerTurn {
    private GetPlayerTurn() { }

    /**
     *
     * @param playerOne
     * @return
     */
    public static ObjectNode getPlayerTurn(final Player playerOne) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = objectMapper.createObjectNode();
        returnValue.put(Category.COMMAND.getCategory(), Command.GET_PLAYER_TURN.getCommand());
        if (playerOne.isHisTurn()) {
            returnValue.put(Category.OUTPUT.getCategory(), Constants.PLAYER_ONE.getValue());
        } else {
            returnValue.put(Category.OUTPUT.getCategory(), Constants.PLAYER_TWO.getValue());
        }
        return returnValue;
    }
}
