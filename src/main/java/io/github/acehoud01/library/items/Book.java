package io.github.acehoud01.library.items;

public class Book extends LibraryItem {
    private String isbn;
    private int pages;

    public Book(String id, String title, String author, String isbn, int pages){
        super(id, title, author);
        this.isbn = isbn;
        this.pages = pages;
    }

    @Override
    public int getBorrowPeriodDays() {
        return 14;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    public String getIsbn() { return isbn; }
    public int getPages() { return pages; }

    @Override
    public String toString() {
        return String.format("%s - ISBN: %s (%d pages)",
                super.toString(), isbn, pages);
    }
}
