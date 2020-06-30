package code;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.*;


/**
 * Класс выполнения обоих фаз программы
 */
public class Execution {

   public static final Logger logger  = Logger.getLogger(Execution.class.getName());

   public static void execute(String pageUrl, String filePath){
        try {
            LogManager.getLogManager().readConfiguration();
            Handler consoleHandler = new ConsoleHandler();
            logger.addHandler(consoleHandler);
        }catch(IOException e) {
            e.printStackTrace();
        }
        InputOutputProcess inputOutputProcess = new InputOutputProcess();
        ParsingAndSelection parsingAndSelection = new ParsingAndSelection();
        inputOutputProcess.downloadgPageCode(pageUrl, filePath);
        try {
            File file = new File(filePath);
            parsingAndSelection.parseTextAndSelectWords(file);
        } catch (FileNotFoundException e){
            logger.log(Level.WARNING,"Возникла ошибка  из-за отсутствия загруженного файла");
        } catch (IOException e) {
            logger.log(Level.WARNING,"Ошибка:"+ e.getMessage());
        }
    }

}
