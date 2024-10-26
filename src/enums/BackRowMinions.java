package enums;

public enum BackRowMinions {
    SENTINEL("Sentinel"),
    BERSERKER("Berserker"),
    THE_CURSED_ONE("The Cursed One"),
    DISCIPLE("Disciple");

    private String name;

    BackRowMinions(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
