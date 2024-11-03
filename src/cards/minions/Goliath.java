package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.CardInput;

public final class Goliath extends MinionCard {

    public Goliath(final CardInput cardInput) {
        super(cardInput, true, 1, MinionType.GOLIATH);
    }

    public Goliath(final MinionCard minionCard) {
        super(minionCard);
    }
}
