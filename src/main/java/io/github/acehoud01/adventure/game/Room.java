package io.github.acehoud01.adventure.game;

import io.github.acehoud01.adventure.enums.Direction;
import io.github.acehoud01.adventure.items.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<Direction, Room> exits;
    private List<Item> items;
    private boolean visited;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.visited = false;
    }

    public void addExit(Direction direction, Room room) {
        exits.put(direction, room);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item takeItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getName().toLowerCase().equals(itemName.toLowerCase())) {
                return items.remove(i);
            }
        }
        return null;
    }

    public String getFullDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(name.toUpperCase()).append(" ===\n");
        sb.append(description).append("\n\n");

        if (!items.isEmpty()) {
            sb.append("Items here:\n");
            for (Item item : items) {
                sb.append("- ").append(item.getName()).append("\n");
            }
            sb.append("\n");
        }

        sb.append("Exits: ");
        if (exits.isEmpty()) {
            sb.append("None");
        } else {
            boolean first = true;
            for (Direction dir : exits.keySet()) {
                if (!first) sb.append(", ");
                sb.append(dir.toString());
                first = false;
            }
        }
        return sb.toString();
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Item> getItems() { return new ArrayList<>(items); }
    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }

    @Override
    public String toString() {
        return name;
    }
}
