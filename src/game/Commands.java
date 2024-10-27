package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import enums.BackRowMinions;
import enums.Category;
import enums.Command;
import enums.ErrorMessage;
import enums.FrontRowMinions;
import enums.Constants;
import fileio.CardInput;
import fileio.ActionsInput;


public abstract class Commands {
    private Commands() { }

    /**
     *
     * @param card the card
     * @return return value
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
     * @param hero the hero
     * @return return value
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

    /**
     *
     * @param actionsInput the current command
     * @param playerOne player1
     * @param playerTwo player2
     * @return return value
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
     * @param actionsInput the current command
     * @param playerOne player1
     * @param playerTwo player2
     * @return return value
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
     * @param game the current game
     * @return return value
     */
    public static ObjectNode getPlayerTurn(final Game game) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = objectMapper.createObjectNode();
        returnValue.put(Category.COMMAND.getCategory(), Command.GET_PLAYER_TURN.getCommand());
        if (game.getPlayerOne().isHisTurn()) {
            returnValue.put(Category.OUTPUT.getCategory(), Constants.PLAYER_ONE.getValue());
        } else {
            returnValue.put(Category.OUTPUT.getCategory(), Constants.PLAYER_TWO.getValue());
        }
        return returnValue;
    }

    /**
     *
     * @param game the current game that is played
     *
     */
    public static void endPlayerTurn(final Game game) {
        switchTurns(game);
        if (game.getPlayerOne().isHisTurn()) {
            for (Card card : game.getGameTable()[Constants.PLAYER_ONE_FRONT.getValue()]) {
                resetCard(card);
            }
            for (Card card : game.getGameTable()[Constants.PLAYER_ONE_BACK.getValue()]) {
                resetCard(card);
            }
        } else {
            for (Card card : game.getGameTable()[Constants.PLAYER_TWO_FRONT.getValue()]) {
                resetCard(card);
            }
            for (Card card : game.getGameTable()[Constants.PLAYER_TWO_BACK.getValue()]) {
                resetCard(card);
            }
        }
        game.setTurn(game.getTurn() + 1);

        // Every two turns (each player takes one turn) there is a round change
        checkRound(game);
    }

    /**
     *
     * @param card the card
     */
    public static void resetCard(final Card card) {
        if (card != null) {
            card.setFrozen(false);
            card.setUsedAbility(false);
            card.setUsedAttack(false);
        }
    }
    /**
     *
     * @param actionsInput the current command
     * @param game the current game
     * @return the mana of the player
     */
    public static ObjectNode getPlayerMana(final ActionsInput actionsInput, final Game game) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = objectMapper.createObjectNode();
        returnValue.put(Category.COMMAND.getCategory(), actionsInput.getCommand());
        returnValue.put(Category.PLAYER_INDEX.getCategory(), actionsInput.getPlayerIdx());
        if (actionsInput.getPlayerIdx() == Constants.PLAYER_ONE.getValue()) {
            returnValue.put(Category.OUTPUT.getCategory(), game.getPlayerOne().getMana());
        } else {
            returnValue.put(Category.OUTPUT.getCategory(), game.getPlayerTwo().getMana());
        }
        return returnValue;
    }

    /**
     *
     * @param actionsInput the current command
     * @param game the current game
     * @return ret
     */
    public static ObjectNode getCardsInHand(final ActionsInput actionsInput, final Game game) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = objectMapper.createObjectNode();
        returnValue.put(Category.COMMAND.getCategory(), actionsInput.getCommand());
        returnValue.put(Category.PLAYER_INDEX.getCategory(), actionsInput.getPlayerIdx());
        ArrayNode cardsInHand = objectMapper.createArrayNode();
        if (actionsInput.getPlayerIdx() == Constants.PLAYER_ONE.getValue()) {
            for (Card card : game.getPlayerOne().getHand()) {
                cardsInHand.add(getCardHelper(card.getCard()));
            }
            returnValue.set(Category.OUTPUT.getCategory(), cardsInHand);
        } else {
            for (Card card : game.getPlayerTwo().getHand()) {
                cardsInHand.add(getCardHelper(card.getCard()));
            }
            returnValue.set(Category.OUTPUT.getCategory(), cardsInHand);
        }
        return returnValue;
    }

    /**
     *
     * @param actionsInput the current command
     * @param game the current game
     * @return return value
     */
    public static ObjectNode getCardsOnTable(final ActionsInput actionsInput, final Game game) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = objectMapper.createObjectNode();
        returnValue.put(Category.COMMAND.getCategory(), actionsInput.getCommand());
        ArrayNode cardsOnTable = objectMapper.createArrayNode();
        for (Card[] cardRow : game.getGameTable()) {
            ArrayNode row = objectMapper.createArrayNode();
            for (Card card : cardRow) {
                if (card != null) {
                    row.add(getCardHelper(card.getCard()));
                }
            }
            cardsOnTable.add(row);
        }
        returnValue.set(Category.OUTPUT.getCategory(), cardsOnTable);
        return returnValue;
    }

    /**
     *
     * @param actionsInput the current command
     * @param game the current game
     * @return return value
     */
    public static ObjectNode placeCard(final ActionsInput actionsInput, final Game game) {
        boolean notEnoughMana = false;
        boolean notEnoughSpace = true;
        ObjectNode returnValue = null;
        int row;

        if (game.getPlayerOne().isHisTurn()) {
            Card card = game.getPlayerOne().getHand().get(actionsInput.getHandIdx());
            if (game.getPlayerOne().getMana() < card.getCard().getMana()) {
                notEnoughMana = true;
            }
            if (!notEnoughMana) {
                row = Constants.PLAYER_ONE_BACK.getValue();
                for (BackRowMinions backRowMinion : BackRowMinions.values()) {
                    if (card.getCard().getName().equals(backRowMinion.getName())) {
                        for (int col = 0; col < Constants.TABLE_COLS.getValue(); col++) {
                            if (game.getGameTable()[row][col] == null) {
                                game.getGameTable()[row][col] = card;
                                notEnoughSpace = false;
                                break;
                            }
                        }
                    }
                }

                row = Constants.PLAYER_ONE_FRONT.getValue();
                for (FrontRowMinions frontRowMinion : FrontRowMinions.values()) {
                    if (card.getCard().getName().equals(frontRowMinion.getName())) {
                        for (int col = 0; col < Constants.TABLE_COLS.getValue(); col++) {
                            if (game.getGameTable()[row][col] == null) {
                                game.getGameTable()[row][col] = card;
                                notEnoughSpace = false;
                                break;
                            }
                        }
                    }
                }

                if (!notEnoughSpace) {
                    game.getPlayerOne().setMana(game.getPlayerOne().getMana()
                            - card.getCard().getMana());
                    game.getPlayerOne().getHand().remove(actionsInput.getHandIdx());
                }
            }
        } else {
            Card card = game.getPlayerTwo().getHand().get(actionsInput.getHandIdx());
            if (game.getPlayerTwo().getMana() < card.getCard().getMana()) {
                notEnoughMana = true;
            }
            if (!notEnoughMana) {
                row = Constants.PLAYER_TWO_BACK.getValue();
                for (BackRowMinions backRowMinion : BackRowMinions.values()) {
                    if (card.getCard().getName().equals(backRowMinion.getName())) {
                        for (int col = 0; col < Constants.TABLE_COLS.getValue(); col++) {
                            if (game.getGameTable()[row][col] == null) {
                                game.getGameTable()[row][col] = card;
                                notEnoughSpace = false;
                                break;
                            }
                        }
                    }
                }

                row = Constants.PLAYER_TWO_FRONT.getValue();
                for (FrontRowMinions frontRowMinion : FrontRowMinions.values()) {
                    if (card.getCard().getName().equals(frontRowMinion.getName())) {
                        for (int col = 0; col < Constants.TABLE_COLS.getValue(); col++) {
                            if (game.getGameTable()[row][col] == null) {
                                game.getGameTable()[row][col] = card;
                                notEnoughSpace = false;
                                break;
                            }
                        }
                    }
                }

                if (!notEnoughSpace) {
                    game.getPlayerTwo().setMana(game.getPlayerTwo().getMana()
                            - card.getCard().getMana());
                    game.getPlayerTwo().getHand().remove(actionsInput.getHandIdx());
                }
            }
        }

        if (notEnoughMana || notEnoughSpace) {
            ObjectMapper objectMapper = new ObjectMapper();
            returnValue = objectMapper.createObjectNode();
            returnValue.put(Category.COMMAND.getCategory(), actionsInput.getCommand());
            returnValue.put(Category.HAND_INDEX.getCategory(), actionsInput.getHandIdx());
            if (notEnoughMana) {
                returnValue.put(Category.ERROR.getCategory(),
                        ErrorMessage.NOT_ENOUGH_MANA_MINION.getMessage());
            } else {
                returnValue.put(Category.ERROR.getCategory(),
                        ErrorMessage.NOT_ENOUGH_SPACE.getMessage());
            }
        }
        return returnValue;
    }

    /**
     *
     * @param game the current game
     */
    public static void switchTurns(final Game game) {
        game.getPlayerOne().setHisTurn(!game.getPlayerOne().isHisTurn());
        game.getPlayerTwo().setHisTurn(!game.getPlayerTwo().isHisTurn());
    }

    /**
     *
     * @param game the current game
     */
    public static void checkRound(final Game game) {
        if (game.getTurn() % 2 != 0) {

            // mana
            if (game.getMana() < Constants.MAX_MANA.getValue()) {
                game.setMana(game.getMana() + 1);
            }
            game.getPlayerOne().setMana(game.getMana() + game.getPlayerOne().getMana());
            game.getPlayerTwo().setMana(game.getMana() + game.getPlayerTwo().getMana());

            // draw card
            if (!game.getPlayerOne().getDeck().isEmpty()) {
                game.getPlayerOne().getHand().add(game.getPlayerOne().getDeck().getFirst());
                game.getPlayerOne().getDeck().removeFirst();
            }
            if (!game.getPlayerTwo().getDeck().isEmpty()) {
                game.getPlayerTwo().getHand().add(game.getPlayerTwo().getDeck().getFirst());
                game.getPlayerTwo().getDeck().removeFirst();
            }
        }
    }
}
