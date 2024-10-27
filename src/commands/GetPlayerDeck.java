package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Category;
import enums.Constants;
import fileio.ActionsInput;
import game.Card;
import game.Player;

import static commands.GetCardHelper.getCardHelper;

public final class GetPlayerDeck {
    private GetPlayerDeck() { }

    /**
     *
     * @param actionsInput
     * @param playerOne
     * @param playerTwo
     * @return
     */
    public static ObjectNode getPlayerDeck(final ActionsInput actionsInput,
                                           final Player playerOne,
                                           final Player playerTwo) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = objectMapper.createObjectNode();
        returnValue.put(Category.COMMAND.getCategory(),
                actionsInput.getCommand());
        returnValue.put(Category.PLAYER_INDEX.getCategory(),
                actionsInput.getPlayerIdx());
        ArrayNode deck = objectMapper.createArrayNode();
        if (actionsInput.getPlayerIdx() == Constants.PLAYER_ONE.getValue()) {
            for (Card card : playerOne.getDeck()) {
                deck.add(getCardHelper(card.getCard()));
            }
            returnValue.set(Category.OUTPUT.getCategory(), deck);
        } else {
            for (Card card : playerTwo.getDeck()) {
                deck.add(getCardHelper(card.getCard()));
            }
            returnValue.set(Category.OUTPUT.getCategory(), deck);
        }
        return returnValue;
    }
}
