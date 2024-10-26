package enums;

public enum Card {
    NAME("name"),
    COLORS("colors"),
    DESCRIPTION("description"),
    MANA("mana"),
    ATTACK_DAMAGE("attackDamage"),
    HEALTH("health");

    private final String field;

    Card(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
