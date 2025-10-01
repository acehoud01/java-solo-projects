package io.github.acehoud01.adventure.enums;

public enum Action {
    MOVE("move", "go"),
    TAKE("take", "get"),
    USE("use"),
    LOOK("look", "exemine"),
    INVENTORY("inventory", "inv"),
    HELP("help"),
    QUIT("quit", "exit"),
    SAVE("save"),
    LOAD("load");

    private final String primary;
    private final String secondary;

    Action(String primary) {
        this.primary = primary;
        this.secondary = null;
    }

    Action(String primary, String secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public static Action fromString(String input) {
        String lower = input.toLowerCase().trim();
        for (Action action : Action.values()) {
            if (action.primary.equals(lower) ||
            (action.secondary != null && action.secondary.equals(lower))) {
                return action;
            }
        }
        return null;
    }

    public String getPrimary() {
        return primary;
    }
    public String getSecondary() {
        return secondary;
    }

    @Override
    public String toString() { return primary; }
}
