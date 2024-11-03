package cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.CardType;
import enums.Constants;
import enums.HeroType;
import fileio.CardInput;
import game.GameBoard;

public abstract class HeroCard extends Card {

    @JsonIgnore
    protected HeroType heroType;

    public HeroCard(final CardInput cardInput, final HeroType heroType) {
        super(cardInput, CardType.HERO);
        this.health = Constants.HERO_HEALTH.getValue();
        this.heroType = heroType;
    }

    public HeroCard(final HeroCard heroCard) {
        super(heroCard);
        this.heroType = heroCard.heroType;
    }

    /**
     * for overriding in subclasses.
     */
    public abstract void useAbility(GameBoard gameBoard, int row);
}
