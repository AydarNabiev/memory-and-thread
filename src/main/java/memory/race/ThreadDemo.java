package memory.race;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        demo();
    }

    private static void demo() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            Thread thread = new ResThread(counter);
//            thread.start();
            threads.add(thread); // Успевает отрабатывать до страта следующего
        }

        threads.parallelStream().forEach(Thread::start); //Запустим всех сразу
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Count = " + counter.getCount());
    }

}
