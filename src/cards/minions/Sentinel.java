package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.CardInput;

public final class Sentinel extends MinionCard {

    public Sentinel(final CardInput cardInput) {
        super(cardInput, false, 0, MinionType.SENTINEL);
    }

    public Sentinel(final MinionCard minionCard) {
        super(minionCard);
    }
}
