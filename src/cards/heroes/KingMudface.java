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

    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {
        for (MinionCard minionCard : gameBoard.getBoard().get(row)) {
            minionCard.setHealth(minionCard.getHealth() + 1);
        }
    }
}
