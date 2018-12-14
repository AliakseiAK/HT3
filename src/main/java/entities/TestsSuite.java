package entities;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static utils.AppConstants.*;

/**
 * Класс для набора тестов.
 */
public class TestsSuite {
    private ArrayList<TestTask> tasks;
    private int totalTests;
    private int passedTests;
    private int failedTests;
    private double totalTime;
    private double averageTime;

    public TestsSuite() {
        this.tasks = new ArrayList<>();
    }


    public void validate(){
        for (TestTask testTask : tasks){
            totalTests += testTask.getOperations().size();
            passedTests += testTask.getPassedOperations();
            failedTests = totalTests - passedTests;
            this.totalTime += testTask.getDurationInMillis();
        }
        averageTime = totalTime / totalTests;
    }

    public String generate(){
        StringBuilder stringBuilder = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.###");

        for (TestTask testTask : this.getTasks()){
            stringBuilder.append(testTask.generate()).append("\n");
        }
        stringBuilder.append(TXT_TOTAL_TESTS).append(this.totalTests).append("\n");
        stringBuilder.append(TXT_PASSED_TESTS).append(this.passedTests).append("\n");
        stringBuilder.append(TXT_FAILED_TESTS).append(this.failedTests).append("\n");
        stringBuilder.append(TXT_TOTAL_TIME).append(df.format(this.totalTime / 1000)).append("\n");
        stringBuilder.append(TXT_AVERAGE_TIME).append(df.format(this.averageTime / 1000)).append("\n");
        return stringBuilder.toString();
    }

    public ArrayList<TestTask> getTasks() {
        return tasks;
    }
}
