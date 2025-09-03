package io.github.acehoud01.library.items;

public class Magazine extends LibraryItem {
    private String issueNumber;
    private int year;

    public Magazine(String id, String title, String publisher, String issueNumber, int year) {
        super(id, title, publisher);
        this.issueNumber = issueNumber;
        this.year = year;
    }

    @Override
    public int getBorrowPeriodDays() {
        return 7; // 1 week for magazines
    }

    @Override
    public String getItemType() {
        return "Magazine";
    }

    public String getIssueNumber() { return issueNumber; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return String.format("%s - Issue: %s (%d)",
                super.toString(), issueNumber, year);
    }
}
