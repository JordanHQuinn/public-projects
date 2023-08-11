package com.journal.oop.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class JournalEntry implements Serializable {
    private LocalDateTime date;
    private String entry;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public JournalEntry() {

    }

    public JournalEntry(LocalDateTime date, String entry) {
        this.date = date;
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "date=" + date +
                ", entry='" + entry + '\'' +
                '}';
    }
}
