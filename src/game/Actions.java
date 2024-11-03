package game;

import cards.Card;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Command;
import enums.OutputMessage;
import fileio.ActionsInput;

import java.util.ArrayList;

public final class Actions {

    private final Parser parser;
    private final Game game;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Actions(final Parser parser, final Game game) {
        this.parser = parser;
        this.game = game;
    }

    public void parseActions(ActionsInput actionsInput) {
        for (Command command : Command.values()) {
            if (command.getCommand().equals(actionsInput.getCommand())) {
                switch (command) {
                    case END_PLAYER_TURN:
                        endPlayerTurn();
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
                    case GET_PLAYER_DECK:
                        getPlayerDeck(actionsInput);
                        break;
                    case GET_PLAYER_HERO:
                        break;
                    case GET_PLAYER_MANA:
                        break;
                    case GET_PLAYER_TURN:
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
                    case GAME_ENDED:
                        break;
                }
            }
        }
    }

    private void endPlayerTurn() {
        game.endTurn();
        if (game.getTurnsPlayed() % 2 == 0) {
            game.endRound();
        }
    }

    private void getPlayerDeck(final ActionsInput actionsInput) {
        ArrayList<Card> deck;
        if (actionsInput.getPlayerIdx() == 1) {
            deck = game.getPlayerOne().getDeck();
        } else {
            deck = game.getPlayerTwo().getDeck();
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put(OutputMessage.COMMAND.getMessage(), actionsInput.getCommand());
        objectNode.put(OutputMessage.PLAYER_INDEX.getMessage(), actionsInput.getPlayerIdx());
        ArrayNode deckArrayNode = objectMapper.createArrayNode();
        if (actionsInput.getPlayerIdx() == 1) {
            for (Card card : deck) {
                deckArrayNode.add(card.toString());
            }
        } else {
            for (Card card : deck) {
                deckArrayNode.add(card.toString());
            }
        }
        objectNode.set(OutputMessage.OUTPUT.getMessage(), deckArrayNode);
        parser.getArrayNodeOutput().add(objectNode);
    }

}



