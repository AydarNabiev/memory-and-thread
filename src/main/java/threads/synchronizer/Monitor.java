package threads.synchronizer;

public class Monitor {
    private int store;

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public synchronized int increment() {
        store += 1;
        return store;
    }
}
