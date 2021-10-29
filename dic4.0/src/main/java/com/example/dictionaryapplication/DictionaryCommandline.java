package com.example.dictionaryapplication;

import java.util.Scanner;

public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public DictionaryCommandline() {
        this.dictionaryManagement.insertFromFile();
        this.dictionaryManagement.insertFromBookmark();
    }
    public void dictionaryBasic() throws Exception{
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("option:");
            System.out.println("1.Add new word");
            System.out.println("2.Show all words");
            System.out.println("3.Advanced options");
            System.out.println("4.Exit");
            option = sc.nextInt();
            if (option == 1) {
                this.dictionaryManagement.insertFromCommandline();
            } else if (option == 2) {
                this.dictionaryManagement.ShowAllWords();
            } else if (option == 3) {
                this.dictionaryAdvanced();
            }
        } while (option != 4);
        this.dictionaryManagement.exportData();
        this.dictionaryManagement.exportBookmark();
    }
    public void dictionaryAdvanced() throws Exception{
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Advanced option:");
            //System.out.println("1.Insert from database");
            System.out.println("1.Dictionary lookup");
            System.out.println("2.Translation");
            System.out.println("3.EditWord");
            System.out.println("4.DeleteWord");
            System.out.println("5.Searcher");
            System.out.println("6.Show bookmark");
            System.out.println("7.Return");
            option = sc.nextInt();
            if (option == 0) {
                this.dictionaryManagement.insertFromFile();
            } else if (option == 1) {
                this.dictionaryManagement.dictionaryLookup();
            } else if (option == 2) {
                this.dictionaryManagement.translate();
            } else if (option == 3) {
                this.dictionaryManagement.editWord();
            } else if (option == 4) {
                this.dictionaryManagement.deleteWord();
            } else if (option == 5){
                this.dictionaryManagement.dictionarySearcher();
            }else if (option == 6){
                this.dictionaryManagement.ShowAllBookmark();
            }
        } while(option != 7);
    }
}
