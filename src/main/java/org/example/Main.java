package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Задание №5
 *
 * @author Суходоев Н.А.
 */

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\act1v\\IdeaProjects\\Collections\\words.txt";

        try {
            List<String> words = readWordsFromFile(filePath);
            System.out.println(words);
            Collections.sort(words);
            Map<String, Integer> wordCount = wordOccurrences(words);
            wordStatistics(wordCount);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
        }
    }

    /**
     * @return возвращает список слов, прочитанных из файла
     * @author Суходоев Н.А.
     * @see #readWordsFromFile(String) - метод для чтения слов из файла
     */

    private static List<String> readWordsFromFile(String filePath) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));

        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        scanner.close();
        return words;
    }

    /**
     * @return возвращает словарь, где каждому слову из списка соответствует количество его вхождений.
     * @author Суходоев Н.А.
     * @see #wordOccurrences(List) - принимает на вход список строк words
     */

    private static Map<String, Integer> wordOccurrences(List<String> words) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        return wordCount;
    }

    /**
     * @return выводит статистику - каждое слово и количество его вхождений, а также выводит слова с максимальным количеством вхождений
     * @author Суходоев Н.А.
     * @see #wordStatistics(Map) - принимает на вход словарь wordCount
     */

    private static void wordStatistics(Map<String, Integer> wordCount) {
        System.out.println("\nСтатистика:");
        int maxCount = 0;
        String key = null;
        int value = 0;

        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            value = entry.getValue();
            if (value > maxCount)
                maxCount = value;
            System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)");
        }
        System.out.println("\nСлова с максимальным количеством повторений: ");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (value == maxCount)
                System.out.println(String.format("- '%s' встречается в файле %d раз(а)", key, value));
        }
    }
}