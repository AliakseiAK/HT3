package entities;

import parsers.Parser;
import utils.MyTimer;

import java.text.DecimalFormat;

import static utils.AppConstants.*;

/**
 * Класс для отдельных операций тестов.
 */
public class TestOperation {
    private boolean isPassed;
    private String name;
    private String value;
    private double durationTime;
    private String timeout;
    private String error;

    public TestOperation(String name, String value) {
        this.name = name;
        this.value = value;
        this.error = "";
    }

    public boolean process(Parser parser, long time, int choosenTimeout) {
        timeout = "";
        MyTimer timer = new MyTimer();
        switch (name){
            case OPEN:
                isPassed = true;
                durationTime = time; //Мы уже обращались к сети и получили ответ, нет смысла повторять запрос.
                timeout = " \"" + choosenTimeout / 1000 + "\"";
                break;
            case CHECK_PAGE_TITLE:
                timer.start();
                isPassed = parser.checkPageTitle(value);
                timer.stop();
                durationTime = timer.getDurationInMilliseconds();
                break;
            case CHECK_PAGE_CONTAINS:
                timer.start();
                isPassed = parser.checkPageContains(value);
                timer.stop();
                durationTime = timer.getDurationInMilliseconds();
                break;
            case CHECK_LINK_PRESENT_BY_HREF:
                timer.start();
                isPassed = parser.checkLinkPresentByHref(value);
                timer.stop();
                durationTime = timer.getDurationInMilliseconds();
                break;
            case CHECK_LINK_PRESENT_BY_NAME:
                timer.start();
                isPassed = parser.checkLinkPresentByName(value);
                timer.stop();
                durationTime = timer.getDurationInMilliseconds();
                break;
            default:
                isPassed = false;
                error = UNSUPPORTED_OPERATION;

        }
        return isPassed;
    }

    public String getDescription(){
        if (this.error.equals("")) {
            DecimalFormat df = new DecimalFormat("#.###");
            return (isPassed ? PASSED : FAILED) + " [" + this.name + " \"" + this.value + "\"" + timeout + "] " +
                    df.format(durationTime / 1000) + "\n";
        } else {
            return UNSUPPORTED_OPERATION + "\n";
        }
    }
}
