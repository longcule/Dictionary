package com.example.dictionaryapplication;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;

public class DictionaryManagement {

    public Dictionary dictionary = new Dictionary();

    public DictionaryManagement() {

    }

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of word:");
        int n;
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Scanner sc1 = new Scanner(System.in);
            Word word = new Word();
            System.out.print("\nEnter your word in English:");
            word.setWord_target(sc1.nextLine());
            System.out.print("\nEnter your word in Vietnamese:");
            word.setWord_explain(sc1.nextLine());
            dictionary.addWord(word);
        }
    }

    public void addWord(String english, String vietnamese) throws IOException {
        Word word = new Word();
        word.setWord_target(english);
        word.setWord_explain(vietnamese);
        this.dictionary.wordList.add(word);
    }

    public void ShowAllWords() throws IOException { //return kieu String
        this.dictionary.ShowAllWords();
    }

    public void ShowAllBookmark() { // return kieu Strig
        this.dictionary.ShowAllBookmark();
    }

    public void dictionaryLookup() { // can co return kieu String khi su dung javafx
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        int run = 1;
        boolean check = false;
        while (run == 1) {
            System.out.println("Enter english word:");
            String search = sc.nextLine();
            for (Word iter : this.dictionary.wordList) {
                if (iter.getWord_target().equals(search)) {
                    check = true;
                    System.out.format("%-15s | %-15s\n",
                            "English", "Vietnamese");
                    System.out.format("%-15s | %-15s\n",
                            iter.getWord_target(),
                            iter.getWord_explain());
                    break;
                }
            }
            if (!check) {
                System.out.println("Not found!");
            }
            System.out.println("1.Continue");
            System.out.println("2.Return");
            run = sc1.nextInt();
        }
    }

    public String lookUp(String search) { // can co return kieu String khi su dung javafx
        for (Word iter : this.dictionary.wordList) {
            if (iter.getWord_target().equals(search)) {
                return dictionary.readline(iter.getWord_explain());
            }
        }
        return "Word not found!";
    }

    public void dictionarySearcher() { // su dung cho goi y khi tim tu
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        int run = 1;
        boolean check = false;
        while (run == 1) {
            System.out.println("Enter english word:");
            String search = sc.nextLine();
            System.out.format("%-5s | %-15s | %-15s\n",
                    "No.", "English", "Vietnamese");
            int count = 0;
            for (int i = 0; i < dictionary.getSize(); i++) {
                if (this.dictionary.wordList.get(i).getWord_target().charAt(0) == search.charAt(0)) {
                    for (int j = i; j < dictionary.getSize(); j++) {
                        if (this.dictionary.wordList.get(j).getWord_target().contains(search)) {
                            count++;
                            check = true;
                            System.out.format("%-5s | %-15s | %-15s\n",
                                    count,
                                    this.dictionary.wordList.get(j).getWord_target(),
                                    this.dictionary.wordList.get(j).getWord_explain());
                        }
                        if (count == 5) break;
                    }
                    break;
                }

            }
            if (!check) {
                System.out.println("Not found!");
            }
            System.out.println("1.Continue");
            System.out.println("2.Return");
            run = sc1.nextInt();
        }
    }

    public List<String> dictionarySearcherFX(String search) throws StringIndexOutOfBoundsException {
        List<String> result = new ArrayList<>();
        try {
            int count = 0;
            boolean check = false;
            int i = 0;
            for (Word iter : dictionary.wordList) {
                if (iter.getWord_target().charAt(0) == search.charAt(0)) {
                    for (int j = i; j < dictionary.getSize(); j++) {
                        if (dictionary.wordList.get(j).getWord_target().contains(search)) {
                            check = true;
                            count++;
                            result.add(dictionary.wordList.get(j).getWord_target());
                        }
                        if (count == 20) {
                            break;
                        }
                    }
                    break;
                }
                i++;
            }
            if (!check) {
                result.add("Word not found!");
                return result;
            }
            return result;
        } catch (NullPointerException exception) {
            result.add("");
            return result;
        }
    }

    public String translateFX(String from, String to, String s) throws Exception { // tham số truyền vào từ combobox
        return Translator.translate(from, to, s);
    }

    public void translate() throws Exception { // tham số truyền vào từ combobox
        try {
            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Translate from: ");
            String fromLang = sc.next();
            fromLang = getFromLanguageFromFile(fromLang);
            System.out.println("To: ");
            String toLang = sc.next();
            toLang = getToLanguageFromFile(toLang);
            System.out.println("Enter your word:");
            String s = sc1.nextLine();
            System.out.println(Translator.translate(fromLang, toLang, s));
            Word newWord = new Word();
            newWord.setWord_target(s);
            newWord.setWord_explain(Translator.translate(fromLang, toLang, s));
            dictionary.Bookmark.add(newWord);
        } catch (IOException e) {
            System.out.println("Can not insert from file !");
        }
    }

    public String getFromLanguageFromFile(String lang) {
        return this.dictionary.getFromLanguageFromFile(lang);
    }

    public String getToLanguageFromFile(String lang) {
        return this.dictionary.getToLanguageFromFile(lang);
    }

    public void editWord() {
        this.dictionary.edit();
    }

    public boolean editWordFX(String target, String newExplain) {
        return this.dictionary.editFX(target, newExplain);
    }

    public void deleteWord() {
        this.dictionary.delete();
    }

    public Boolean deleteWordFx(String word) {
        return dictionary.deleteWordFx(word);
    }

    public void exportData() throws IOException { // chay khi tắt chương trình
        PrintWriter writer = null;
        writer = new PrintWriter(new File("src\\main\\Database\\data.txt"));
        for (Word iter : this.dictionary.wordList) {
            writer.write(iter.getWord_target() + "~" + iter.getWord_explain() + "\n");
        }
        writer.flush();
        writer.close();
        System.out.println("Data is exported successfully");
    }

    public void exportBookmark() throws IOException {
        PrintWriter writer = null;
        writer = new PrintWriter(new File("src\\main\\Database\\Bookmark.txt"));
        for (Word iter : this.dictionary.Bookmark) {
            writer.write(iter.getWord_target() + ":" + iter.getWord_explain() + "\n");

        }
        writer.flush();
        writer.close();
        System.out.println("Bookmark is exported successfully");
    }

    public void insertFromFile() {
        try {
            Path path = Path.of("src\\main\\Database\\Data.txt");
            List<String> inputWordsList = Files.readAllLines(path);
            for (String word : inputWordsList) {
                String[] wordAttribute = word.split("~");
                Word newWord = new Word();
                newWord.setWord_target(wordAttribute[0]);
                newWord.setWord_explain(wordAttribute[1]);
                this.dictionary.addWord(newWord);
            }
            System.out.println("Database is inserted successfully");
        } catch (IOException e) {
            System.out.println("Can not insert from file !");
        }
    }

    public void insertFromBookmark() {
        try {
            Path path = Path.of("src\\main\\Database\\Bookmark.txt");
            List<String> inputWordsList = Files.readAllLines(path);
            for (String word : inputWordsList) {
                String[] wordAttribute = word.split(":");
                Word newWord = new Word();
                newWord.setWord_target(wordAttribute[0]);
                newWord.setWord_explain(wordAttribute[1]);
                this.dictionary.addBookmark(newWord);
            }
            System.out.println("Bookmark is inserted successfully");
        } catch (IOException e) {
            System.out.println("Can not insert from file !");
        }
    }
}
