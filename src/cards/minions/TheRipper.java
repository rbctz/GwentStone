package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.CardInput;
import fileio.Coordinates;
import game.Game;

public final class TheRipper extends MinionCard {
    public TheRipper(final CardInput cardInput) {
        super(cardInput, false, 1, MinionType.THE_RIPPER);
    }

    public TheRipper(final MinionCard minionCard) {
        super(minionCard);
    }

    /**
     * The ability of The Ripper is to decrease the
     * attack damage of a minion by 2.
     *
     * @param game        The game.
     * @param coordinates The coordinates of the minion.
     */
    @Override
    public void useAbility(final Game game, final Coordinates coordinates) {
        MinionCard minionCard = game.getGameBoard().getCardFromTable(coordinates);
        minionCard.setAttackDamage(minionCard.getAttackDamage() - 2);
        if (minionCard.getAttackDamage() < 0) {
            minionCard.setAttackDamage(0);
        }
    }
}
