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
    protected boolean usedAbility;

    public Card(final CardInput cardInput, final CardType cardType) {
        this.mana = cardInput.getMana();
        this.description = cardInput.getDescription();
        this.name = cardInput.getName();
        this.colors = cardInput.getColors();
        this.usedAbility = false;
        this.cardType = cardType;
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
        this.usedAbility = card.usedAbility;
        this.cardType = card.cardType;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    @JsonIgnore
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public boolean isUsedAbility() {
        return usedAbility;
    }

    public void setUsedAbility(boolean usedAbility) {
        this.usedAbility = usedAbility;
    }
}
