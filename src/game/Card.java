package game;

import fileio.CardInput;

public final class Card {

    private CardInput card;
    private boolean frozen;
    private boolean usedAttack;
    private boolean usedAbility;
    private int x;
    private int y;

    public Card(final CardInput card) {
        this.card = new CardInput();
        this.card.setName(card.getName());
        this.card.setDescription(card.getDescription());
        this.card.setColors(card.getColors());
        this.card.setMana(card.getMana());
        this.card.setAttackDamage(card.getAttackDamage());
        this.card.setHealth(card.getHealth());
        this.frozen = false;
        this.x = 0;
        this.y = 0;
    }

    public CardInput getCard() {
        return card;
    }

    public void setCard(final CardInput card) {
        this.card = card;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isUsedAttack() {
        return usedAttack;
    }

    public void setUsedAttack(final boolean usedAttack) {
        this.usedAttack = usedAttack;
    }

    public boolean isUsedAbility() {
        return usedAbility;
    }

    public void setUsedAbility(final boolean usedAbility) {
        this.usedAbility = usedAbility;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }
}
