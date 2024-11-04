package cards.heroes;

import cards.HeroCard;
import cards.MinionCard;
import enums.HeroType;
import fileio.CardInput;
import game.GameBoard;

public final class GeneralKocioraw extends HeroCard {
    public GeneralKocioraw(final CardInput cardInput) {
        super(cardInput, HeroType.GENERAL_KOCIORAW);
    }

    public GeneralKocioraw(final HeroCard heroCard) {
        super(heroCard);
    }


    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {
        for (MinionCard minionCard : gameBoard.getBoard().get(row)) {
            minionCard.setAttackDamage(minionCard.getAttackDamage() + 1);
        }
    }
}
