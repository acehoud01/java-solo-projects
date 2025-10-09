package io.github.acehoud01.adventure.game;

import io.github.acehoud01.adventure.enums.*;
import io.github.acehoud01.adventure.items.Item;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Player player;
    private Map<String, Room> rooms;
    private Scanner scanner;
    private GameSaveManager saveManager;
    private boolean gameRunning;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.saveManager = new GameSaveManager();
        this.rooms = new HashMap<>();
        this.gameRunning = true;

        initializeGame();
    }

    private void initializeGame() {
        System.out.print("Enter your character name: ");
        String playerName = scanner.nextLine();
        this.player = new Player(playerName);

        createRooms();
        player.setCurrentRoom(rooms.get("entrance"));
    }

    private void createRooms() {
        // Create rooms
        Room entrance = new Room("Entrance Hall",
                "A grand entrance hall with marble floors and high ceilings. Dust particles dance in the sunlight.");
        Room library = new Room("Ancient Library",
                "Towering bookshelves filled with ancient tomes. A musty smell fills the air.");
        Room treasure = new Room("Treasure Chamber",
                "A hidden chamber glowing with golden light. Precious gems sparkle on the walls.");
        Room garden = new Room("Enchanted Garden",
                "A magical garden with glowing flowers and a crystal-clear fountain in the center.");
        Room dungeon = new Room("Dark Dungeon",
                "A damp, dark dungeon with stone walls. You hear strange echoes in the distance.");

        // Set up exits
        entrance.addExit(Direction.NORTH, library);
        entrance.addExit(Direction.EAST, garden);
        entrance.addExit(Direction.WEST, dungeon);

        library.addExit(Direction.SOUTH, entrance);
        library.addExit(Direction.EAST, treasure);

        treasure.addExit(Direction.WEST, library);

        garden.addExit(Direction.WEST, entrance);

        dungeon.addExit(Direction.EAST, entrance);

        // Add items
        library.addItem(new Item("Ancient Key", "An ornate golden key", ItemType.KEY, 0, true));
        library.addItem(new Item("Magic Sword", "A gleaming sword with mystical runes", ItemType.WEAPON, 50, true));

        garden.addItem(new Item("Health Potion", "A glowing red potion that restores health", ItemType.POTION, 30, true));
        garden.addItem(new Item("Ruby", "A precious red ruby", ItemType.TREASURE, 100, false));

        treasure.addItem(new Item("Golden Crown", "A magnificent golden crown", ItemType.TREASURE, 500, false));
        treasure.addItem(new Item("Diamond", "A sparkling diamond", ItemType.TREASURE, 200, false));

        dungeon.addItem(new Item("Health Potion", "A glowing red potion", ItemType.POTION, 25, true));

        // Store rooms in map
        rooms.put("entrance", entrance);
        rooms.put("library", library);
        rooms.put("treasure", treasure);
        rooms.put("garden", garden);
        rooms.put("dungeon", dungeon);
    }

    public void start() {
        System.out.println("Welcome to the Text Adventure Game!");
        System.out.println("Type 'help' for commands.\n");

        showCurrentRoom();

        while (gameRunning && player.isAlive()) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                processCommand(input);
            }
        }

        if (!player.isAlive()) {
            System.out.println("Game Over! You have died.");
        }

        System.out.println("Thanks for playing!");
    }

    private void processCommand(String input) {
        String[] parts = input.toLowerCase().split("\\s+");
        String command = parts[0];

        Action action = Action.fromString(command);

        if (action != null) {
            switch (action) {
                case MOVE:
                    handleMove(parts);
                    break;
                case TAKE:
                    handleTake(parts);
                    break;
                case USE:
                    handleUse(parts);
                    break;
                case LOOK:
                    showCurrentRoom();
                    break;
                case INVENTORY:
                    player.displayInventory();
                    break;
                case HELP:
                    showHelp();
                    break;
                case SAVE:
                    saveManager.saveGame(player);
                    break;
                case LOAD:
                    saveManager.loadGame(player, this);
                    showCurrentRoom();
                    break;
                case QUIT:
                    gameRunning = false;
                    break;
                default:
                    System.out.println("I don't understand that command.");
            }
        } else {
            System.out.println("I don't understand that command. Type 'help' for available commands.");
        }
    }

    private void handleMove(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Move where? (north, south, east, west)");
            return;
        }

        Direction direction = Direction.fromString(parts[1]);
        if (direction == null) {
            System.out.println("Invalid direction. Use: north, south, east, west");
            return;
        }

        Room nextRoom = player.getCurrentRoom().move(direction);
        if (nextRoom == null) {
            System.out.println("You can't go that way.");
        } else {
            player.setCurrentRoom(nextRoom);
            if (!nextRoom.isVisited()) {
                nextRoom.setVisited(true);
                player.addScore(10); // Exploration bonus
            }
            showCurrentRoom();
        }
    }

    private void handleTake(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Take what?");
            return;
        }

        String itemName = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));
        Item item = player.getCurrentRoom().takeItem(itemName);

        if (item == null) {
            System.out.println("There's no " + itemName + " here.");
        } else {
            player.addItem(item);
            System.out.println("You took the " + item.getName() + ".");
        }
    }

    private void handleUse(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Use what?");
            return;
        }

        String itemName = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));
        Item item = player.useItem(itemName);

        if (item == null) {
            System.out.println("You don't have that item or it can't be used.");
        } else {
            switch (item.getType()) {
                case POTION:
                    System.out.printf("You used the %s and restored %d health!\n",
                            item.getName(), item.getValue());
                    break;
                case KEY:
                    System.out.printf("You used the %s. Something clicks...\n", item.getName());
                    break;
                default:
                    System.out.printf("You used the %s.\n", item.getName());
            }
        }
    }

    private void showCurrentRoom() {
        Room current = player.getCurrentRoom();
        System.out.println("\n" + current.getFullDescription());
        System.out.println();
        player.displayStatus();
        System.out.println();
    }

    private void showHelp() {
        System.out.println("\n=== AVAILABLE COMMANDS ===");
        System.out.println("move <direction> - Move in a direction (north, south, east, west)");
        System.out.println("take <item> - Take an item from the room");
        System.out.println("use <item> - Use an item from your inventory");
        System.out.println("look - Look around the current room");
        System.out.println("inventory - Show your inventory");
        System.out.println("save - Save your game");
        System.out.println("load - Load your saved game");
        System.out.println("help - Show this help message");
        System.out.println("quit - Exit the game\n");
    }

    public Room findRoomByName(String name) {
        return rooms.get(name.toLowerCase());
    }
}
