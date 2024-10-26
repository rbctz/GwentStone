package game;

import fileio.CardInput;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private Card hero;

    private int mana;
    private boolean hisTurn;

    private int wins;

    public Player(ArrayList<CardInput> deck, CardInput hero) {
        this.deck = new ArrayList<>();
        for (CardInput cardInput : deck)
            this.deck.add(new Card(cardInput));
        this.hand = new ArrayList<>();
        this.hero = new Card(hero);

        this.mana = 0;
        this.hisTurn = false;
        this.wins = 0;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Card getHero() {
        return hero;
    }

    public void setHero(Card hero) {
        this.hero = hero;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isHisTurn() {
        return hisTurn;
    }

    public void setHisTurn(boolean hisTurn) {
        this.hisTurn = hisTurn;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
