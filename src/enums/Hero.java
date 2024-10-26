package enums;

public enum Hero {
    LORD_ROYCE("Lord Royce"),
    EMPRESS_THORINA("Empress Thorina"),
    KING_MUDFACE("King Mudface"),
    GENERAL_KOCIORAW("General Kocioraw");

    private String name;

    Hero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
