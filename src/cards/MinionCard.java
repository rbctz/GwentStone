package cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.CardType;
import enums.MinionType;
import fileio.CardInput;
import fileio.Coordinates;
import game.Game;

public abstract class MinionCard extends Card {

    protected int attackDamage;
    protected boolean frozen;

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

    /**
     * Method that freezes a card.
     */
    public void freeze() {
        this.frozen = true;
    }

    /**
     * Method that unfreezes a card.
     */
    public void unfreeze() {
        this.frozen = false;
    }

    /**
     * for overriding in subclasses.
     */
    public void useAbility(final Game game, final Coordinates coordinates) {
        // this method is for overriding
    }

    /**
     * Method that returns the attack damage of a card.
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Method that modifies the attack damage of a card.
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * Method that returns the attacked status of a card.
     */
    @JsonIgnore
    public boolean getAttacked() {
        return attacked;
    }

    /**
     * Method that sets the attacked status of a card.
     */
    public void setAttacked(final boolean attacked) {
        this.attacked = attacked;
    }

    /**
     * Method that returns the tank status of a card.
     */
    @JsonIgnore
    public boolean getIsTank() {
        return isTank;
    }

    /**
     * Method that returns the minion type of a card.
     */
    @JsonIgnore
    public MinionType getMinionType() {
        return minionType;
    }

    /**
     * Method that returns the row of a card.
     */
    @JsonIgnore
    public int getRow() {
        return row;
    }

    /**
     * Method that returns the frozen status of a card.
     */
    @JsonIgnore
    public boolean isFrozen() {
        return frozen;
    }
}
