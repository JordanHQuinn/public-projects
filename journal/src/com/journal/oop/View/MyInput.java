package com.journal.oop.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyInput {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String input;

    public static String GetUserString() {
        String userString = "";
        try {
            userString = reader.readLine();
        } catch (Exception ex) {
            // TODO something
            userString = ex.getMessage();
        }
        return userString;
    }

    public static int GetUserInt() {
        int userInt = -1;
        while (userInt < 0) {
            try {
                userInt = Integer.parseInt(GetUserString());
            } catch (Exception ex) {
                // TODO something
                userInt = -1;
            }
            // Failed
            return userInt;
        }
        return -1;
    }
}