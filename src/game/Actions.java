package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Category;
import enums.Command;
import enums.Constants;
import fileio.ActionsInput;
import fileio.CardInput;

public abstract class Actions {
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

    /**
     *
     * @param card
     * @return
     */
    public static ObjectNode getCardHelper(final CardInput card) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnCard = objectMapper.createObjectNode();
        returnCard.put(enums.Card.MANA.getField(), card.getMana());
        returnCard.put(enums.Card.ATTACK_DAMAGE.getField(), card.getAttackDamage());
        returnCard.put(enums.Card.HEALTH.getField(), card.getHealth());
        returnCard.put(enums.Card.DESCRIPTION.getField(), card.getDescription());
        ArrayNode colors = objectMapper.createArrayNode();
        for (String color : card.getColors()) {
            colors.add(color);
        }
        returnCard.set(enums.Card.COLORS.getField(), colors);
        returnCard.put(enums.Card.NAME.getField(), card.getName());
        return returnCard;
    }

    /**
     *
     * @param hero
     * @return
     */
    public static ObjectNode getHeroHelper(final CardInput hero) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnHero = objectMapper.createObjectNode();
        returnHero.put(enums.Card.MANA.getField(), hero.getMana());
        returnHero.put(enums.Card.DESCRIPTION.getField(), hero.getDescription());
        ArrayNode colors = objectMapper.createArrayNode();
        for (String color : hero.getColors()) {
            colors.add(color);
        }
        returnHero.set(enums.Card.COLORS.getField(), colors);
        returnHero.put(enums.Card.NAME.getField(), hero.getName());
        returnHero.put(enums.Card.HEALTH.getField(), Constants.HERO_HEALTH.getValue());
        return returnHero;
    }
}
