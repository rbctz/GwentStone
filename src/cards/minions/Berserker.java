package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.CardInput;

public final class Berserker extends MinionCard {

    public Berserker(final CardInput cardInput) {
        super(cardInput, false, 0, MinionType.BERSERKER);
    }

    public Berserker(final MinionCard minionCard) {
        super(minionCard);
    }
}
