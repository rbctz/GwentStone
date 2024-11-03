package game;

import cards.Card;
import cards.HeroCard;
import enums.Command;
import fileio.ActionsInput;

import java.util.ArrayList;

public final class Actions {

    private final Parser parser;
    private final Game game;

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
                        getPlayerDeck(actionsInput.getPlayerIdx());
                        break;
                    case GET_PLAYER_HERO:
                        getPlayerHero(actionsInput.getPlayerIdx());
                        break;
                    case GET_PLAYER_MANA:
                        break;
                    case GET_PLAYER_TURN:
                        getPlayerTurn();
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

    private void getPlayerTurn() {
        final int playerTurnIndex;
        if (game.getCurrentPlayerTurn() == game.getPlayerOne()) {
            playerTurnIndex = 1;
        } else {
            playerTurnIndex = 2;
        }
        Parser.getArrayNodeOutput()
                .addPOJO(new OutputConstructor(Command.GET_PLAYER_TURN.getCommand(), playerTurnIndex));
    }

    private void getPlayerDeck(final int playerIdx) {
        ArrayList<Card> deck;
        if (playerIdx == 1) {
            deck = game.getPlayerOne().getDeck();
        } else {
            deck = game.getPlayerTwo().getDeck();
        }
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(Command.
                GET_PLAYER_DECK.getCommand(), playerIdx, deck));
    }

    private void getPlayerHero(final int playerIdx) {
        HeroCard hero;
        if (playerIdx == 1) {
            hero = game.getPlayerOne().getHero();
        } else {
            hero = game.getPlayerTwo().getHero();
        }
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(Command.
                GET_PLAYER_HERO.getCommand(), hero, playerIdx));

    }

}



