package cards;

import enums.CardType;
import enums.MinionType;
import fileio.ActionsInput;
import fileio.CardInput;
import game.Game;

public abstract class MinionCard extends Card {

    protected int attackDamage;
    protected boolean frozen;
    protected boolean attacked;
    protected final boolean isTank;
    protected final MinionType minionType;
    protected final int row;

    public MinionCard(final CardInput cardInput, final boolean isTank,
                      final int row, final MinionType minionType) {
        super(cardInput, CardType.MINION);
        this.health = cardInput.getHealth();
        this.attackDamage = cardInput.getAttackDamage();
        this.frozen = false;
        this.attacked = false;
        this.isTank = isTank;
        this.row = row;
        this.minionType = minionType;
    }

    public void freeze() {
        this.frozen = true;
    }

    public void unfreeze() {
        this.frozen = false;
    }

    public void useAbility(final Game game, final ActionsInput actionsInput) {
        // this method is for overriding
    }

    public boolean getAttacked() {
        return attacked;
    }

    public void setAttacked(final boolean attacked) {
        this.attacked = attacked;
    }
}
