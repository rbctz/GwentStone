package commands;

import enums.Constants;
import game.Card;
import game.Game;

public final class EndPlayerTurn {
    private EndPlayerTurn() { }

    /**
     *
     * @param game the current game that is played
     * @return
     */
    public static void endPlayerTurn(final Game game) {
        HelpMethods.switchTurns(game);
        if (HelpMethods.getTurn(game) == Constants.PLAYER_ONE.getValue()) {
            for (Card card : game.getGameTable()[Constants.PLAYER_ONE_FRONT.getValue()]) {
                card.setFrozen(false);
                card.setUsedAbility(false);
                card.setUsedAttack(false);
            }
            for (Card card : game.getGameTable()[Constants.PLAYER_ONE_BACK.getValue()]) {
                card.setFrozen(false);
                card.setUsedAttack(false);
                card.setUsedAttack(false);
            }
        } else {
            for (Card card : game.getGameTable()[Constants.PLAYER_TWO_FRONT.getValue()]) {
                card.setFrozen(false);
                card.setUsedAbility(false);
                card.setUsedAttack(false);
            }
            for (Card card : game.getGameTable()[Constants.PLAYER_TWO_BACK.getValue()]) {
                card.setFrozen(false);
                card.setUsedAttack(false);
                card.setUsedAttack(false);
            }
        }
        game.setRound(game.getRound() + 1);

        // Every two turns (each player takes one turn) there is a round change
        if (game.getRound() % 2 != 0) {

            // mana
            if (game.getMana() < Constants.MAX_MANA.getValue()) {
                game.setMana(game.getMana() + 1);
            }
            game.getPlayerOne().setMana(game.getMana() + game.getPlayerOne().getMana());
            game.getPlayerTwo().setMana(game.getMana() + game.getPlayerTwo().getMana());

            // draw card
            if (!game.getPlayerOne().getDeck().isEmpty()) {
                game.getPlayerOne().getHand().add(game.getPlayerOne().getDeck().getFirst());
                game.getPlayerOne().getDeck().removeFirst();
            }
            if (!game.getPlayerTwo().getDeck().isEmpty()) {
                game.getPlayerTwo().getHand().add(game.getPlayerTwo().getDeck().getFirst());
                game.getPlayerTwo().getDeck().removeFirst();
            }
        }
    }
}
