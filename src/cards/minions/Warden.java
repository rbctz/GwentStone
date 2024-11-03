package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.CardInput;

public final class Warden extends MinionCard {

    public Warden(final CardInput cardInput) {
        super(cardInput, true, 1, MinionType.WARDEN);
    }

    public Warden(final MinionCard minionCard) {
        super(minionCard);
    }
}
