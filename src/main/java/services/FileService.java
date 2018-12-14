package services;

import entities.TestsSuite;
import entities.TestOperation;
import entities.TestTask;

import java.io.*;

import static utils.AppConstants.*;

/**
 * Класс, выполняющий операции с файлами: чтение и запись.
 */
public class FileService {
    private String inputFile;

    public FileService(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Чтение и парсинг файла с задачами. В принципе, для стройности архитектуры можно было вынести парсинг в отдельный класс,
     * но я решил в данном случае не усложнять.
     * @return подготовленный набор тестов.
     * @throws IOException при ошибках ввода/вывода.
     */
    public TestsSuite readFile() throws IOException {
        TestsSuite suite = new TestsSuite();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            TestTask currentTask = null;
            String currentUrl;
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split(" \"");
                if (splittedLine[0].equals(OPEN)){
                    currentTask = new TestTask();
                    currentUrl = splittedLine[1].substring(0, splittedLine[1].length() - 1);
                    currentTask.setUrl(currentUrl);
                    int seconds = Integer.parseInt(splittedLine[2].substring(0, splittedLine[2].length() - 1));
                    currentTask.setTimeoutInMillis(seconds * 1000);
                    suite.getTasks().add(currentTask);
                    TestOperation testOperation = new TestOperation(OPEN, currentUrl);
                    currentTask.getOperations().add(testOperation);

                } else if (splittedLine[0].equals(CHECK_PAGE_TITLE) || splittedLine[0].equals(CHECK_PAGE_CONTAINS)){ //Предполагается, что имена операций корректны
                    assert currentTask != null; // Предполагается, что текстовый файл корректный и каждая задача начинается с open.
                    TestOperation testOperation = new TestOperation(splittedLine[0], splittedLine[1].substring(0, splittedLine[1].length() - 1));
                    currentTask.getOperations().add(testOperation);
                }
            }
        }
        return suite;
    }

    /**
     * Запись лог-файла
     * @param suite результаты тестов.
     * @param outputFile файл для записи.
     */
    public void writeLogFile(TestsSuite suite, String outputFile) {
        File file = new File(outputFile);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(suite.generate());
            writer.close();
        } catch (IOException e) {
            System.out.println(ERR_CREATE_FILE);
        }
    }
}
