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
            String header = br.readLine(); // Пропускаємо заголовок

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);

                User user = new User(name, age);
                users.add(user);
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