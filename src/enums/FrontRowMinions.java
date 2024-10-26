package enums;

public enum FrontRowMinions {
    GOLIATH("Goliath"),
    WARDEN("Warden"),
    THE_RIPPER("The Ripper"),
    MIRAJ("Miraj");

    private final String name;

    FrontRowMinions(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
