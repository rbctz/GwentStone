package enums;

public enum MinionType {
    SENTINEL("Sentinel"),
    BERSERKER("Berserker"),
    THE_CURSED_ONE("The Cursed One"),
    DISCIPLE("Disciple"),

    GOLIATH("Goliath"),
    WARDEN("Warden"),
    THE_RIPPER("The Ripper"),
    MIRAJ("Miraj");

    private final String name;

    MinionType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
