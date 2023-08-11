package com.journal.oop.Controller;

import com.journal.oop.DAL.JournalDAL;
import com.journal.oop.Model.JournalEntry;
import com.journal.oop.View.MyInput;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DoWork {
    ArrayList<JournalEntry> journalEntries = new ArrayList<JournalEntry>();
    ArrayList<JournalEntry> readEntries = null;
    JournalDAL journalDAL = new JournalDAL();
    MyInput input = new MyInput();
    int userInput;
    int month = 1;
    int day = 1;
    int year = 1;
    int hour = 1;
    int minute = 1;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");


    //user menu
    public int menu() throws IOException, ClassNotFoundException {
        while (userInput != 5) {
            System.out.println("What would you like to do?");
            System.out.println("1: Create new journal entry");
            System.out.println("2: View journal entry by date");
            System.out.println("3: Search for journal entries in a range of dates");
            System.out.println("4: Edit selected entry");
            System.out.println("5: exit");
            userInput = MyInput.GetUserInt();
            switch (userInput) {
                case 1:
                    LocalDateTime date = getDate();
                    String stringEntry = createEntry();
                    JournalEntry entry = new JournalEntry(date, stringEntry);
                    System.out.println(entry);
                    journalEntries.add(entry);
                    try {
                        journalDAL.save(journalEntries);
                    } catch (Exception ex) {
                        System.out.println("No can Do " + ex.getMessage());
                    }

                    System.out.println(journalEntries);
                    break;
                case 2:
                    System.out.println("What year entry would you like to see?");
                    int userEntry = MyInput.GetUserInt();
                    viewEntry(userEntry);
                    break;
                case 3:
                    System.out.println("What is the starting year of the range?");
                    int start = MyInput.GetUserInt();
                    System.out.println("What is the ending year of the range?");
                    int end = MyInput.GetUserInt();
                    searchDateRange(start, end);
                    break;
                case 4:
                    System.out.println("Which entry would you like to edit?");
                    int intEntry = MyInput.GetUserInt();
                    System.out.println("What would you like to add?");
                    String strInput = MyInput.GetUserString();
                    editEntry(intEntry, strInput);
                    break;
                case 5:
                    System.out.println("Goodbye");
                    break;
            }
        }
        return userInput;
    }

    //add date to journal entry
    public LocalDateTime getDate() {
        int result;
        System.out.println("Enter month");
        result = MyInput.GetUserInt();
        if (result == -1) {
            month = LocalDateTime.now().getMonthValue();
        } else {
            month = result;
        }
        System.out.println("Enter Day");
        result = MyInput.GetUserInt();
        if (result == -1) {
            day = LocalDateTime.now().getDayOfMonth();
        } else {
            day = result;
        }
        System.out.println("Enter Year");
        result = MyInput.GetUserInt();
        if (result == -1) {
            year = LocalDateTime.now().getYear();
        } else {
            year = result;
        }
        System.out.println("enter Hour");
        result = MyInput.GetUserInt();
        if (result == -1) {
            hour = LocalDateTime.now().getHour();
        } else {
            hour = result;
        }
        System.out.println("enter minute");
        result = MyInput.GetUserInt();
        if (result == -1) {
            minute = LocalDateTime.now().getMinute();
        } else {
            minute = result;
        }
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        String formattedDate = date.format(dateFormatter);
        System.out.println(formattedDate);
        return date;
    }

    //add a string to journal entry
    public String createEntry() {
        String stringEntry = " ";
        System.out.println("What would you like your entry to say?");
        stringEntry = MyInput.GetUserString();
        return stringEntry;
    }

    //View a single entry
    public void viewEntry(int input) {
        for (int entry = 0; entry < journalEntries.size(); entry++) {
            try {
                if (journalDAL.load().get(entry).getDate().getYear() == input) {

                    readEntries = journalDAL.load();
                    System.out.println(readEntries.get(entry));


                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Search for entries based on range
    public void searchDateRange(int startYear, int endYear) {
        for (int entry = 0; entry < journalEntries.size(); entry++) {
            try {
                if (journalDAL.load().get(entry).getDate().getYear() >= startYear && journalDAL.load().get(entry).getDate().getYear() <= endYear) {

                    readEntries = journalDAL.load();
                    System.out.println(readEntries.get(entry));


                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void editEntry(int input, String strInput) {
        for (int entry = 0; entry < journalEntries.size(); entry++) {
            try {
                if (journalDAL.load().get(entry).getDate().getYear() == input) {

                    readEntries = journalDAL.load();
                    readEntries.get(entry).setEntry(readEntries.get(entry).getEntry() + " " + strInput);
                    journalDAL.save(readEntries);
                    System.out.println(readEntries.get(entry));


                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
