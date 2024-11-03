package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.ActionsInput;
import fileio.CardInput;
import game.Game;

public final class Miraj extends MinionCard {
    public Miraj(final CardInput cardInput) {
        super(cardInput, false, 1, MinionType.MIRAJ);
    }

    public Miraj(final MinionCard minionCard) {
        super(minionCard);
    }

    @Override
    public void useAbility(final Game game, final ActionsInput actionsInput) {

    }
}
