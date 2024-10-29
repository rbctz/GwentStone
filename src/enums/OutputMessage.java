package enums;

public enum OutputMessage {
    COMMAND("command"),
    PLAYER_INDEX("playerIdx"),
    HAND_INDEX("handIdx"),
    CARD_ATTACKER("cardAttacker"),
    CARD_TARGET("cardAttacked"),
    AFFECTED_ROW("affectedRow"),
    X("x"),
    Y("y"),
    OUTPUT("output"),
    ERROR("error"),
    GAME_ENDED("gameEnded"),
    PLAYER_ONE_WINS("playerOneWins"),
    PLAYER_TWO_WINS("playerTwoWins");

    private final String message;

    OutputMessage(final String category) {
        this.message = category;
    }

    public String getMessage() {
        return message;
    }
}
