package enums;

public enum FrontRowMinions {
    GOLIATH("Goliath"),
    WARDEN("Warden"),
    THE_RIPPER("The Ripper"),
    MIRAJ("Miraj");

    private String name;

    FrontRowMinions(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
