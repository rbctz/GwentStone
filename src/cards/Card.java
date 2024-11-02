package cards;

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

    protected Card(final CardInput cardInput, final CardType cardType) {
        this.mana = cardInput.getMana();
        this.description = cardInput.getDescription();
        this.name = cardInput.getName();
        this.colors = cardInput.getColors();
        this.usedAbility = false;
        this.cardType = cardType;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }
}
