package io.github.acehoud01.adventure.game;

import io.github.acehoud01.adventure.enums.ItemType;
import io.github.acehoud01.adventure.items.Item;

import java.io.*;

public class GameSaveManager {
    private static final String SAVE_FILE = "game_save.txt";

    public void saveGame(Player player) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println("PLAYER_DATA");
            writer.println(player.getName());
            writer.println(player.getHealth());
            writer.println(player.getScore());
            writer.println(player.getCurrentRoom().getName());

            writer.println("INVENTORY");
            for (Item item : player.getInventory()) {
                writer.printf("%s,%s,%s,%d,%b\n",
                        item.getName(),
                        item.getDescription(),
                        item.getType(),
                        item.getValue(),
                        item.isUsable());
            }

            System.out.println("Game saved successfully!\n");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public boolean loadGame(Player player, Game game) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line = reader.readLine();
            if (!"PLAYER_DATA".equals(line)) {
                return false;
            }

            String playerName = reader.readLine();
            int health = Integer.parseInt(reader.readLine());
            int score = Integer.parseInt(reader.readLine());
            String roomName = reader.readLine();

            // Find the room by name
            Room savedRoom = game.findRoomByName(roomName);
            if (savedRoom != null) {
                player.setCurrentRoom(savedRoom);
            }

            // Load inventory
            line = reader.readLine();
            if ("INVENTORY".equals(line)) {
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", 5);
                    if (parts.length == 5) {
                        String name = parts[0];
                        String desc = parts[1];
                        ItemType type = ItemType.valueOf(parts[2].toUpperCase());
                        int value = Integer.parseInt(parts[3]);
                        boolean usable = Boolean.parseBoolean(parts[4]);

                        Item item = new Item(name, desc, type, value, usable);
                        player.addItem(item);
                    }
                }
            }

            System.out.println("Game loaded successfully!\n");
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("No save file found.\n");
            return false;
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
            return false;
        }
    }
}