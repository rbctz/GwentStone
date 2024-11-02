package cards.heroes;

import cards.HeroCard;
import enums.HeroType;
import fileio.CardInput;
import game.GameBoard;

public final class GeneralKocioraw extends HeroCard {
    public GeneralKocioraw(final CardInput cardInput) {
        super(cardInput, HeroType.GENERAL_KOCIORAW);
    }


    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {

    }
}
