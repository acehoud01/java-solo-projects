package io.github.acehoud01.adventure.game;

import io.github.acehoud01.adventure.items.Item;
import io.github.acehoud01.adventure.enums.ItemType;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private List<Item> inventory;
    private Room currentRoom;
    private int score;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.maxHealth = 100;
        this.inventory = new ArrayList<>();
        this.score = 0;
    }

    public void addItem(Item item) {
        inventory.add(item);
        if (item.getType() == ItemType.TREASURE) {
            score += item.getValue();
        }
    }

    public Item useItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getName().toLowerCase().equals(itemName.toLowerCase()) && item.isUsable()) {
                if (item.getType() == ItemType.POTION) {
                    heal(item.getValue());
                    return inventory.remove(i);
                }
                return item;
            }
        }
        return null;
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void displayInventory() {
        System.out.println("\n=== INVENTORY ===");
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            for (Item item : inventory) {
                System.out.println("- " + item);
            }
        }
        System.out.printf("Health: %d/%d | Score: %d\n\n", health, maxHealth, score);
    }

    public void displayStatus() {
        System.out.printf("Player: %s | Health: %d/%d | Score: %d | Items: %d\n",
                name, health, maxHealth, score, inventory.size());
    }


    // Getters and Setters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public List<Item> getInventory() { return new ArrayList<>(inventory); }
    public Room getCurrentRoom() { return currentRoom; }
    public void setCurrentRoom(Room room) { this.currentRoom = room; }
    public int getScore() { return score; }
    public void addScore(int points) { this.score += points; }
}
