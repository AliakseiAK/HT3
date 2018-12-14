package utils;

/**
 * Класс, позволяющий получать продолжительность операций. Как ни странно, готового решения я не нашел.
 */
public class MyTimer {
    private long startTime;
    private long endTime;

    public MyTimer() {
    }

    public void start(){
        this.startTime = System.nanoTime();
    }

    public void stop(){
        this.endTime = System.nanoTime();
    }

    public long getDurationInMilliseconds(){
        return (this.endTime - this.startTime) / 1000000;
    }
}
