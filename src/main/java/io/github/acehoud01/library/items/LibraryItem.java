package io.github.acehoud01.library.items;

import io.github.acehoud01.library.interfaces.Borrowable;
import java.time.LocalDate;

public abstract class LibraryItem implements Borrowable {
    protected String id;
    protected String title;
    protected String author;
    protected boolean available;
    protected String borrowerName;
    protected LocalDate borrowDate;
    protected LocalDate dueDate;

    public LibraryItem(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    @Override
    public void borrowItem(String memberName) {
        if (!available) {
            throw new IllegalStateException("Item not available");
        }
        this.available = false;
        this.borrowerName = memberName;
        this.borrowDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(getBorrowPeriodDays());
    }

    @Override
    public void returnItem() {
        this.available = true;
        this.borrowerName = null;
        this.borrowDate = null;
        this.dueDate = null;
    }

    @Override
    public boolean isAvailable() {
        return available;

    }

    @Override
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String getBorrowerName() {
        return borrowerName;
    }

    public abstract int getBorrowPeriodDays();
    protected abstract String getItemType();

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s - %s",
                id, title, author, available ? "Available" : "Borrowed");
    }
}
