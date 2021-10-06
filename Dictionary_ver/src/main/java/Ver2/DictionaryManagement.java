package Ver2;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dictionary = new Dictionary();


    public void InsertFromFile() throws IOException {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("src\\main\\java\\Ver2\\data.txt"));
            while (sc.hasNext()) {
                String search = sc.nextLine();
                Scanner check = new Scanner(search).useDelimiter("    ");
                Word word = new Word();
                word.setWord_target(check.next());
                word.setWord_explain(check.next());
                dictionary.wordList.add(word);

            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insertFromCommandline() {
        Word word = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your word in English:");
        word.setWord_target(sc.nextLine());
        System.out.print("\nEnter your word in Vietnamese:");
        word.setWord_explain(sc.nextLine());
        dictionary.addWord(word);
    }

    public void ShowAllWords() {
        this.dictionary.ShowAllWords();
    }

    public void dictionaryLookup() {
        String s = "";
        System.out.println("Enter the word you want to look up:");
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        for (Word i: dictionary.wordList) {
            if (i.getWord_target().equals(s)) {
                System.out.println(s + " ------> " + i.getWord_explain());
            }
        }
    }
}

