package cards.heroes;

import cards.HeroCard;
import cards.MinionCard;
import enums.HeroType;
import fileio.CardInput;
import game.GameBoard;

public final class KingMudface extends HeroCard {
    public KingMudface(final CardInput cardInput) {
        super(cardInput, HeroType.KING_MUDFACE);
    }

    public KingMudface(final HeroCard heroCard) {
        super(heroCard);
    }

    /**
     * The ability of King Mudface is to increase the health of all minions in a row by 1.
     *
     * @param gameBoard The board of the game.
     * @param row      The row in which the ability is used.
     */
    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {
        for (MinionCard minionCard : gameBoard.getBoard().get(row)) {
            minionCard.setHealth(minionCard.getHealth() + 1);
        }
    }
}
