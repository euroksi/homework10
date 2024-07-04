package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertToJSON {
    public static void main(String[] args) {
        String inputFilePath = "file.txt";
        String outputFilePath = "user.json";

        List<User> users = readUsersFromFile(inputFilePath);
        writeUsersToJsonFile(users, outputFilePath);
    }

    private static List<User> readUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine();
            if (header != null) {
                System.out.println("Header: " + header);
            }

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Line: " + line);
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String name = parts[0];
                    int age = 0;

                    try {
                        age = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format for age: " + parts[1]);
                        continue;
                    }

                    User user = new User(name, age);
                    users.add(user);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static void writeUsersToJsonFile(List<User> users, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new FileWriter(filePath), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

