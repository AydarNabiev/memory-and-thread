package memory.race;

public class Counter {
    private int count;

    public synchronized int getCount() {
        return count;
    }

    public synchronized void increment() {
        this.count++;
    }
}
