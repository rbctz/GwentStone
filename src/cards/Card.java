package cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.CardType;
import fileio.CardInput;

import java.util.ArrayList;

public abstract class Card {

    protected CardType cardType;
    protected int mana;
    protected int health;
    protected String description;
    protected String name;
    protected ArrayList<String> colors;
    protected boolean attacked;

    public Card(final CardInput cardInput, final CardType cardType) {
        this.mana = cardInput.getMana();
        this.description = cardInput.getDescription();
        this.name = cardInput.getName();
        this.colors = cardInput.getColors();
        this.cardType = cardType;
        this.attacked = false;
    }

    /**
     * Copy constructor.
     */
    public Card(final Card card) {
        this.mana = card.mana;
        this.health = card.health;
        this.description = card.description;
        this.name = card.name;
        this.colors = card.colors;
        this.cardType = card.cardType;
        this.attacked = card.attacked;
    }

    /**
     * Method that modifies the health of a card.
     */
    public void takeDamage(final int damage) {
        this.health -= damage;
    }

    /**
     * Method that returns the card type.
     */
    @JsonIgnore
    public CardType getCardType() {
        return cardType;
    }

    /**
     * Method that returns the mana cost of a card.
     */
    public int getMana() {
        return mana;
    }

    /**
     * Method that sets the mana cost of a card.
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * Method that returns the health of a card.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Method that sets the health of a card.
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Method that returns the description of a card.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that sets the description of a card.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Method that returns the colors of a card.
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * Method that sets the colors of a card.
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * Method that returns the name of a card.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that sets the name of a card.
     */
    public void setName(final String name) {
        this.name = name;
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
}
