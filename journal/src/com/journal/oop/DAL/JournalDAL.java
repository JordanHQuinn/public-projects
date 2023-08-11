package com.journal.oop.DAL;

import com.journal.oop.Model.JournalEntry;

import java.io.*;
import java.util.ArrayList;

public class JournalDAL {
    String fileName = "C:\\temp\\file.xjson";

    ///////////////////////saving to file
    public void save(ArrayList<JournalEntry> journalEntries) throws IOException {
        FileOutputStream fileStream = new FileOutputStream(fileName);
        ObjectOutputStream outStream = new ObjectOutputStream(fileStream);
        outStream.writeObject(journalEntries);
        outStream.close();
    }

    //////////////
//loading from file name
    public ArrayList<JournalEntry> load() throws IOException, ClassNotFoundException {
        ArrayList<JournalEntry> tempJE = null;
        FileInputStream inputStream = new FileInputStream(fileName);
        ObjectInputStream objInputString = new ObjectInputStream(inputStream);
        ///////////////////
        tempJE = (ArrayList<JournalEntry>) objInputString.readObject();
        return tempJE;
    }

    public String getFileName() {
        return fileName;
    }
}
