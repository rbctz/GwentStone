package cards.heroes;

import cards.HeroCard;
import cards.MinionCard;
import enums.HeroType;
import fileio.CardInput;
import game.GameBoard;

public final class LordRoyce extends HeroCard {
    public LordRoyce(final CardInput cardInput) {
        super(cardInput, HeroType.LORD_ROYCE);
    }

    public LordRoyce(final HeroCard heroCard) {
        super(heroCard);
    }

    /**
     * The ability of Lord Royce is to freeze all minions in a row.
     *
     * @param gameBoard The board of the game.
     * @param row      The row in which the ability is used.
     */
    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {
        for (MinionCard minionCard : gameBoard.getBoard().get(row)) {
            minionCard.freeze();
        }
    }
}
