package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String inputFilePath = "words.txt";
        Map<String, Integer> wordFrequency = countWordFrequency(inputFilePath);
        printWordFrequency(wordFrequency);
    }

    private static Map<String, Integer> countWordFrequency(String filePath) {
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordFrequency;
    }

    private static void printWordFrequency(Map<String, Integer> wordFrequency) {
        List<Map.Entry<String, Integer>> sortedWords = wordFrequency.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toList());

        for (Map.Entry<String, Integer> entry : sortedWords) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}