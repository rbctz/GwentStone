package game;

import cards.Card;
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
     * @return
     */
    public ArrayList<ArrayList<MinionCard>> getAllCardsOnTable() {
        return new ArrayList<>(board);
    }

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
     * @param row
     * @return
     */
    public boolean isRowFull(final int row) {
        return board.get(row).size() == BOARD_COLUMNS;
    }

    /**
     * Places a card on the board.
     * @param card
     * @param row
     */
    public void placeCard(final MinionCard card, final int row) {
        board.get(row).add(card);
    }

    /**
     * Removes a card from the board.
     * @param coordinates
     */
    public void removeCard(final Coordinates coordinates) {
        board.get(coordinates.getX()).remove(coordinates.getY());
    }

    /**
     * Returns the card at the given coordinates.
     * @param coordinates
     * @return
     */
    public MinionCard getCardFromTable(final Coordinates coordinates) {
        return board.get(coordinates.getX()).get(coordinates.getY());
    }

    /**
     * Checks if a card already attacked.
     * @param coordinates
     * @return
     */
    public boolean cardAttacked(final Coordinates coordinates) {
        return board.get(coordinates.getX()).get(coordinates.getY()).getAttacked();
    }

    /**
     * Unfreezes all cards on the specified row.
     * @param row
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
     * @param row
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
     * @return
     */
    public boolean isEnemy(final Player player, final Coordinates coordinates) {
        if (coordinates.getX() != player.getBackRow()
                && coordinates.getX() != player.getFrontRow()) {
            return true;
        }
        return false;
    }
}
