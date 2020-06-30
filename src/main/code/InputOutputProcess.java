package code;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для подключения к серверу путем HTTP-запроса и загрузки кода страницы в текстовый файл
 */
public class InputOutputProcess {

    /** Параметры HTTP - запроса
     * @param GET_METHOD тип HTTP - запроса
     * @param CONNECT_TIME время подключения к серверу
     * @param READ_TIME время ожидания данных с сервера для их чтения
     */

    private String  GET_METHOD = "GET";
    private int CONNECT_TIME = 3000;
    private int READ_TIME = 5000;
    private static final Logger logger = Logger.getLogger(InputOutputProcess.class.getName());
    /**
     * Подключение к серверу с установленными параметрами запроса
     */
    public void downloadgPageCode(String pageUrl, String filePath) {
        HttpURLConnection connection = null;
        BufferedReader buffRead = null;
        BufferedWriter buffWrite = null;

        try {
            connection = (HttpURLConnection) new URL(pageUrl).openConnection();
            connection.setRequestMethod(GET_METHOD);
            connection.setUseCaches(Boolean.FALSE);
            connection.setConnectTimeout(CONNECT_TIME);
            connection.setReadTimeout(READ_TIME);
            connection.connect();

           try {
               if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                   buffRead = new BufferedReader(new InputStreamReader(connection.
                                                                        getInputStream(), "UTF-8"));
                   buffWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
                   String pageContent;
                   while ((pageContent = buffRead.readLine()) != null) {
                           buffWrite.write(pageContent + "\n");
                           //buffWrite.write("\n");
                   }
                   buffWrite.flush();
               } else {
                   logger.log(Level.WARNING,"Не удалось получить ответ от сервера: "
                           + connection.getResponseMessage() + ", код " + connection.getResponseCode());
               }
           } catch (FileNotFoundException f){
              logger.log(Level.WARNING,"Ошибка: некорректно введен путь загружаемого файла");
           } finally {
               try {
                   if(buffWrite!=null)
                       buffWrite.close();
                   if(buffRead!=null)
                       buffRead.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        } catch (ConnectException e) {
            logger.log(Level.WARNING,"Время подключения к сервверу истекло, ответ от сервера не получен");
        } catch (SocketTimeoutException e) {
            logger.log(Level.WARNING,"Время ожидания данных для чтения с сервера истекло, попробуйте заново");
        } catch (MalformedURLException e) {
            logger.log(Level.WARNING,"Некорректно введен URL адрес, не удалось подключиться");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка: чтение данных URL невозвожно");
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
