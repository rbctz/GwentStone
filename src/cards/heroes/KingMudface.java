package cards.heroes;

import cards.HeroCard;
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

    }
}
