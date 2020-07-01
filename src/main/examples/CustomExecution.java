package examples;

import code.Execution;
import java.util.Scanner;

/**
 * Класс для выполнения программы с пользовательскими данными
 */
public class CustomExecution {

    /**
     * @param pageUrl вводимый url страницы сервера
     * @param filePath вводимый путь к создаваемому файлу с загружаемым кодом страницы сервера
     */
    private static String pageUrl;
    private static String filePath;

    public static void main(String[] args) {
        Execution execution = new Execution();
        setPageUrl();
        setFilePath();
        execution.execute(pageUrl,filePath);
    }

    static void setPageUrl() {
        System.out.println("Введите url страницы: ");
        pageUrl = new Scanner(System.in).nextLine();
    }

    static void setFilePath() {
        String fileName;
        System.out.println("Придумайте имя файлу, содержащему HTML-страницу: ");
        fileName = new Scanner(System.in).nextLine() + ".html";
        System.out.println("Введите путь, куда нужно загрузить файл: ");
        filePath = new Scanner(System.in).nextLine();
        if (filePath.endsWith("\\")) {
            filePath = filePath + fileName;
        } else {
            filePath = filePath + "\\" + fileName;
        }
    }
}
