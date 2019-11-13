package threads.multithreadStart;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CounterThread counterThread = new CounterThread();
        Thread newThread = new Thread(counterThread);
        newThread.start();
        long res = 0;
        for (int i = 0; i < 590_000; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            res += i;
        }
        System.out.println(res);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
