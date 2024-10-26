package enums;

public enum Command {
    GET_PLAYER_DECK("getPlayerDeck"),
    GET_PLAYER_HERO("getPlayerHero"),
    GET_PLAYER_MANA("getPlayerMana"),
    GET_PLAYER_TURN("getPlayerTurn"),
    END_PLAYER_TURN("endPlayerTurn"),
    PLACE_CARD("placeCard"),
    CARD_USES_ATTACK("cardUsesAttack"),
    CARD_USES_ABILITY("cardUsesAbility"),
    USE_ATTACK_HERO("useAttackHero"),
    USE_HERO_ABILITY("useHeroAbility"),
    GET_CARDS_IN_HAND("getCardsInHand"),
    GET_CARDS_ON_TABLE("getCardsOnTable"),
    GET_CARD_AT_POSITION("getCardAtPosition"),
    GET_FROZEN_CARDS_ON_TABLE("getFrozenCardsOnTable"),
    GET_TOTAL_GAMES_PLAYED("getTotalGamesPlayed"),
    GET_PLAYER_ONE_WINS("getPlayerOneWins"),
    GET_PLAYER_TWO_WINS("getPlayerTwoWins");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
