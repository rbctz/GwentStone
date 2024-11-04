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
     * Freeze all enemy cards on the affected row
     * @param gameBoard
     * @param row
     */
    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {
        for (MinionCard minionCard : gameBoard.getBoard().get(row)) {
            minionCard.freeze();
        }
    }
}
