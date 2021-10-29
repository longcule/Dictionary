package com.example.dictionaryapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
    public List<Word> wordList = new ArrayList<>();
    public List<Word> Bookmark = new ArrayList<>();
    private int size;
    Dictionary() {
        size = 0;
    }

    public void addWord(Word word) {
        wordList.add(word);
        size++;
    }
    public void addBookmark(Word word) {
        Bookmark.add(word);
    }
    public int getSize() {
        return this.size;
    }

    public static String readline(String s) {
        for (int i = 0; i < s.length();i ++) {
            if (s.charAt(i) == ';') {
                s = s.substring(0, i) + "\n" + s.substring(i + 1);
            }
        }
        return s;
    }
    public void ShowAllWords() throws IOException { // fix khi su dung javafx
        System.out.format("%-5s | %-15s | %-15s\n",
                "No.", "English", "Vietnamese");
        int i = 1;
        for (Word iter : this.wordList) {
            System.out.format("%-5s | %-15s | %-15s\n",
                    i + 1,
                    iter.getWord_target(),
                    "");
                    //readline(iter.getWord_explain()));
            i ++;
        }
    }
    public void ShowAllBookmark() { // fix khi su dung java fx
        System.out.format("%-5s | %-15s | %-15s\n",
                "No.", "Input", "Output");
        int i = 0;
        for (Word iter: Bookmark) {
            System.out.format("%-5s | %-15s | %-15s\n",
                    i + 1,
                    iter.getWord_target(),
                    iter.getWord_explain());
            i++;

        }
    }

    public void edit() {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter word edit needed:");
        String target = sc.next();
        boolean check = false;
        for(Word iter : this.wordList) {
            if(iter.getWord_target().equals(target)) {
                check = true;
                System.out.println("Enter new explain:");
                String explain = sc1.nextLine();
                iter.setWord_explain(explain);
            }
        }
        if (!check) {
            System.out.println("Word is not found!");
        } else {
            System.out.println("Word is edited");
        }
    }

    public boolean editFX(String target, String newExplain) {
        boolean check = false;
        for(Word iter : this.wordList) {
            if(iter.getWord_target().equals(target)) {
                check = true;
                iter.setWord_explain(newExplain);
            }
        }
        return check;
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter delete word:");
        String deletion = sc.next();
        boolean check = false;
        for(int i = 0; i < size; i++) {
            if(wordList.get(i).getWord_target().equals(deletion)) {
                check = true;
                wordList.remove(wordList.get(i));
                size--;
            }
        }
        if (!check) {
            System.out.println("Word is not found!");
        } else {
            System.out.println("Word is deleted!");
        }
    }

    public Boolean deleteWordFx(String word) {
        boolean check = false;
        for(int i = 0; i < size; i++) {
            if(wordList.get(i).getWord_target().equals(word)) {
                wordList.remove(wordList.get(i));
                size--;
                check = true;
            }
        }
        return check;
    }

    public static String getToLanguageFromFile(String lang){
        try {
            Path path = Path.of("src\\main\\Database\\Languages.txt");
            List<String> inputLanguages = Files.readAllLines(path);
            for (String language : inputLanguages) {
                String[] split = language.split(": ");
                if(split[1].equals(lang)){
                    return split[0];
                }
            }
        } catch (IOException e) {
            System.out.println("Can not insert from file !");
        }
        return "vi";
    }

    public static String getFromLanguageFromFile(String lang){
        try {
            Path path = Path.of("src\\main\\Database\\Languages.txt");
            List<String> inputLanguages = Files.readAllLines(path);
            for (String language : inputLanguages) {
                String[] split = language.split(": ");
                if(split[1].equals(lang)){
                    return split[0];
                }
            }
        } catch (IOException e) {
            System.out.println("Can not insert from file !");
        }
        return "en";
    }

}
