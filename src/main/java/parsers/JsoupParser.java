package parsers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.MyTimer;

import java.io.IOException;

/**
 * Класс, обеспечивающий парсинг переданной страницы. Применена библиотека jsoup.
 */

public class JsoupParser implements Parser {

    // Объективные причины применения jsoup:
    // 1. Да, можно написать regexp'ы для поиска внутри тегов. Однако, если подобная задача уже решена в общем виде
    // и использование готового решения не несет каких-либо заметных накладных расходов, лучше использовать его.
    // 2. Библиотека достаточно известна и гарантированно решает задачи парсинга html.
    // 3. Она берет на себя обработку соединения и получение страницы. Это кусок работы, который мне не придется делать вручную и возиться с низкоуровневыми вызовами.
    // 4. Разбирать HTML и XML с помощью регэкспов - не самая лучшая идея. Пояснения здесь:
    // https://stackoverflow.com/questions/701166/can-you-provide-some-examples-of-why-it-is-hard-to-parse-xml-and-html-with-a-reg
    // https://stackoverflow.com/questions/1732348/regex-match-open-tags-except-xhtml-self-contained-tags/1732454#1732454
    // Возможно, мне не хватает опыта в использовании регэкспов, но доводы по ссылкам выше представляются мне разумными.
    // Кроме того, я посмотрел код некоторых интересных в рамках задания методов jsoup и считаю, что попытка их самостоятельной реализации
    // с достаточной степенью универсальности только для того, чтобы задействовать регекспы в учебном задании, будет явным оверкиллом.
    //
    // Субъективная причина - у меня уже был опыт использования jsoup и я могу решить с ее помощью задачу за предсказуемое время.

    private Document doc;

    public JsoupParser() {
    }


    /**
     * Закачка заданной веб-страницы и извлечение нужных элементов.
     * Таймер измеряет время от отправки запроса до получения полного ответа.
     * @return коллекцию с результатами разбора страницы.
     */
    @Override
    public long open(String url, int timeoutInMillis) {
        try{
            MyTimer timer = new MyTimer();
            timer.start();
            doc = Jsoup.connect(url).get();
            timer.stop();
            if (timer.getDurationInMilliseconds() > timeoutInMillis){
                return -1;
            } else {
                return timer.getDurationInMilliseconds();
            }
        }catch (IOException ex){
            return -1;
        }
    }

    /**
     * Поиск на странице ссылки с заданным адресом.
     * @param href адрес
     * @return true если найдена.
     */
    @Override
    public boolean checkLinkPresentByHref(String href) {
        return doc.getElementsByAttributeValueContaining("href", href) != null;
    }

    /**
     * Поиск на странице ссылки с заданным именем.
     * @param linkName имя ссылки
     * @return true если найдена.
     */
    @Override
    public boolean checkLinkPresentByName(String linkName) {
        return doc.select("a:contains(" + linkName + ")").first() != null;
    }

    /**
     * Сравнение с заданным текстом заголовка.
     * @param query текст заголовка
     * @return true если текст совпадает.
     */
    @Override
    public boolean checkPageTitle(String query) {
        return doc.title().equals(query);
    }

    /**
     * Поиск на странице произвольного текста.
     * @param query текст для поиска.
     * @return true если найдена.
     */
    @Override
    public boolean checkPageContains(String query) {
        return doc.body().toString().contains(query);
    }
}
