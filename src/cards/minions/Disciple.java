package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.ActionsInput;
import fileio.CardInput;
import game.Game;

public final class Disciple extends MinionCard {
    public Disciple(CardInput cardInput) {
        super(cardInput, false, 1, MinionType.DISCIPLE);
    }

    public Disciple(final MinionCard minionCard) {
        super(minionCard);
    }

    @Override
    public void useAbility(final Game game, final ActionsInput actionsInput) {

    }
}
