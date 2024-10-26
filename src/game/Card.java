package game;

import fileio.CardInput;

public final class Card {

    private CardInput card;
    private boolean frozen;
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
}
