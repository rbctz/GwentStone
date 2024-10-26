package enums;

public enum Constants {
    PLAYER_ONE(1),
    PLAYER_TWO(2),
    HERO_HEALTH(30),
    TABLE_ROWS(4),
    TABLE_COLS(5);

    private final int value;

    Constants(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
