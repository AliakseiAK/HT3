package services;

import entities.TestsSuite;
import entities.TestTask;
import parsers.JsoupParser;

/**
 *  Класс, абстрагирующий от остального приложения обработку страницы, что позволяет при необходимости подключать несколько разных парсеров.
 *
 */
public class ParseService {
    private JsoupParser parser;

    public ParseService() {
        this.parser = new JsoupParser();
    }

    public void processTestSuite(TestsSuite testsSuite) {
        for (TestTask task : testsSuite.getTasks()) {
            task.getResult(parser);
        }
        testsSuite.validate();
    }
}
