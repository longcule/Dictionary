package Ver2;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class DictionaryCommandline {
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public void dictionaryBasic() {
        int option;
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        do {
            System.out.println("option:");
            System.out.println("1.Add new word");
            System.out.println("2.Show all words");
           // System.out.println("3.Translate Eng-->Vie");
            System.out.println("3.Exit");
            option = sc1.nextInt();
            if (option == 1) {
                boolean running = true;
                int choice;
                while (running) {
                    dictionaryManagement.insertFromCommandline();
                    System.out.println("1.Continue add new word");
                    System.out.println("2.Back");
                    choice = sc2.nextInt();
                    running = (choice == 1);
                }
            } else if (option == 2) {
                this.dictionaryManagement.ShowAllWords();
            }/*else if (option == 3) {
                boolean running = true;
                int choice;
                while (running) {
                //   dictionaryManagement.dictionaryAdvanced();
                    System.out.println("1.Continue translate Eng-->Vie");
                    System.out.println("2.Back");
                  choice = sc2.nextInt();
                    running = (choice == 1);
                }
            }*/
        } while (option != 3);
    }
    public void dictionaryAdvanced () throws IOException {
              dictionaryManagement.InsertFromFile();
              dictionaryManagement.dictionaryLookup();
    }

}
