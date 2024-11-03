package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.ActionsInput;
import fileio.CardInput;
import game.Game;

public final class TheRipper extends MinionCard {
    public TheRipper(final CardInput cardInput) {
        super(cardInput, false, 1, MinionType.THE_RIPPER);
    }

    public TheRipper(final MinionCard minionCard) {
        super(minionCard);
    }

    @Override
    public void useAbility(final Game game, final ActionsInput actionsInput) {

    }
}
