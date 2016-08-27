package com.annakovalek.util;

import java.io.File;

/**
 * The class {@link FileAnalyzerUtil} analyzes the selected files
 *
 * @author AnnaKovalek
 */
public class FileAnalyzerUtil {
    /**
     * Анализирует папку на наличие определенного файла
     *
     * @param pathToFiles the path to files
     * @param nameOfFile  the name of file
     * @return true if the file is found
     */
    public static boolean analyzeFileByName(String pathToFiles, String nameOfFile) {


        File[] files = validateIncomingParameters(pathToFiles, nameOfFile);

        for (File file : files) {
            if (file.getName().equals(nameOfFile)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param pathToFiles the path to files
     * @param extension   the extension of file
     * @return true if file is found
     */
    public static boolean analyzeFileExtension(String pathToFiles, String extension) {

        File[] files = validateIncomingParameters(pathToFiles, extension);

        for (File file : files) {
            if (file.getName().endsWith(extension)) { //другое условие
                return true;
            }
        }

        return false;
    }

    /**
     * Validate incoming parameters: String pathToFiles ana expression: String extension, String nameOfFile
     *
     * @param pathToFiles path to files
     * @param expression  extension or nameOfFile
     * @return the array of files if the parameters are validated
     * @throws IllegalArgumentException if Incorrect path to file.
     * @throws RuntimeException if incorrect  expression values: nameOfFile or extension .value.
     * @throws NullPointerException if path to file is not a directory.
     * @throws IllegalArgumentException if directory is empty.
     */

    private static File[] validateIncomingParameters(String pathToFiles, String expression) {
        //validation incoming values
        if (pathToFiles == null || pathToFiles.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path to file");
        }
        //validation incoming values
        if (expression == null || expression.isEmpty()) {
            throw new RuntimeException("Incorrect  expression values");
        }
        File directory = new File(pathToFiles);
        if (!directory.isDirectory()) {
            throw new NullPointerException("The current file path " + pathToFiles +
                    "is not a directory");
        }
        File[] files = directory.listFiles();
        if (files == null) {
            throw new IllegalArgumentException("Directory is empty");
        }
        return files;
    }


}
