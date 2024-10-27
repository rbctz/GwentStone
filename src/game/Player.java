package game;

import enums.Constants;
import fileio.CardInput;

import java.util.ArrayList;

public final class Player {

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private Card hero;

    private int mana;
    private boolean hisTurn;

    private int wins;

    public Player(final ArrayList<CardInput> deck, final CardInput hero) {
        this.deck = new ArrayList<>();
        for (CardInput cardInput : deck) {
            this.deck.add(new Card(cardInput));
        }
        this.hand = new ArrayList<>();
        this.hero = new Card(hero);
        this.hero.getCard().setHealth(Constants.HERO_HEALTH.getValue());

        this.mana = 1;
        this.hisTurn = false;
        this.wins = 0;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(final ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(final ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Card getHero() {
        return hero;
    }

    public void setHero(final Card hero) {
        this.hero = hero;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public boolean isHisTurn() {
        return hisTurn;
    }

    public void setHisTurn(final boolean hisTurn) {
        this.hisTurn = hisTurn;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(final int wins) {
        this.wins = wins;
    }
}
