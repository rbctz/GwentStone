package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.*;
import fileio.ActionsInput;
import game.Card;
import game.Game;
import game.GameThread;

public abstract class AttackCommandSuper {

    /**
     * This method is used to attack with a card.
     * @param actionsInput the current command
     * @param game the current game
     * @return the return value of the command called
     */
    public static ObjectNode cardUsesAttack(final ActionsInput actionsInput, final Game game) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = null;

        boolean errorFrozen = false;
        boolean errorAlreadyAttacked = false;
        boolean errorTargetNotTank = false;
        boolean errorTargetNotEnemy = false;
        boolean isThereAnyTankAlive = false;

        int xAttacker = actionsInput.getCardAttacker().getX();
        int yAttacker = actionsInput.getCardAttacker().getY();
        int xTarget = actionsInput.getCardAttacked().getX();
        int yTarget = actionsInput.getCardAttacked().getY();

        Card cardAttacker = game.getGameTable()[xAttacker][yAttacker];
        Card cardTarget = game.getGameTable()[xTarget][yTarget];

        if (game.getGameTable()[xAttacker][yAttacker] == null) {
            return returnValue;
        }

        if (game.getGameTable()[xTarget][yTarget] == null) {
            return returnValue;
        }

        // CHECK IF THE ATTACKER IS FROZEN
        if (game.getGameTable()[xAttacker][yAttacker].isFrozen()) {
            errorFrozen = true;
        }

        // CHECK IF THE CARD HAS ALREADY ATTACKED
        if (game.getGameTable()[xAttacker][yAttacker].isUsedAttack()) {
            errorAlreadyAttacked = true;
        }

        // CHECK IF THE TARGET IS AN ENEMY
        if ((xAttacker == Constants.PLAYER_ONE_FRONT.getValue()
        || xAttacker == Constants.PLAYER_ONE_BACK.getValue())
        && (xTarget == Constants.PLAYER_ONE_FRONT.getValue()
        || xTarget == Constants.PLAYER_ONE_BACK.getValue())) {
            errorTargetNotEnemy = true;
        }
        if ((xAttacker == Constants.PLAYER_TWO_FRONT.getValue()
        || xAttacker == Constants.PLAYER_TWO_BACK.getValue())
        && (xTarget == Constants.PLAYER_TWO_FRONT.getValue()
        || xTarget == Constants.PLAYER_TWO_BACK.getValue())) {
            errorTargetNotEnemy = true;
        }

        // CHECK IN WHICH ROW THE ENEMY TANKS WOULD BE
        int enemyFrontRow;
        if (xAttacker == Constants.PLAYER_ONE_FRONT.getValue()) {
            enemyFrontRow = Constants.PLAYER_TWO_FRONT.getValue();
        } else {
            enemyFrontRow = Constants.PLAYER_ONE_FRONT.getValue();
        }

        // CHECK IF THERE IS ANY ENEMY TANK ALIVE
        for (int i = 0; i < Constants.TABLE_COLS.getValue(); i++) {
            Card card = game.getGameTable()[enemyFrontRow][i];
            if (card != null) {
                if (card.getCard().getName().equals(FrontRowMinions.GOLIATH.getName())
                        || card.getCard().getName().equals(FrontRowMinions.WARDEN.getName())) {
                    isThereAnyTankAlive = true;
                    break;
                }
            }
        }

        // CHECK IF THE TARGET IS A TANK
        if (isThereAnyTankAlive) {
            if (!cardTarget.getCard().getName().
                    equals(FrontRowMinions.GOLIATH.getName())
                    && !cardTarget.getCard().getName().
                    equals(FrontRowMinions.WARDEN.getName())) {
                errorTargetNotTank = true;
            }
        }

        if (!errorFrozen && !errorAlreadyAttacked && !errorTargetNotEnemy && !errorTargetNotTank) {
            cardAttacker.setUsedAttack(true);
            cardTarget.getCard().setHealth(
                    cardTarget.getCard().getHealth()
                        - cardAttacker.getCard().getAttackDamage());
            if (cardTarget.getCard().getHealth() <= 0) {
                game.getGameTable()[xTarget][yTarget] = null;
            }
        } else {
            returnValue = objectMapper.createObjectNode();
            returnValue.put(OutputMessage.COMMAND.getMessage(), actionsInput.getCommand());
            ObjectNode posAttacker = objectMapper.createObjectNode();
            ObjectNode posTarget = objectMapper.createObjectNode();
            posAttacker.put(OutputMessage.X.getMessage(), xAttacker);
            posAttacker.put(OutputMessage.Y.getMessage(), yAttacker);
            posTarget.put(OutputMessage.X.getMessage(), xTarget);
            posTarget.put(OutputMessage.Y.getMessage(), yTarget);

            returnValue.put(OutputMessage.CARD_ATTACKER.getMessage(), posAttacker);
            returnValue.put(OutputMessage.CARD_TARGET.getMessage(), posTarget);

            if (errorFrozen) {
                returnValue.put(OutputMessage.ERROR.getMessage(), ErrorMessage.FROZEN.getMessage());
            } else if (errorAlreadyAttacked) {
                returnValue.put(OutputMessage.ERROR.getMessage(), ErrorMessage.ATTACKER_ALREADY_ATTACKED.getMessage());
            } else if (errorTargetNotEnemy) {
                returnValue.put(OutputMessage.ERROR.getMessage(), ErrorMessage.NOT_ENEMY.getMessage());
            } else if (errorTargetNotTank) {
                returnValue.put(OutputMessage.ERROR.getMessage(), ErrorMessage.NOT_TANK.getMessage());
            }
        }
        return returnValue;
    }

    /**
     * This method is used to attack the enemy hero.
     * @param actionsInput the current command
     * @param game the current game
     * @return the return value of the command called
     */
    public static ObjectNode useAttackHero(final ActionsInput actionsInput, final Game game) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnValue = null;

        boolean errorFrozen = false;
        boolean errorAlreadyAttacked = false;
        boolean isThereAnyTankAlive = false;
        int enemyFrontRow;
        Card enemyHero;

        int xAttacker = actionsInput.getCardAttacker().getX();
        int yAttacker = actionsInput.getCardAttacker().getY();

        if (game.getPlayerOne().isHisTurn()) {
            enemyHero = game.getPlayerTwo().getHero();
            enemyFrontRow = Constants.PLAYER_TWO_FRONT.getValue();
        } else {
            enemyHero = game.getPlayerOne().getHero();
            enemyFrontRow = Constants.PLAYER_ONE_FRONT.getValue();
        }

        // CHECK IF THE ATTACKER IS FROZEN
        if (game.getGameTable()[xAttacker][yAttacker].isFrozen()) {
            errorFrozen = true;
        }

        // CHECK IF THE CARD HAS ALREADY ATTACKED
        if (game.getGameTable()[xAttacker][yAttacker].isUsedAttack()) {
            errorAlreadyAttacked = true;
        }

        // CHECK IF THERE IS ANY ENEMY TANK ALIVE
        for (int i = 0; i < Constants.TABLE_COLS.getValue(); i++) {
            Card card = game.getGameTable()[enemyFrontRow][i];
            if (card != null) {
                if (card.getCard().getName().equals(FrontRowMinions.GOLIATH.getName())
                        || card.getCard().getName().equals(FrontRowMinions.WARDEN.getName())) {
                    isThereAnyTankAlive = true;
                    break;
                }
            }
        }

        if (!errorFrozen && !errorAlreadyAttacked && !isThereAnyTankAlive) {
            game.getGameTable()[xAttacker][yAttacker].setUsedAttack(true);
            enemyHero.getCard().setHealth(
                    enemyHero.getCard().getHealth()
                        - game.getGameTable()[xAttacker][yAttacker].getCard().getAttackDamage());
            if (enemyHero.getCard().getHealth() <= 0) {
                returnValue = objectMapper.createObjectNode();
                if (game.getPlayerOne().isHisTurn()) {
                    GameThread.setPlayerOneWins(GameThread.getPlayerOneWins() + 1);
                    returnValue.put(OutputMessage.GAME_ENDED.getMessage(), OutputMessage.PLAYER_ONE_WINS.getMessage());
                } else {
                    GameThread.setPlayerTwoWins(GameThread.getPlayerTwoWins() + 1);
                    returnValue.put(OutputMessage.GAME_ENDED.getMessage(), OutputMessage.PLAYER_TWO_WINS.getMessage());
                }
            }
        } else {
            returnValue = objectMapper.createObjectNode();
            returnValue.put(OutputMessage.COMMAND.getMessage(), actionsInput.getCommand());
            ObjectNode posAttacker = objectMapper.createObjectNode();
            posAttacker.put(OutputMessage.X.getMessage(), xAttacker);
            posAttacker.put(OutputMessage.Y.getMessage(), yAttacker);
            returnValue.put(OutputMessage.CARD_ATTACKER.getMessage(), posAttacker);

            if (errorFrozen) {
                returnValue.put(OutputMessage.ERROR.getMessage(), ErrorMessage.FROZEN.getMessage());
            } else if (errorAlreadyAttacked) {
                returnValue.put(OutputMessage.ERROR.getMessage(), ErrorMessage.ATTACKER_ALREADY_ATTACKED.getMessage());
            } else if (isThereAnyTankAlive) {
                returnValue.put(OutputMessage.ERROR.getMessage(), ErrorMessage.NOT_TANK.getMessage());
            }
        }

        return returnValue;
    }
}
