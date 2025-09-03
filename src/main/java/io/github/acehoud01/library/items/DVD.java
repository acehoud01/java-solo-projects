package io.github.acehoud01.library.items;

public class DVD extends LibraryItem{
    private int durationMinutes;
    private String genre;

    public DVD(String id, String title, String director, int durationMinutes, String genre) {
        super(id, title, director);
        this.durationMinutes = durationMinutes;
        this.genre = genre;
    }

    @Override
    public int getBorrowPeriodDays() {
        return 3; // 3 days for DVD
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    public int getDurationMinutes() { return  durationMinutes; };
    public String getGenre() { return genre; }

    @Override
    public String toString() {
        return String.format("%s - %s (%d min)",
                super.toString(), genre, durationMinutes);
    }
}
