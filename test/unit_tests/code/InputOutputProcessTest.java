package code;

import org.junit.Test;
import org.junit.Assert;

import java.io.*;

public class InputOutputProcessTest {
    private static final String url = "https://www.simbirsoft.com/";
    private static final String filePath = "test/resources/First Test.txt";
    InputOutputProcess inputOutputProcess = new InputOutputProcess();


    /**
     * Проверка на существование и содержимость загруженного файла
     */
    @Test
    public void downloadgPageCodeTestOne(){

            File file = new File(filePath);
            inputOutputProcess.downloadgPageCode(url, filePath);
            boolean fileExist = file.exists();
            Assert.assertTrue(fileExist);
            try {
                 BufferedReader buffReader = new BufferedReader((new FileReader(file)));
                 String fileContains = buffReader.readLine();
                 boolean fileIsEmpty = fileContains.isEmpty();
                 Assert.assertFalse(fileIsEmpty);
                 try {
                    buffReader.close();
                 } catch (IOException e) {
                    e.printStackTrace();
                 }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден или путь указан неверно");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
