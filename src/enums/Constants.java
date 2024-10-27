package enums;

public enum Constants {
    PLAYER_ONE(1),
    PLAYER_TWO(2),
    HERO_HEALTH(30),
    TABLE_ROWS(4),
    TABLE_COLS(5),
    PLAYER_ONE_BACK(3),
    PLAYER_ONE_FRONT(2),
    PLAYER_TWO_FRONT(1),
    PLAYER_TWO_BACK(0),
    MAX_MANA(10);

    private final int value;

    Constants(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
