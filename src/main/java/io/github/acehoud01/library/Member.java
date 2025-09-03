package io.github.acehoud01.library;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final String memberId;
    private final String name;
    private String email;
    private final List<String> borrowedItems;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    public void borrowItem(String itemId) {
        borrowedItems.add(itemId);
    }

    public void returnItem(String itemId) {
        borrowedItems.remove(itemId);
    }

    public boolean canBorrow() {
        return borrowedItems.size() < 5; // Max 5 items per member
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getBorrowedItems() { return new ArrayList<>(borrowedItems); }

    @Override
    public String toString() {
        return String.format("%s - %s (%s) - %d items borrowed",
                memberId, name, email, borrowedItems.size());
    }
}
