package entities;


import parsers.Parser;

import java.util.ArrayList;

import static utils.AppConstants.ERR_NETWORK;

/**
 * Класс, описывающий задание для отдельного хоста. Такой подход позволит впоследствии добавлять новые
 * варианты операций. Содержит произвольное количество отдельных конкретных операций.
 */
public class TestTask {
    private String url;
    private ArrayList<TestOperation> operations;

    private int timeoutInMillis;
    private long durationInMillis;
    private int passedOperations;

    private String error = "";

    public TestTask() {
        this.operations = new ArrayList<>();
    }

    /**
     * Запускает выполнение операций.
     * @param parser выбранный вариант парсера.
     */
    public void getResult(Parser parser) {
        long time = parser.open(this.url, this.timeoutInMillis);
        if (time != -1){
            this.durationInMillis = time;
            for (TestOperation operation : operations) {
                if (operation.process(parser, time, this.timeoutInMillis)) {
                    passedOperations++;
                }
            }
        } else {
            error = ERR_NETWORK;
        }
    }


    /**
     * Выдает отформатированное описание для лога.
     * Содержит информацию, относящуюся к адреса и описания всех заданий для него.
     * @return отформатированное описание результатов теста одного адреса.
     */
    public String generate(){
        if (this.error.equals("")){
            StringBuilder stringBuilder = new StringBuilder();
            for (TestOperation testOperation : operations){
                stringBuilder.append(testOperation.getDescription());
            }
            stringBuilder.append("\n");
            return stringBuilder.toString();
        } else {
            return ERR_NETWORK + "\n";
        }
    }

    public void setTimeoutInMillis(int timeoutInMillis) {
        this.timeoutInMillis = timeoutInMillis;
    }

    long getDurationInMillis() {
        return durationInMillis;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<TestOperation> getOperations() {
        return operations;
    }

    int getPassedOperations() {
        return passedOperations;
    }
}
