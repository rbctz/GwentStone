package game;

import fileio.CardInput;

public class Card {

    private CardInput card;
    private boolean frozen;
    private int x;
    private int y;

    public Card(CardInput card) {
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

    public void setCard(CardInput card) {
        this.card = card;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
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
