package io.github.acehoud01.library;

import io.github.acehoud01.library.items.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private final List<LibraryItem> items;
    private final List<Member> members;
    private final Scanner scanner;


    public LibraryManager() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeSampleData();
    }



    private void initializeSampleData() {
        // Add sample books
        items.add(new Book("B001", "The Java Programming Language", "James Gosling", "978-0321349804", 928));
        items.add(new Book("B002", "Clean Code", "Robert C. Martin", "978-0132350884", 464));
        items.add(new Book("B003", "Design Patterns", "Gang of Four", "978-0201633612", 395));

        // Add sample magazines
        items.add(new Magazine("M001", "Java Magazine", "Oracle", "Jan 2024", 2024));
        items.add(new Magazine("M002", "Programming Today", "Tech Media", "Feb 2024", 2024));

        // Add sample DVDs
        items.add(new DVD("D001", "The Social Network", "David Fincher", 120, "Drama"));
        items.add(new DVD("D002", "Jobs", "Joshua Michael Stern", 128, "Biography"));

        // Add sample members
        members.add(new Member("MEM001", "John Doe", "john@email.com"));
        members.add(new Member("MEM002", "Jane Smith", "jane@email.com"));
    }

    public void addItem() {
        System.out.println("Select item type:");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. DVD");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        switch (choice) {
            case 1 -> addBook(id, title);
            case 2 -> addMagazine(id, title);
            case 3 -> addDVD(id, title);
            default -> {
                System.out.println("Invalid choice");
                return;
            }
        }
        System.out.println("Item added successfully!\n");
    }

    public void addBook(String id, String title) {
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter pages: ");
        int pages = scanner.nextInt();
        scanner.nextLine();

        items.add(new Book(id, title, author, isbn, pages));
    }

    private void addMagazine(String id, String title) {
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter issue number: ");
        String issue = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        items.add(new Magazine(id, title, publisher, issue, year));
    }

    private void addDVD(String id, String title) {
        System.out.print("Enter director: ");
        String director = scanner.nextLine();
        System.out.print("Enter duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        items.add(new DVD(id, title, director, duration, genre));
    }

    public void viewAllItems() {
        if (items.isEmpty()) {
            System.out.println("No items in library.\n");
            return;
        }
        System.out.println("\n=== LIBRARY ITEMS ===");
        for (LibraryItem item : items) {
            System.out.println(item);
        }
        System.out.println();
    }

    public void borrowItem() {
        System.out.print("Enter member ID: ");
        String memberID = scanner.nextLine();

        Member member = findMember(memberID);
        if (member == null) {
            System.out.println("Member not found.\n");
            return;
        }

        if (!member.canBorrow()) {
            System.out.println("Member has reached borrowing limit.\n");
            return;
        }

        System.out.print("Enter item ID");
        String itemId = scanner.nextLine();

        LibraryItem item = findItem(itemId);
        if (item == null) {
            System.out.println("Item not found.\n");
            return;
        }

        if (!item.isAvailable()) {
            System.out.println("Item is not available.\n");
            return;
        }

        try {
            item.borrowItem(member.getMemberId());
            member.borrowItem(itemId);
            System.out.printf("Item borrowed successfully! Due date: %s\n\n", item.getDueDate());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    public void returnItem() {
        System.out.print("Enter item ID: ");
        String itemId = scanner.nextLine();

        LibraryItem item = findItem(itemId);
        if (item == null) {
            System.out.println("Item not found.\n");
            return;
        }

        if (item.isAvailable()) {
            System.out.println("Item is not currently borrowed.\n");
            return;
        }

        // Find member who borrowed this item
        Member member = null;
        for (Member m : members) {
            if (m.getBorrowedItems().contains(itemId)) {
                member = m;
                break;
            }
        }

        item.returnItem();
        if (member != null) {
            member.returnItem(itemId);
        }

        System.out.println("Item returned successfully!\n");
    }

    public void searchItems() {
        System.out.print("Enter search term (title or author): ");
        String searchTerm = scanner.nextLine().toLowerCase();

        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(searchTerm) ||
                    item.getAuthor().toLowerCase().contains(searchTerm)) {
                results.add(item);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No items found.\n");
        } else {
            System.out.println("\n=== SEARCH RESULTS ===");
            for (LibraryItem item : results) {
                System.out.println(item);
            }
            System.out.println();
        }
    }

    private LibraryItem findItem(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    private Member findMember(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. View All Items");
            System.out.println("2. Add New Item");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");
            System.out.println("5. Search Items");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: viewAllItems(); break;
                case 2: addItem(); break;
                case 3: borrowItem(); break;
                case 4: returnItem(); break;
                case 5: searchItems(); break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

}
