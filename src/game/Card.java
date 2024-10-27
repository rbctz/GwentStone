package game;

import fileio.CardInput;

public final class Card {

    private CardInput card;
    private boolean isFrozen;
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
        this.isFrozen = false;
        this.x = 0;
        this.y = 0;
    }

    public CardInput getCard() {
        return card;
    }

    public void setCard(CardInput card) {
        this.card = card;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public boolean isUsedAttack() {
        return usedAttack;
    }

    public void setUsedAttack(boolean usedAttack) {
        this.usedAttack = usedAttack;
    }

    public boolean isUsedAbility() {
        return usedAbility;
    }

    public void setUsedAbility(boolean usedAbility) {
        this.usedAbility = usedAbility;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
