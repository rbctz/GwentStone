package game;

import cards.Card;
import cards.HeroCard;
import cards.MinionCard;
import enums.Command;
import enums.ErrorMessage;
import enums.HeroType;
import enums.MinionType;
import fileio.ActionsInput;
import fileio.Coordinates;

import java.util.ArrayList;

public final class Actions {

    private final Parser parser;
    private final Game game;

    public Actions(final Parser parser, final Game game) {
        this.parser = parser;
        this.game = game;
    }

    /**
     * Method that parses the actions from the input file
     * @param actionsInput the input from the file
     */
    public void parseActions(final ActionsInput actionsInput) {
        for (Command command : Command.values()) {
            if (command.getCommand().equals(actionsInput.getCommand())) {
                switch (command) {
                    case END_PLAYER_TURN:
                        endPlayerTurn();
                        break;
                    case PLACE_CARD:
                        placeCard(actionsInput.getHandIdx());
                        break;
                    case CARD_USES_ATTACK:
                        cardUsesAttack(actionsInput.getCardAttacker(),
                                actionsInput.getCardAttacked());
                        break;
                    case CARD_USES_ABILITY:
                        cardUsesAbility(actionsInput.getCardAttacker(),
                                actionsInput.getCardAttacked());
                        break;
                    case USE_ATTACK_HERO:
                        useAttackHero(actionsInput.getCardAttacker());
                        break;
                    case USE_HERO_ABILITY:
                        useAbilityHero(actionsInput.getAffectedRow());
                        break;
                    case GET_PLAYER_DECK:
                        getPlayerDeck(actionsInput.getPlayerIdx());
                        break;
                    case GET_PLAYER_HERO:
                        getPlayerHero(actionsInput.getPlayerIdx());
                        break;
                    case GET_PLAYER_MANA:
                        getPlayerMana(actionsInput.getPlayerIdx());
                        break;
                    case GET_PLAYER_TURN:
                        getPlayerTurn();
                        break;
                    case GET_CARDS_IN_HAND:
                        getCardsInHand(actionsInput.getPlayerIdx());
                        break;
                    case GET_CARDS_ON_TABLE:
                        getCardsOnTable();
                        break;
                    case GET_CARD_AT_POSITION:
                        getCardAtPosition(actionsInput.getX(), actionsInput.getY());
                        break;
                    case GET_FROZEN_CARDS_ON_TABLE:
                        getFrozenCardsOnTable();
                        break;
                    case GET_TOTAL_GAMES_PLAYED:
                        getTotalGamesPlayed();
                        break;
                    case GET_PLAYER_ONE_WINS:
                        getPlayerOneWins();
                        break;
                    case GET_PLAYER_TWO_WINS:
                        getPlayerTwoWins();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void cardUsesAttack(final Coordinates attacker, final Coordinates target) {

        boolean cardAttacked = false;
        boolean cardFrozen = false;
        boolean notEnemy = false;
        boolean enemyNotATank = false;

        if (game.getGameBoard().getCardFromTable(attacker).getAttacked()) {
            cardAttacked = true;
        } else if (game.getGameBoard().getCardFromTable(attacker).isFrozen()) {
            cardFrozen = true;
        } else if (!game.getGameBoard().isEnemy(game.getCurrentPlayerTurn(), target)) {
            notEnemy = true;
        } else if (game.enemyHasTanks()
                && !game.getGameBoard().getCardFromTable(target).getIsTank()) {
            enemyNotATank = true;
        }

        if (cardAttacked) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ATTACK.getCommand(), attacker, target,
                    ErrorMessage.ATTACKER_ALREADY_ATTACKED.getMessage()));
        } else if (cardFrozen) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ATTACK.getCommand(), attacker, target,
                    ErrorMessage.FROZEN.getMessage()));
        } else if (notEnemy) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ATTACK.getCommand(), attacker, target,
                    ErrorMessage.NOT_ENEMY.getMessage()));
        } else if (enemyNotATank) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ATTACK.getCommand(), attacker, target,
                    ErrorMessage.NOT_TANK.getMessage()));
        } else {
            MinionCard attackerCard = game.getGameBoard().getCardFromTable(attacker);
            attackerCard.setAttacked(true);
            game.cardAttacksMinion(attacker, target);
        }
    }

    private void cardUsesAbility(final Coordinates attacker, final Coordinates target) {

        boolean cardAttacked = false;
        boolean cardFrozen = false;


        if (game.getGameBoard().getCardFromTable(attacker).getAttacked()) {
            cardAttacked = true;
        } else if (game.getGameBoard().getCardFromTable(attacker).isFrozen()) {
            cardFrozen = true;
        }

        boolean notCurrentPlayer = false;
        boolean notEnemy = false;
        boolean enemyNotATank = false;
        final MinionType minionType = game.getGameBoard().
                getCardFromTable(attacker).getMinionType();
        if (minionType == MinionType.DISCIPLE) {
            if (game.getGameBoard().isEnemy(game.getCurrentPlayerTurn(), target)) {
                notCurrentPlayer = true;
            }
        } else if (minionType == MinionType.MIRAJ
                || minionType == MinionType.THE_CURSED_ONE
                || minionType == MinionType.THE_RIPPER) {
            if (!game.getGameBoard().isEnemy(game.getCurrentPlayerTurn(), target)) {
                notEnemy = true;
            } else if (game.enemyHasTanks()
                    && !game.getGameBoard().getCardFromTable(target).getIsTank()) {
                enemyNotATank = true;
            }
        }
        if (cardAttacked) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ABILITY.getCommand(), attacker, target,
                    ErrorMessage.ATTACKER_ALREADY_ATTACKED.getMessage()));
        } else if (cardFrozen) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ABILITY.getCommand(), attacker, target,
                    ErrorMessage.FROZEN.getMessage()));
        } else if (notCurrentPlayer) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ABILITY.getCommand(), attacker, target,
                    ErrorMessage.NOT_CURRENT_PLAYER.getMessage()));
        } else if (notEnemy) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ABILITY.getCommand(), attacker, target,
                    ErrorMessage.NOT_ENEMY.getMessage()));
        } else if (enemyNotATank) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.CARD_USES_ABILITY.getCommand(), attacker, target,
                    ErrorMessage.NOT_TANK.getMessage()));
        } else {
            MinionCard attackerCard = game.getGameBoard().getCardFromTable(attacker);
            attackerCard.setAttacked(true);
            attackerCard.useAbility(game, target);
        }
    }

    private void useAttackHero(final Coordinates coordinates) {
        boolean cardAttacked = false;
        boolean cardFrozen = false;
        boolean enemyNotTank = false;
        Player otherPlayer;
        if (game.getCurrentPlayerTurn() == game.getPlayerOne()) {
            otherPlayer = game.getPlayerTwo();
        } else {
            otherPlayer = game.getPlayerOne();
        }

        if (game.getGameBoard().getCardFromTable(coordinates).isFrozen()) {
            cardFrozen = true;
        } else if (game.getGameBoard().getCardFromTable(coordinates).getAttacked()) {
            cardAttacked = true;
        } else if (otherPlayer.hasTanksOnBoard()) {
            enemyNotTank = true;
        }

        if (cardFrozen) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.USE_ATTACK_HERO.getCommand(), coordinates,
                    ErrorMessage.FROZEN.getMessage()));
        } else if (cardAttacked) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.USE_ATTACK_HERO.getCommand(), coordinates,
                    ErrorMessage.ATTACKER_ALREADY_ATTACKED.getMessage()));
        } else if (enemyNotTank) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.USE_ATTACK_HERO.getCommand(), coordinates,
                    ErrorMessage.NOT_TANK.getMessage()));
        } else {
            int winner;
            final Player currentPlayer = game.getCurrentPlayerTurn();

            MinionCard attackerCard = game.getGameBoard().getCardFromTable(coordinates);
            attackerCard.setAttacked(true);
            if (attackerCard.getAttackDamage() >= otherPlayer.getHero().getHealth()) {
                if (currentPlayer == game.getPlayerOne()) {
                    winner = 1;
                    parser.incrementPlayerOneWins();
                } else {
                    winner = 2;
                    parser.incrementPlayerTwoWins();
                }

                if (winner == 1) {
                    Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                            "Player one killed the enemy hero."));
                } else {
                    Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                            "Player two killed the enemy hero."));
                }
            } else {
                otherPlayer.getHero().takeDamage(attackerCard.getAttackDamage());
            }
        }
    }

    private void useAbilityHero(final int rowIndex) {
        HeroType heroType = game.getCurrentPlayerTurn().getHero().getHeroType();
        boolean notEnoughMana = false;
        boolean heroAttacked = false;
        boolean rowsNotEnemy = false;
        boolean rowsNotAlly = false;
        Player otherPlayer;
        if (game.getCurrentPlayerTurn() == game.getPlayerOne()) {
            otherPlayer = game.getPlayerTwo();
        } else {
            otherPlayer = game.getPlayerOne();
        }

        if (game.getCurrentPlayerTurn().getMana()
                < game.getCurrentPlayerTurn().getHero().getMana()) {
            notEnoughMana = true;
        } else if (game.getCurrentPlayerTurn().getHero().getAttacked()) {
            heroAttacked = true;
        } else {
            if (heroType == HeroType.KING_MUDFACE || heroType == HeroType.GENERAL_KOCIORAW) {
                if (game.getCurrentPlayerTurn().getFrontRow() != rowIndex
                        && game.getCurrentPlayerTurn().getBackRow() != rowIndex) {
                    rowsNotAlly = true;
                }
            } else if (heroType == HeroType.EMPRESS_THORINA || heroType == HeroType.LORD_ROYCE) {
                if (game.getCurrentPlayerTurn().getFrontRow() == rowIndex
                        || game.getCurrentPlayerTurn().getBackRow() == rowIndex) {
                    rowsNotEnemy = true;
                }
            }
        }
        if (notEnoughMana) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.USE_HERO_ABILITY.getCommand(),
                    ErrorMessage.NOT_ENOUGH_MANA_HERO.getMessage(), rowIndex));
        } else if (heroAttacked) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.USE_HERO_ABILITY.getCommand(),
                    ErrorMessage.HERO_ALREADY_ATTACKED.getMessage(), rowIndex));
        } else if (rowsNotEnemy) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.USE_HERO_ABILITY.getCommand(),
                    ErrorMessage.NOT_ENEMY_ROW_HERO.getMessage(), rowIndex));
        } else if (rowsNotAlly) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.USE_HERO_ABILITY.getCommand(),
                    ErrorMessage.NOT_CURRENT_PLAYER_ROW.getMessage(), rowIndex));
        } else {
            checkIfThorinaKilledTank(heroType, rowIndex, otherPlayer);
            game.getCurrentPlayerTurn().getHero().setAttacked(true);
            game.getCurrentPlayerTurn().getHero().useAbility(game.getGameBoard(), rowIndex);
            game.getCurrentPlayerTurn().reduceMana(
                    game.getCurrentPlayerTurn().getHero().getMana());
        }

    }

    private void checkIfThorinaKilledTank(final HeroType heroType,
                                          final int rowIndex,
                                          final Player otherPlayer) {
        if (heroType == HeroType.EMPRESS_THORINA) {
            Coordinates coordinates = new Coordinates();
            coordinates.setX(rowIndex);
            int maxHealth = 0;
            int col;
            for (col = 0; col < game.getGameBoard().getBoard().get(rowIndex).size(); col++) {
                int minionHP = game.getGameBoard().getBoard().get(rowIndex).get(col).getHealth();
                if (minionHP > maxHealth) {
                    maxHealth = minionHP;
                    coordinates.setY(col);
                }
            }
            if (game.getGameBoard().getCardFromTable(coordinates).getIsTank()) {
                otherPlayer.removeTankFromBoard();
            }
        }
    }

    private void endPlayerTurn() {
        game.endTurn();
        if (game.getTurnsPlayed() % 2 == 0) {
            game.endRound();
        }
    }

    private void getPlayerTurn() {
        final int playerTurnIndex;
        if (game.getCurrentPlayerTurn() == game.getPlayerOne()) {
            playerTurnIndex = 1;
        } else {
            playerTurnIndex = 2;
        }
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                Command.GET_PLAYER_TURN.getCommand(), playerTurnIndex));
    }

    private void getPlayerDeck(final int playerIdx) {
        ArrayList<Card> deck;
        if (playerIdx == 1) {
            deck = game.getPlayerOne().getDeck();
        } else {
            deck = game.getPlayerTwo().getDeck();
        }
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(Command.
                GET_PLAYER_DECK.getCommand(), playerIdx, deck));
    }

    private void getPlayerHero(final int playerIdx) {
        HeroCard hero;
        if (playerIdx == 1) {
            hero = game.getPlayerOne().getHero();
        } else {
            hero = game.getPlayerTwo().getHero();
        }
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(Command.
                GET_PLAYER_HERO.getCommand(), hero, playerIdx));

    }

    private void placeCard(final int handIndex) {
        Card cardToBePlaced = game.getCurrentPlayerTurn().getHand().get(handIndex);
        boolean notEnoughMana = false;
        boolean rowFull = false;

        if (game.getCurrentPlayerTurn().getMana() < cardToBePlaced.getMana()) {
            notEnoughMana = true;
        } else {
            int row;
            MinionCard minionCardToBePlaced = (MinionCard) cardToBePlaced;
            if (minionCardToBePlaced.getRow() == 0) {
                row = game.getCurrentPlayerTurn().getBackRow();
            } else {
                row = game.getCurrentPlayerTurn().getFrontRow();
            }

            if (game.getGameBoard().isRowFull(row)) {
                rowFull = true;
            }
        }

        if (notEnoughMana) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.PLACE_CARD.getCommand(), handIndex,
                    ErrorMessage.NOT_ENOUGH_MANA_MINION.getMessage()));
        } else if (rowFull) {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.PLACE_CARD.getCommand(), handIndex,
                    ErrorMessage.NOT_ENOUGH_SPACE.getMessage()));
        } else {
            MinionCard minionCardToBePlaced =
                    (MinionCard) game.getCurrentPlayerTurn().getHand().get(handIndex);
            game.placeCard(minionCardToBePlaced);
            game.getCurrentPlayerTurn().getHand().remove(handIndex);
        }
    }

    private void getCardsOnTable() {
        ArrayList<ArrayList<MinionCard>> cardsOnTable = game.getGameBoard().getAllCardsOnTable();
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                Command.GET_CARDS_ON_TABLE.getCommand(), cardsOnTable));
    }

    private void getFrozenCardsOnTable() {
        ArrayList<MinionCard> frozenCardsOnTable = game.getGameBoard().getAllFrozenCardsOnTable();
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                frozenCardsOnTable, Command.GET_FROZEN_CARDS_ON_TABLE.getCommand()));
    }

    private void getPlayerMana(final int playerIndex) {
        int mana;
        if (playerIndex == 1) {
            mana = game.getPlayerOne().getMana();
        } else {
            mana = game.getPlayerTwo().getMana();
        }
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                Command.GET_PLAYER_MANA.getCommand(), playerIndex, mana));
    }

    private void getCardsInHand(final int playerIndex) {
        ArrayList<Card> hand;
        if (playerIndex == 1) {
            hand = game.getPlayerOne().getHand();
        } else {
            hand = game.getPlayerTwo().getHand();
        }
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                Command.GET_CARDS_IN_HAND.getCommand(), playerIndex, hand));
    }

    private void getCardAtPosition(final int x, final int y) {
        if (game.getGameBoard().getBoard().get(x).size() >= y) {
            Coordinates coordinates = new Coordinates();
            coordinates.setX(x);
            coordinates.setY(y);
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.GET_CARD_AT_POSITION.getCommand(), x, y,
                            game.getGameBoard().getCardFromTable(coordinates)));
        } else {
            Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                    Command.GET_CARD_AT_POSITION.getCommand(), x, y,
                            ErrorMessage.NO_CARD.getMessage()));
        }
    }

    private void getPlayerOneWins() {
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                Command.GET_PLAYER_ONE_WINS.getCommand(), parser.getPlayerOneWins()));
    }

    private void getPlayerTwoWins() {
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                Command.GET_PLAYER_TWO_WINS.getCommand(), parser.getPlayerTwoWins()));
    }

    private void getTotalGamesPlayed() {
        Parser.getArrayNodeOutput().addPOJO(new OutputConstructor(
                Command.GET_TOTAL_GAMES_PLAYED.getCommand(), parser.getGamesPlayed()));
    }
}



