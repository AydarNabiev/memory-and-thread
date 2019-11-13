package threads.deadlock;

public class Main {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Thread1 thread1 = new Thread1(a, b);
        Thread2 thread2 = new Thread2(a, b);
        thread1.start();
        thread2.start();
    }
}
