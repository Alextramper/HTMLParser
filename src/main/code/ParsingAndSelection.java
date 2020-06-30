package code;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Класс для выделения текста из кода url-страницы и подсчета уникальных слов
 */
public class ParsingAndSelection {

    /**
     * @param spliters массив из символов-разделителей
     */
    String[] spliters = {"\\s", ",", ".", "!", "\\?", "\"", ";", ":", "\\[", "]", "\\(", "\\)", "\\t", "\\r", "\\n"};
    Map<String, Integer> uniqueWords = new LinkedHashMap<>();

    /**
     * Метод, выделяющй текстовые данные из данных страницы и выводящий результат в консоль
     * @param file файл с загруженными данными url-страницы
     * @throws IOException
     */
    public void parseTextAndSelectWords(File file) throws IOException {
        String textOfPage;
        Document pageFile = Jsoup.parse(file, "UTF-8");
        Elements text = pageFile.getElementsByTag("body");
        textOfPage = text.text();
        uniqueWords = textSpliting(textOfPage, spliters.length - 1);

        for (Map.Entry<String, Integer> map : uniqueWords.entrySet()) {
            System.out.println(map.getKey() + " - " + map.getValue());
        }
    }

    /**
     * Рекурсивный метод, разделяющий слова из текста по определенным символам и возвращающий Map<Key,Value>,
     * содержащий список уникальных слов в тексте и их количество
     * @param text выделенный текст путем парсинга файла с загруженными данными
     * @param spliterIndex индекс массива символов
     */
    public Map<String, Integer> textSpliting(String text, int spliterIndex) {
       if (spliterIndex == 0) {
            Map<String, Integer> map = new LinkedHashMap<>();
            text = text.toUpperCase();
            String[] splitedText = text.split(spliters[0]);
            List<String> textList = Arrays.asList(splitedText);
            for( String str: textList){
                if(StringUtils.isNotBlank(str))
                map.put(str, Collections.frequency(textList, str));
            }
            return map;
        }
        String replacedChars = text.replace(spliters[spliterIndex]," ");
        return textSpliting(replacedChars, spliterIndex-1);
    }
}
