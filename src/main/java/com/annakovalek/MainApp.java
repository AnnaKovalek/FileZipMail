package com.annakovalek;

import com.annakovalek.util.FileAnalyzerUtil;

/**
 * Main class App
 *
 * @author AnnaKovalek
 */
public class MainApp {
    /**
     * The path to the local files
     */
    private static final String PATH_TO_FILES = "myFiles/";

    /**
     * Start app
     *
     * @param args the array of strings
     */
    public static void main(String[] args) {
        String nameFile = "ReadMe.txt";
        boolean isExists = FileAnalyzerUtil.analyzeFileByName(PATH_TO_FILES, nameFile);

        System.out.println("File" + nameFile + "is existed? Answer" + isExists);


    }
}
