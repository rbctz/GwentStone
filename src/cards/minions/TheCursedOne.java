package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.ActionsInput;
import fileio.CardInput;
import game.Game;

public final class TheCursedOne extends MinionCard {
    public TheCursedOne(final CardInput cardInput) {
        super(cardInput, false, 0, MinionType.THE_CURSED_ONE);
    }

    @Override
    public void useAbility(final Game game, final ActionsInput actionsInput) {

    }
}
