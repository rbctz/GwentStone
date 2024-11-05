package game;

import cards.MinionCard;
import enums.Command;
import enums.Constants;
import fileio.ActionsInput;
import fileio.Coordinates;
import fileio.Input;
import fileio.StartGameInput;

import java.util.Collections;
import java.util.Random;

import static enums.Constants.MAX_MANA;

public final class Game {

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
     * @param input input file
     * @param index index of the game to be started
     */
    public void startGame(final Input input, final int index) {
        final StartGameInput startGameInput = input.getGames().get(index).getStartGame();

        int startingPlayer = startGameInput.getStartingPlayer();

        playerOne = new Player(input.getPlayerOneDecks().getDecks()
                .get(startGameInput.getPlayerOneDeckIdx()),
                startGameInput.getPlayerOneHero(),
                Constants.PLAYER_ONE_FRONT.getValue(),
                Constants.PLAYER_ONE_BACK.getValue());
        playerTwo = new Player(input.getPlayerTwoDecks().getDecks()
                .get(startGameInput.getPlayerTwoDeckIdx()),
                startGameInput.getPlayerTwoHero(),
                Constants.PLAYER_TWO_FRONT.getValue(),
                Constants.PLAYER_TWO_BACK.getValue());
        gameBoard = new GameBoard();

        Random random = new Random(startGameInput.getShuffleSeed());
        Collections.shuffle(playerOne.getDeck(), random);
        random = new Random(startGameInput.getShuffleSeed());
        Collections.shuffle(playerTwo.getDeck(), random);

        playerOne.drawCard();
        playerTwo.drawCard();

        if (startingPlayer == 1) {
            currentPlayerTurn = playerOne;
        } else {
            currentPlayerTurn = playerTwo;
        }

        final Actions actions = new Actions(parser, this);
        for (ActionsInput actionsInput : input.getGames().get(index).getActions()) {
            actions.parseActions(actionsInput);
            if (!Parser.getArrayNodeOutput().isEmpty()
                    && Parser.getArrayNodeOutput().get(Parser.getArrayNodeOutput().size() - 1)
                    .has(Command.GAME_ENDED.getCommand())) {
                break;
            }
        }

    }

    /**
     * Ends a players turn.
     */
    public void endTurn() {

        // Unfreeze all the players cards on the board
        gameBoard.unfreezeAllCardsOnRow(currentPlayerTurn.getBackRow());
        gameBoard.unfreezeAllCardsOnRow(currentPlayerTurn.getFrontRow());

        // Reset the attacked status of all the players cards on the board
        gameBoard.resetAttackedOnRow(currentPlayerTurn.getBackRow());
        gameBoard.resetAttackedOnRow(currentPlayerTurn.getFrontRow());

        // Reset the attacked status of the players hero
        currentPlayerTurn.getHero().setAttacked(false);

        // Switch the current player
        if (currentPlayerTurn == playerOne) {
            currentPlayerTurn = playerTwo;
        } else {
            currentPlayerTurn = playerOne;
        }

        this.turnsPlayed++;
    }

    /**
     * Ends a round.
     */
    public void endRound() {
        if (manaPerTurn < MAX_MANA.getValue()) {
            manaPerTurn++;
        }
        playerOne.setMana(playerOne.getMana() + manaPerTurn);
        playerTwo.setMana(playerTwo.getMana() + manaPerTurn);
        playerOne.drawCard();
        playerTwo.drawCard();
    }

    /**
     * Places a card on the board.
     * @param minionCard card to be placed
     */
    public void placeCard(final MinionCard minionCard) {
        if (minionCard.getRow() == 0) {
            gameBoard.placeCard(minionCard, currentPlayerTurn.getBackRow());
        } else {
            gameBoard.placeCard(minionCard, currentPlayerTurn.getFrontRow());
        }
        currentPlayerTurn.reduceMana(minionCard.getMana());
        if (minionCard.getIsTank()) {
            currentPlayerTurn.addTankOnBoard();
        }
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

    public Player getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Checks if the enemy player has tanks on the board.
     * @return boolean value if the enemy has tanks on the board
     */
    public boolean enemyHasTanks() {
        if (currentPlayerTurn == playerOne) {
            return playerTwo.getNumTanksOnBoard() > 0;
        } else {
            return playerOne.getNumTanksOnBoard() > 0;
        }
    }

    /**
     * Attacks a minion with another minion.
     * @param attackCoordinates coordinates of the attacking card
     * @param targetCoordinates coordinates of the target card
     */
    public void cardAttacksMinion(final Coordinates attackCoordinates,
                                  final Coordinates targetCoordinates) {

        MinionCard attacker = gameBoard.getCardFromTable(attackCoordinates);
        MinionCard target = gameBoard.getCardFromTable(targetCoordinates);

        if (attacker.getAttackDamage() >= target.getHealth()) {
            if (target.getIsTank()) {
                if (currentPlayerTurn == playerOne) {
                    playerTwo.removeTankFromBoard();
                } else {
                    playerOne.removeTankFromBoard();
                }
            }
            gameBoard.removeCard(targetCoordinates);
        } else {
            target.takeDamage(attacker.getAttackDamage());
        }
    }
}
