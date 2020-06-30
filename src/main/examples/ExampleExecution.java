package examples;

import code.Execution;

/**
 * Класс для выполнения программы с определенными примерами переменных
 */
public class ExampleExecution {
    private static String pageUrl = "https://www.simbirsoft.com/";
    private static String filePath = "src/main/examples/exampleFile.txt";

    public static void main(String[] args) {
        Execution execution = new Execution();
        System.out.println("Файл сохранится в текущую рабочую папку");
        execution.execute(pageUrl,filePath);
    }
}
