package game;

import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.CardUsesAttack;
import enums.Command;
import enums.Constants;
import fileio.ActionsInput;
import fileio.CardInput;
import java.util.ArrayList;

import static commands.AttackCommandSuper.cardUsesAttack;
import static commands.Commands.*;


public final class Game {

    private Player playerOne, playerTwo;
    private int mana;
    private int turn;
    private Card[][] gameTable
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
        turn = 1;
        mana = 1;
        playerOne.getHand().add(playerOne.getDeck().getFirst());
        playerOne.getDeck().removeFirst();
        playerTwo.getHand().add(playerTwo.getDeck().getFirst());
        playerTwo.getDeck().removeFirst();

        for (int i = 0; i < Constants.TABLE_ROWS.getValue(); i++) {
            for (int j = 0; j < Constants.TABLE_COLS.getValue(); j++) {
                gameTable[i][j] = null;
            }
        }
    }

    /**
     *
     * @param actionsInput the current command
     * @return the return value of the command called
     */
    public ObjectNode execute(final ActionsInput actionsInput) {
        ObjectNode value = null;
        for (Command command : Command.values()) {
            if (actionsInput.getCommand().equals(command.getCommand())) {
                switch (command) {
                    case GET_PLAYER_DECK:
                        value = getPlayerDeck(actionsInput, playerOne, playerTwo);
                        break;
                    case GET_PLAYER_HERO:
                        value = getPlayerHero(actionsInput, playerOne, playerTwo);
                        break;
                    case GET_PLAYER_MANA:
                        value = getPlayerMana(actionsInput, this);
                        break;
                    case GET_PLAYER_TURN:
                        value = getPlayerTurn(this);
                        break;
                    case END_PLAYER_TURN:
                        endPlayerTurn(this);
                        break;
                    case PLACE_CARD:
                        value = placeCard(actionsInput, this);
                        break;
                    case CARD_USES_ATTACK:
                        value = cardUsesAttack(actionsInput, this);
                        break;
                    case CARD_USES_ABILITY:
                        break;
                    case USE_ATTACK_HERO:
                        break;
                    case USE_HERO_ABILITY:
                        break;
                    case GET_CARDS_IN_HAND:
                        value = getCardsInHand(actionsInput, this);
                        break;
                    case GET_CARDS_ON_TABLE:
                        value = getCardsOnTable(actionsInput, this);
                        break;
                    case GET_CARD_AT_POSITION:
                        value = getCardAtPosition(actionsInput, this);
                        break;
                    case GET_FROZEN_CARDS_ON_TABLE:
                        break;
                    case GET_TOTAL_GAMES_PLAYED:
                        break;
                    case GET_PLAYER_ONE_WINS:
                        break;
                    case GET_PLAYER_TWO_WINS:
                        break;
                }
            }
        }
        return value;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(final Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(final Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(final int turn) {
        this.turn = turn;
    }

    public Card[][] getGameTable() {
        return gameTable;
    }

    public void setGameTable(final Card[][] gameTable) {
        this.gameTable = gameTable;
    }
}
