package cards.minions;

import cards.MinionCard;
import enums.MinionType;
import fileio.CardInput;
import fileio.Coordinates;
import game.Game;

public final class TheCursedOne extends MinionCard {
    public TheCursedOne(final CardInput cardInput) {
        super(cardInput, false, 0, MinionType.THE_CURSED_ONE);
    }

    public TheCursedOne(final MinionCard minionCard) {
        super(minionCard);
    }

    @Override
    public void useAbility(final Game game, final Coordinates coordinates) {
        final MinionCard target = game.getGameBoard().getCardFromTable(coordinates);
        if (target.getAttackDamage() == 0) {
            game.getGameBoard().removeCard(coordinates);
            if (target.getIsTank()) {
                if (game.getCurrentPlayerTurn() == game.getPlayerOne()) {
                    game.getPlayerTwo().removeTankFromBoard();
                } else {
                    game.getPlayerOne().removeTankFromBoard();
                }
            }
        } else {
            int auxHealth = target.getHealth();
            target.setHealth(target.getAttackDamage());
            target.setAttackDamage(auxHealth);
        }
    }
}
