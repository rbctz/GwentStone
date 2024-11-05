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


    /**
     * The ability of General Kocioraw is to increase the
     *   attack damage of all minions in a row by 1.
     *
     * @param gameBoard The board of the game.
     * @param row      The row in which the ability is used.
     */
    @Override
    public void useAbility(final GameBoard gameBoard, final int row) {
        for (MinionCard minionCard : gameBoard.getBoard().get(row)) {
            minionCard.setAttackDamage(minionCard.getAttackDamage() + 1);
        }
    }
}
