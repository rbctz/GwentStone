package cards;

import cards.heroes.EmpressThorina;
import cards.heroes.GeneralKocioraw;
import cards.heroes.KingMudface;
import cards.heroes.LordRoyce;
import cards.minions.*;
import enums.CardType;
import enums.HeroType;
import enums.MinionType;
import fileio.CardInput;

public final class CardHelper {

    /**
     * Creates a card based on the input.
     *
     * @param cardInput the input for the card
     * @return the card created
     */
    public Card createCard(final CardInput cardInput) {
        for (MinionType minionType : MinionType.values()) {
            if (minionType.getName().equals(cardInput.getName())) {
                return switch (minionType) {
                    case GOLIATH -> new Goliath(cardInput);
                    case WARDEN -> new Warden(cardInput);
                    case SENTINEL -> new Sentinel(cardInput);
                    case BERSERKER -> new Berserker(cardInput);
                    case MIRAJ -> new Miraj(cardInput);
                    case DISCIPLE -> new Disciple(cardInput);
                    case THE_CURSED_ONE -> new TheCursedOne(cardInput);
                    case THE_RIPPER -> new TheRipper(cardInput);
                };
            }
        }

        for (HeroType heroType : HeroType.values()) {
            if (heroType.getName().equals(cardInput.getName())) {
                return switch (heroType) {
                    case EMPRESS_THORINA -> new EmpressThorina(cardInput);
                    case LORD_ROYCE -> new LordRoyce(cardInput);
                    case KING_MUDFACE -> new KingMudface(cardInput);
                    case GENERAL_KOCIORAW -> new GeneralKocioraw(cardInput);
                };
            }
        }
        return null;
    }

    /**
     * Dupes a card for output
     * OVERLOADED METHOD
     * @param card the card to create
     * @return the card created
     */
    public Card createCard(final Card card) {
        if (card.getCardType() == CardType.MINION) {
            MinionCard minionCard = (MinionCard) card;
            for (MinionType minionType : MinionType.values()) {
                if (minionType.getName().equals(card.name)) {
                    return switch (minionType) {
                        case GOLIATH -> new Goliath(minionCard);
                        case WARDEN -> new Warden(minionCard);
                        case SENTINEL -> new Sentinel(minionCard);
                        case BERSERKER -> new Berserker(minionCard);
                        case MIRAJ -> new Miraj(minionCard);
                        case DISCIPLE -> new Disciple(minionCard);
                        case THE_CURSED_ONE -> new TheCursedOne(minionCard);
                        case THE_RIPPER -> new TheRipper(minionCard);
                    };
                }
            }
        } else if (card.getCardType() == CardType.HERO) {
            HeroCard heroCard = (HeroCard) card;
            for (HeroType heroType : HeroType.values()) {
                if (heroType.getName().equals(card.name)) {
                    return switch (heroType) {
                        case EMPRESS_THORINA -> new EmpressThorina(heroCard);
                        case LORD_ROYCE -> new LordRoyce(heroCard);
                        case KING_MUDFACE -> new KingMudface(heroCard);
                        case GENERAL_KOCIORAW -> new GeneralKocioraw(heroCard);
                    };
                }
            }
        }
        return null;
    }
}
