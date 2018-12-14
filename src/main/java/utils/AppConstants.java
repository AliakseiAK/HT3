package utils;

/**
 * Класс содержащий константы названий и сообщений. Исключает опечатки.
 */
public class AppConstants {
    //Обозначения результатов
    public static final String PASSED = "+";
    public static final String FAILED = "!";

    //Сообщения
    public static final String USAGE = "Неверное количество аргументов. Приложение принимает два аргумента: имя файла с командами; имя файла для вывода лога.";
    public static final String TXT_TOTAL_TESTS = "Всего выполнено тестов: ";
    public static final String TXT_PASSED_TESTS = "Успешно пройденных тестов: ";
    public static final String TXT_FAILED_TESTS = "Непройденных тестов: ";
    public static final String TXT_TOTAL_TIME = "Общее время выполнения всех тестов: ";
    public static final String TXT_AVERAGE_TIME = "Среднее время выполнения теста: ";
    public static final String ERR_CREATE_FILE = "Ошибка создания лог-файла!";
    public static final String ERR_NETWORK = "Ошибка доступа к заданному адресу!";
    public static final String ERR_TIMEOUT = "Превышен таймаут соединения!";
    public static final String UNSUPPORTED_OPERATION = "Операция не поддерживается";

    //Названия операций
    public static final String OPEN = "open";
    public static final String CHECK_PAGE_TITLE = "checkPageTitle";
    public static final String CHECK_PAGE_CONTAINS = "checkPageContains";
    public static final String CHECK_LINK_PRESENT_BY_HREF = "checkLinkPresentByHref";
    public static final String CHECK_LINK_PRESENT_BY_NAME = "checkLinkPresentByName";

}
