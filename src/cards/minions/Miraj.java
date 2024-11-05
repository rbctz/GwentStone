package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.CardInput;
import fileio.Coordinates;
import game.Game;

public final class Miraj extends MinionCard {
    public Miraj(final CardInput cardInput) {
        super(cardInput, false, 1, MinionType.MIRAJ);
    }

    public Miraj(final MinionCard minionCard) {
        super(minionCard);
    }

    /**
     * The ability of Miraj is to swap health with a minion.
     *
     * @param game        The game.
     * @param coordinates The coordinates of the minion.
     */
    @Override
    public void useAbility(final Game game, final Coordinates coordinates) {
        MinionCard minionCard = game.getGameBoard().getCardFromTable(coordinates);
        int auxHealth = minionCard.getHealth();
        minionCard.setHealth(this.getHealth());
        this.setHealth(auxHealth);
    }
}
