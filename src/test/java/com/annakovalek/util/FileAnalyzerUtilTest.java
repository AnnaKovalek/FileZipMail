package com.annakovalek.util;

import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * @author annakovalek
 */
public class FileAnalyzerUtilTest {

    /**
     * Directory of test the test files
     */
    private static final String DIR = "src/test/resources/testFiles";

    private static final String FILE_1 = "text.txt";
    private static final String FILE_2 = "image.jpg";
    private static final String FILE_3 = "read.txt";

    private static final Map<String, String> MAP_FILES = new HashMap<>();

    static {
        MAP_FILES.put(FILE_1, "src/test/resources/testFiles/text.txt");
        MAP_FILES.put(FILE_2, "src/test/resources/testFiles/image.jpg");
        MAP_FILES.put(FILE_3, "src/test/resources/testFiles/read.txt");
    }

    @BeforeClass
    public static void init() throws IOException {
        File folder = new File(DIR);

        if (folder.mkdirs()) {
            System.out.println(">>> Create test dir: " + DIR);

            for (String item : MAP_FILES.values()) {
                File file = new File(item);
                if (!file.exists()) {
                    boolean newFile = file.createNewFile();
                    System.out.println(" >>> Create new test file: " + newFile + " file name: " + item);
                }
            }
        }
    }

    @AfterClass
    public static void destroy() {
        File folder = new File(DIR);
        for (String item : MAP_FILES.values()) {
            File file = new File(item);
            if (file.exists()) {
                boolean delete = file.delete();
                System.out.println(" <<< Delete test file: " + delete + " file name: " + item);
            }
        }

        boolean delete = folder.delete();
        System.out.println("<<< Delete test " + delete + " dir: " + DIR);
    }

    /**
     * method: analyzeFileByName
     */
    @Test
    public void testAnalyzeFileByNameCorrectValue() {
        correctValuesAnalyzeFileByNameTrue(DIR, FILE_1);
        correctValuesAnalyzeFileByNameTrue(DIR, FILE_2);
        correctValuesAnalyzeFileByNameTrue(DIR, FILE_3);

        correctValuesAnalyzeFileByNameFalse(DIR, ".doc");
        correctValuesAnalyzeFileByNameFalse(DIR, ".exe");
        correctValuesAnalyzeFileByNameFalse(DIR, ".pdf");

        correctValuesAnalyzeFileByNameFalse("/", "src");
        correctValuesAnalyzeFileByNameFalse("/", "test");

        correctValuesAnalyzeFileByNameFalse("src/", FILE_2);
        correctValuesAnalyzeFileByNameFalse("/", FILE_3);
        correctValuesAnalyzeFileByNameFalse("/", "tar");
        correctValuesAnalyzeFileByNameFalse("/", "impl");
    }

    /**
     * method: analyzeFileByName
     * Expected: IllegalArgumentException, NullPointerException, ArithmeticException.
     */
    @Test
    public void testAnalyzeFileByName() {
        incorrectValues(null, "test.txt");
        incorrectValues("", "test.txt");
        incorrectValues("lof", null);
        incorrectValues("file", "");

        incorrectValues(DIR, "");
        incorrectValues(DIR, null);
    }

    /**
     * method: analyzeFileByExtension
     * Expected: IllegalArgumentException, NullPointerException, ArithmeticException.
     */
    @Test
    public void testAnalyzeFileByExtension() {
        incorrectValues(null, "test.txt");
        incorrectValues("", "test.txt");
        incorrectValues("lof", null);
        incorrectValues("file", "");
        incorrectValues("resources", ".png");
    }

    private void incorrectValues(String pathToFiles, String expression) {
        try {
            FileAnalyzerUtil.analyzeFileByName(pathToFiles, expression);
            fail("Fail!");
        } catch (IllegalArgumentException | NullPointerException | ArithmeticException ignored) {
            //ignore
        }
    }

    private void correctValuesAnalyzeFileByNameTrue(String pathToFiles, String nameOfFile) {
        //when
        boolean isExist = FileAnalyzerUtil.analyzeFileByName(pathToFiles, nameOfFile);

        //then
        assertTrue(isExist);
    }

    private void correctValuesAnalyzeFileByNameFalse(String pathToFiles, String nameOfFile) {
        //when
        boolean isExist = FileAnalyzerUtil.analyzeFileByName(pathToFiles, nameOfFile);

        //then
        assertFalse(isExist);
    }

}
