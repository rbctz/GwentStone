package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Category;
import enums.Constants;
import fileio.ActionsInput;
import game.Player;

import static commands.GetHeroHelper.getHeroHelper;

public final class GetPlayerHero {
    private GetPlayerHero() { }
    /**
     *
     * @param actionsInput
     * @param playerOne
     * @param playerTwo
     * @return
     */
    public static ObjectNode getPlayerHero(final ActionsInput actionsInput,
                                           final Player playerOne,
                                           final Player playerTwo) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = objectMapper.createObjectNode();
        returnValue.put(Category.COMMAND.getCategory(), actionsInput.getCommand());
        returnValue.put(Category.PLAYER_INDEX.getCategory(), actionsInput.getPlayerIdx());
        if (actionsInput.getPlayerIdx() == Constants.PLAYER_ONE.getValue()) {
            returnValue.set(Category.OUTPUT.getCategory(),
                    getHeroHelper(playerOne.getHero().getCard()));
        } else {
            returnValue.set(Category.OUTPUT.getCategory(),
                    getHeroHelper(playerTwo.getHero().getCard()));
        }
        return returnValue;
    }
}
