package Ver2;

import java.util.ArrayList;


public class Dictionary {

    public static ArrayList<Word> wordList = new ArrayList<>();
    private int size;
    Dictionary() {
        size = 0;
    }

    public void addWord(Word word) {
        wordList.add(word);
        size++;
    }

    public int getSize() {
        return this.size;
    }

    public void ShowAllWords() {
        System.out.format("%-5s | %-15s | %-15s\n",
                "No.", "English", "Vietnamese");
        for (int i = 0; i < this.getSize(); i++) {
            System.out.format("%-5s | %-15s | %-15s\n",
                    i + 1,
                    this.wordList.get(i).getWord_target(),
                    this.wordList.get(i).getWord_explain());

        }
    }
}
