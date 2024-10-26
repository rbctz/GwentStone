package game;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Command;
import enums.Constants;
import fileio.ActionsInput;
import fileio.CardInput;
import java.util.ArrayList;
import static game.Actions.getPlayerDeck;
import static game.Actions.getPlayerHero;
import static game.Actions.getPlayerTurn;


public final class Game {

    private final Player playerOne, playerTwo;
    private int mana;
    private int round;
    private Card[][] table
            = new Card[Constants.TABLE_ROWS.getValue()][Constants.TABLE_COLS.getValue()];

    public Game(final int startingPlayer,
                final ArrayList<CardInput> deckOne,
                final ArrayList<CardInput> deckTwo,
                final CardInput heroOne,
                final CardInput heroTwo) {
        playerOne = new Player(deckOne, heroOne);
        playerTwo = new Player(deckTwo, heroTwo);
        if (startingPlayer == 1) {
            playerOne.setHisTurn(true);
        } else {
            playerTwo.setHisTurn(true);
        }
        round = 2;
        mana = 1;
        playerOne.getHand().add(playerOne.getDeck().getFirst());
        playerOne.getDeck().removeFirst();
        playerTwo.getHand().add(playerTwo.getDeck().getFirst());
        playerTwo.getDeck().removeFirst();
    }

    /**
     *
     * @param actionsInput
     * @return
     */
    public ObjectNode execute(final ActionsInput actionsInput) {
        ObjectNode value = null;
        for (Command command : Command.values()) {
            if (command.getCommand().equals(actionsInput.getCommand())) {
                switch (command) {
                    case GET_PLAYER_DECK:
                        value = getPlayerDeck(actionsInput, playerOne, playerTwo);
                        break;
                    case GET_PLAYER_HERO:
                        value = getPlayerHero(actionsInput, playerOne, playerTwo);
                        break;
                    case GET_PLAYER_MANA:
                        break;
                    case GET_PLAYER_TURN:
                        value = getPlayerTurn(playerOne);
                        break;
                    case END_PLAYER_TURN:
                        break;
                    case PLACE_CARD:
                        break;
                    case CARD_USES_ATTACK:
                        break;
                    case CARD_USES_ABILITY:
                        break;
                    case USE_ATTACK_HERO:
                        break;
                    case USE_HERO_ABILITY:
                        break;
                    case GET_CARDS_IN_HAND:
                        break;
                    case GET_CARDS_ON_TABLE:
                        break;
                    case GET_CARD_AT_POSITION:
                        break;
                    case GET_FROZEN_CARDS_ON_TABLE:
                        break;
                    case GET_TOTAL_GAMES_PLAYED:
                        break;
                    case GET_PLAYER_ONE_WINS:
                        break;
                    case GET_PLAYER_TWO_WINS:
                        break;
                    case null:
                        break;
                }
            }
        }
        return value;
    }
}
