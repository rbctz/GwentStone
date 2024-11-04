package cards.heroes;

import cards.HeroCard;
import enums.HeroType;
import fileio.CardInput;
import game.GameBoard;

public final class EmpressThorina extends HeroCard {
    public EmpressThorina(final CardInput cardInput) {
        super(cardInput, HeroType.EMPRESS_THORINA);
    }

    public EmpressThorina(final HeroCard heroCard) {
        super(heroCard);
    }

    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {
    }
}
