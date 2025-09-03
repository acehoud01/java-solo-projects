package io.github.acehoud01.library.interfaces;

import java.time.LocalDate;

public interface Borrowable {
    void borrowItem(String memberName);
    void returnItem();
    boolean isAvailable();
    LocalDate getDueDate();
    String getBorrowerName();
}
