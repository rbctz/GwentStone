package cards.heroes;

import cards.HeroCard;
import enums.HeroType;
import fileio.CardInput;
import fileio.Coordinates;
import game.GameBoard;

public final class EmpressThorina extends HeroCard {
    public EmpressThorina(final CardInput cardInput) {
        super(cardInput, HeroType.EMPRESS_THORINA);
    }

    public EmpressThorina(final HeroCard heroCard) {
        super(heroCard);
    }

    /**
     * The ability of Empress Thorina is to remove the minion with the highest health from a row.
     *
     * @param gameBoard The board of the game.
     * @param row      The row in which the ability is used.
     */
    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(row);
        int maxHealth = 0;
        int colIterator;
        for (colIterator = 0; colIterator < gameBoard.getBoard().get(row).size(); colIterator++) {
            int minionHealth = gameBoard.getBoard().get(row).get(colIterator).getHealth();
            if (minionHealth > maxHealth) {
                maxHealth = minionHealth;
                coordinates.setY(colIterator);
            }
        }
        gameBoard.removeCard(coordinates);
    }
}
