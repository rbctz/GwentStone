package game;

import enums.Command;
import fileio.ActionsInput;
import fileio.Input;
import fileio.StartGameInput;

import java.util.Collections;
import java.util.Random;

import static enums.Constants.MAX_MANA;

public final class Game {

    private Random random;
    private final Parser parser;
    private Player playerOne;
    private Player playerTwo;
    private GameBoard gameBoard;
    private Player currentPlayerTurn;

    private int turnsPlayed = 0;
    private int manaPerTurn = 1;


    public Game(final Parser parser) {
        this.parser = parser;
    }

    /**
     * Starts the game.
     * @param input
     * @param index
     */
    public void startGame(final Input input, final int index) {
        final StartGameInput startGameInput = input.getGames().get(index).getStartGame();

        int startingPlayer = startGameInput.getStartingPlayer();

        playerOne = new Player(input.getPlayerOneDecks().getDecks()
                .get(startGameInput.getPlayerOneDeckIdx()),
                startGameInput.getPlayerOneHero(), 2, 3);
        playerTwo = new Player(input.getPlayerTwoDecks().getDecks()
                .get(startGameInput.getPlayerTwoDeckIdx()),
                startGameInput.getPlayerTwoHero(), 1, 0);

        playerOne.drawCard();
        playerTwo.drawCard();

        gameBoard = new GameBoard();

        random = new Random(startGameInput.getShuffleSeed());
        Collections.shuffle(playerOne.getDeck(), random);
        random = new Random(startGameInput.getShuffleSeed());
        Collections.shuffle(playerTwo.getDeck(), random);

        if (startingPlayer == 1) {
            currentPlayerTurn = playerOne;
        } else {
            currentPlayerTurn = playerTwo;
        }

        final Actions actions = new Actions(parser, this);
        for (ActionsInput actionsInput : input.getGames().get(index).getActions()) {
            actions.parseActions(actionsInput);
            if (!parser.getArrayNodeOutput().isEmpty() &&
                parser.getArrayNodeOutput().get(parser.getArrayNodeOutput().size() - 1)
                .has(Command.GAME_ENDED.getCommand())) {
                break;
            }
        }

    }

    public void endTurn() {
        gameBoard.unfreezeAllCardsOnRow(currentPlayerTurn.getBackRow());
        gameBoard.unfreezeAllCardsOnRow(currentPlayerTurn.getFrontRow());

        gameBoard.resetAttackedOnRow(currentPlayerTurn.getBackRow());
        gameBoard.resetAttackedOnRow(currentPlayerTurn.getFrontRow());

        if (currentPlayerTurn == playerOne) {
            currentPlayerTurn = playerTwo;
        } else {
            currentPlayerTurn = playerOne;
        }

        this.turnsPlayed++;
    }

    public void endRound() {
        if (manaPerTurn < MAX_MANA.getValue()) {
            manaPerTurn++;
        }
        playerOne.setMana(playerOne.getMana() + manaPerTurn);
        playerTwo.setMana(playerTwo.getMana() + manaPerTurn);
        playerOne.drawCard();
        playerTwo.drawCard();
    }

    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
