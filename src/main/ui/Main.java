package ui;

import java.io.FileNotFoundException;

// Codes inspired by "https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo"
public class Main {
    public static void main(String[] args) {
        try {
            new ClubApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
