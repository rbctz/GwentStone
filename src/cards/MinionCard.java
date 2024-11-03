package cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /**
     * Copy constructor.
     */
    public MinionCard(final MinionCard minionCard) {
        super(minionCard);
        this.health = minionCard.health;
        this.attackDamage = minionCard.attackDamage;
        this.frozen = minionCard.frozen;
        this.attacked = minionCard.attacked;
        this.isTank = minionCard.isTank;
        this.row = minionCard.row;
        this.minionType = minionCard.minionType;
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

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    @JsonIgnore
    public boolean getAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    @JsonIgnore
    public boolean getIsTank() {
        return isTank;
    }

    @JsonIgnore
    public MinionType getMinionType() {
        return minionType;
    }

    @JsonIgnore
    public int getRow() {
        return row;
    }
    @JsonIgnore
    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
