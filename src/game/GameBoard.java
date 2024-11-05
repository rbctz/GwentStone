package game;

import cards.MinionCard;
import fileio.Coordinates;

import java.util.ArrayList;

public final class GameBoard {

    private final ArrayList<ArrayList<MinionCard>> board;

    private static final int BOARD_ROWS = 4;
    private static final int BOARD_COLUMNS = 5;

    public GameBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < BOARD_ROWS; i++) {
            board.add(new ArrayList<>());
        }
    }

    public ArrayList<ArrayList<MinionCard>> getBoard() {
        return board;
    }

    public static int getBoardRows() {
        return BOARD_ROWS;
    }

    /**
     * Returns all cards on the board.
     * @return all cards on the board
     */
    public ArrayList<ArrayList<MinionCard>> getAllCardsOnTable() {
        return new ArrayList<>(board);
    }

    /**
     * Returns all cards on the board that are frozen.
     * @return all cards on the board that are frozen
     */
    public ArrayList<MinionCard> getAllFrozenCardsOnTable() {
        ArrayList<MinionCard> frozenCards = new ArrayList<>();
        for (ArrayList<MinionCard> row : board) {
            for (MinionCard card : row) {
                if (card != null && card.isFrozen()) {
                    frozenCards.add(card);
                }
            }
        }
        return frozenCards;
    }

    /**
     * Checks if a row is full.
     * @param row the row to check
     * @return true if the row is full, false otherwise
     */
    public boolean isRowFull(final int row) {
        return board.get(row).size() == BOARD_COLUMNS;
    }

    /**
     * Places a card on the board.
     * @param card the card to place
     * @param row the row to place the card on
     */
    public void placeCard(final MinionCard card, final int row) {
        board.get(row).add(card);
    }

    /**
     * Removes a card from the board.
     * @param coordinates the coordinates of the card to remove
     */
    public void removeCard(final Coordinates coordinates) {
        board.get(coordinates.getX()).remove(coordinates.getY());
    }

    /**
     * Returns the card at the given coordinates.
     * @param coordinates the coordinates of the card
     * @return the card at the given coordinates
     */
    public MinionCard getCardFromTable(final Coordinates coordinates) {
        return board.get(coordinates.getX()).get(coordinates.getY());
    }

    /**
     * Checks if a card already attacked.
     * @param coordinates the coordinates of the card
     * @return true if the card already attacked, false otherwise
     */
    public boolean cardAttacked(final Coordinates coordinates) {
        return board.get(coordinates.getX()).get(coordinates.getY()).getAttacked();
    }

    /**
     * Unfreezes all cards on the specified row.
     * @param row the row to unfreeze
     */
    public void unfreezeAllCardsOnRow(final int row) {
        for (MinionCard card : board.get(row)) {
            if (card != null) {
                card.unfreeze();
            }
        }
    }

    /**
     * Resets the attacked status of all cards on the specified row.
     * @param row the row to reset
     */
    public void resetAttackedOnRow(final int row) {
        for (MinionCard card : board.get(row)) {
            if (card != null) {
                card.setAttacked(false);
            }
        }
    }

    /**
     * Checks if a card is on the board is
     *  an enemy of the current player
     * @param player - the player
     * @param coordinates - the coordinates
     * @return true if the card is an enemy, false otherwise
     */
    public boolean isEnemy(final Player player, final Coordinates coordinates) {
        return coordinates.getX() != player.getBackRow()
                && coordinates.getX() != player.getFrontRow();
    }
}
