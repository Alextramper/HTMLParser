package code;
import org.junit.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class ParsingAndSelectionTest {
    String text;
    public ParsingAndSelection parsingAndSelection = new ParsingAndSelection();
    File file = new File("test/resources/Second UNIT Test.txt");

    /**
     * Проверка логики на правильный подсчет уникальных слов
     */
    @Test
    public void textSplitingTest() {
        try {
            StringBuilder strBuild = new StringBuilder();
            Map<String,Integer> someMap;

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while((text = bufferedReader.readLine()) != null) {
                strBuild.append(text.replace("\\t", " "));
            }
            this.text = strBuild.toString();
            someMap = parsingAndSelection.textSpliting(text, parsingAndSelection.spliters.length-1);
            //Для наглядной проверки
            //for (Map.Entry<String, Integer> map : someMap.entrySet()) {
            //  System.out.println(map.getKey() + " - " + map.getValue());}
            bufferedReader.close();
            boolean equal = someMap.get("ФАЙЛ") == 4;
            Assert.assertTrue(equal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}