package io.github.acehoud01.adventure.enums;

public enum ItemType {
    WEAPON("weapon"),
    POTION("potion"),
    KEY("key"),
    TREASURE("treasure");

    private final String name;

    ItemType(String name) { this.name = name; }

    @Override
    public String toString() { return name;}
}
