package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.Coordinates;
import game.Game;

public final class Disciple extends MinionCard {
    public Disciple(CardInput cardInput) {
        super(cardInput, false, 0, MinionType.DISCIPLE);
    }

    public Disciple(final MinionCard minionCard) {
        super(minionCard);
    }

    @Override
    public void useAbility(final Game game, final Coordinates coordinates) {
        MinionCard minionCard = game.getGameBoard().getCardFromTable(coordinates);
        minionCard.setHealth(minionCard.getHealth() + 2);
    }
}
