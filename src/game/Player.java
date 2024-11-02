package game;

import cards.Card;
import cards.CardHelper;
import cards.HeroCard;
import fileio.CardInput;

import java.util.ArrayList;

public final class Player {

    private final ArrayList<Card> deck;
    private final ArrayList<Card> hand;

    private int mana;
    private int numTanksOnBoard = 0;
    private final HeroCard hero;

    private final int frontRow;
    private final int backRow;

    public Player(final ArrayList<CardInput> deck, final CardInput hero,
                  final int frontRow, final int backRow) {

        CardHelper cardHelper = new CardHelper();
        this.deck = new ArrayList<>();
        for (CardInput cardInput : deck) {
            this.deck.add(cardHelper.createCard(cardInput));
        }
        this.hand = new ArrayList<>();
        this.hero = (HeroCard) cardHelper.createCard(hero);
        this.frontRow = frontRow;
        this.backRow = backRow;
        this.mana = 1;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getBackRow() {
        return backRow;
    }

    public int getFrontRow() {
        return frontRow;
    }

    public HeroCard getHero() {
        return hero;
    }

    public int getNumTanksOnBoard() {
        return numTanksOnBoard;
    }

    /**
     * adds a tank to the board
     */
    public void addTankOnBoard() {
        numTanksOnBoard++;
    }

    /**
     * removes a tank from the board
     */
    public void removeTankFromBoard() {
        numTanksOnBoard--;
    }

    /**
     * reduces the mana of the player by the cost of the action
     * @param actionCost the cost of the action
     */
    public void reduceMana(final int actionCost) {
        this.mana -= actionCost;
    }

    /**
     * draws a card from the deck and adds it to the hand
     */
    public void drawCard() {
        if (!deck.isEmpty()) {
            hand.add(deck.getFirst());
            deck.removeFirst();
        }
    }

}
