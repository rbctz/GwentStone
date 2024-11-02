package cards.heroes;

import cards.HeroCard;
import enums.HeroType;
import fileio.CardInput;
import game.GameBoard;

public final class LordRoyce extends HeroCard {
    public LordRoyce(final CardInput cardInput) {
        super(cardInput, HeroType.LORD_ROYCE);
    }

    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {

    }
}
