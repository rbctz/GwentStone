package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.CardInput;
import fileio.Coordinates;
import game.Game;

public final class Disciple extends MinionCard {
    public Disciple(final CardInput cardInput) {
        super(cardInput, false, 0, MinionType.DISCIPLE);
    }

    public Disciple(final MinionCard minionCard) {
        super(minionCard);
    }

    /**
     * The ability of Disciple is to increase the health of a minion by 2.
     *
     * @param game        The game.
     * @param coordinates The coordinates of the minion.
     */
    @Override
    public void useAbility(final Game game, final Coordinates coordinates) {
        MinionCard minionCard = game.getGameBoard().getCardFromTable(coordinates);
        minionCard.setHealth(minionCard.getHealth() + 2);
    }
}
