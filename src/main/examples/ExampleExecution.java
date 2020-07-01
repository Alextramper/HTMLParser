package examples;

import code.Execution;

/**
 * Класс для выполнения программы с определенными примерами переменных
 */
public class ExampleExecution {
    private static String pageUrl = "https://www.workaway.info/";
    private static String filePath = "F:/My files/exampleFile.html";

    public static void main(String[] args) {
        Execution execution = new Execution();
        System.out.println("File will be here: " + filePath);
        execution.execute(pageUrl,filePath);
    }
}
