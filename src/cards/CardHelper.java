package cards;

import cards.heroes.EmpressThorina;
import cards.heroes.GeneralKocioraw;
import cards.heroes.KingMudface;
import cards.heroes.LordRoyce;
import cards.minions.*;
import enums.HeroType;
import enums.MinionType;
import fileio.CardInput;

public final class CardHelper {

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
}
