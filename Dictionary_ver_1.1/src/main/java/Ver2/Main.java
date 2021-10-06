package Ver2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        DictionaryCommandline dic = new DictionaryCommandline();
        int op;
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        do {
            System.out.println("option:");
            System.out.println("1.dictionaryBasic");
            System.out.println("2.dictionaryAdvanced");
            op = sc1.nextInt();
            if (op == 1) {
                dic.dictionaryBasic();
            } else if (op == 2) {
                boolean running = true;
                int choice;
                while (running) {
                    dic.dictionaryAdvanced();
                    System.out.println("1.Continue translate");
                    System.out.println("2.Back");
                    choice = sc2.nextInt();
                    running = (choice == 1);
                }
            }
        } while (op != 2);
    }
}
