package io.github.acehoud01.adventure.items;

import io.github.acehoud01.adventure.enums.ItemType;

public class Item {
    private String name;
    private String description;
    private ItemType type;
    private int value;
    private boolean usable;

    public Item(String name, String description, ItemType type, int value, boolean usable) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.usable = usable;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public ItemType getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public boolean isUsable() {
        return usable;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", name, type, description);
    }
}
