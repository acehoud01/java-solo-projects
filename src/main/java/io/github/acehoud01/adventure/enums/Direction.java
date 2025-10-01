package io.github.acehoud01.adventure.enums;

public enum Direction {
    NORTH("north", "n"),
    SOUTH("south", "s"),
    EAST("east", "e"),
    WEST("west", "w");

    private final String fullName;
    private final String shortName;

    Direction(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }
    public String getShortName() {
        return shortName;
    }

    public static Direction fromString(String input) {
        String lower = input.toLowerCase().trim();
        for (Direction dir : Direction.values()) {
            if (dir.fullName.equals(lower) || dir.shortName.equals(lower)) {
                return dir;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
