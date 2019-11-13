package threads.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorTest.
 *
 * @author Ilya_Sukhachev
 */
public class ExecutorTest {

    static ExecutorService staticThreads = Executors.newFixedThreadPool(2);

    static ExecutorService dinamicThreads = Executors.newCachedThreadPool();

    static ExecutorService singleThread = Executors.newSingleThreadExecutor();

    static ExecutorService forkJoinPool = Executors.newWorkStealingPool();

    public static void main(String[] args) throws Exception {

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

//        System.out.println(forkJoinPool.invokeAny(callables));
//        forkJoinPool.shutdown();
//        System.out.println(staticThreads.invokeAny(callables));
//        staticThreads.shutdown();
//        System.out.println(dinamicThreads.invokeAny(callables));
//        dinamicThreads.shutdown();

//        System.out.println(singleThread.invokeAll(callables));
//        singleThread.shutdown();
//        if (singleThread.awaitTermination(3, TimeUnit.MILLISECONDS)){
//            System.out.println("Не все задания успели завершиться");
//            singleThread.shutdownNow(); // прерываем не дожидаясь
//        }

//        forkJoinPool.invokeAll(callables)
//                .stream()
//                .map(future -> {
//                    try {
//                        return future.get();
//                    } catch (Exception e) {
//                        throw new IllegalStateException(e);
//                    }
//                })
//                .forEach(System.out::println);

//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "task1", singleThread);
//        System.out.println(future.get());
//        future = future.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " what?"));
//        System.out.println(future.get());
//        singleThread.shutdown();

        schedule();
    }

    private static void schedule() {
        System.out.println("начали...");
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(); // Executors.newScheduledThreadPool(1)
        service.schedule(() -> System.out.println("отложенное действие"), 2, TimeUnit.SECONDS);
//        service.scheduleAtFixedRate(() -> // еще можно whith вместо at
//                System.out.println("отложенное действие"), 0, 2, TimeUnit.SECONDS);
        service.shutdown();
    }
}
