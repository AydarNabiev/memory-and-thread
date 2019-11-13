package memory.atomicity;

import java.util.concurrent.CountDownLatch;


/**
 * Non thread-safe class that have a read-modify-write memory.race condition.
 *
 * Поле number должно увеличиваьтся каждый раз для каждого потока
 * Однако из за наличия гонки некоторые обновления могут быть потеряны
 * что приведет к неверным результатом
 * Гонка более выражена по причине одновременного старта всех потоков startSignal.countDown();
 */
public class UnsafeReadModifyWriteWithLatch {
	private static final int NUM_THREADS = 1_000;
	private int number;
	private final CountDownLatch startSignal = new CountDownLatch(1);// Latch на одно CountDown для продолжения
	private final CountDownLatch endSignal = new CountDownLatch(NUM_THREADS); // Latch на 1000 CountDown
	
	public void incrementNumber() {
		number++;
	}
	
	public int getNumber() {
		return number;
	}
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			test();
		}
	}
	
	public static void test() throws InterruptedException {
		final UnsafeReadModifyWriteWithLatch rmw = new UnsafeReadModifyWriteWithLatch();
		
		for (int i = 0; i < NUM_THREADS; i++) { // Создаем 1000 потоков
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						rmw.startSignal.await(); // каждый из которых паркуется в ожидании startSignal.countDown()
						rmw.incrementNumber();
					} catch (InterruptedException e) { 
						
					} finally {
						rmw.endSignal.countDown(); // по окончанию каждого оповестить счетчик
					}
					
				}
			}, "T" + i).start();
		}
		
//		Thread.sleep(2000);
		rmw.startSignal.countDown(); // Всем стартовать
		rmw.endSignal.await(); // Ждем когда все потоки завершаться
		System.out.println("Final number (should be 1_000): " + rmw.getNumber());
	}
}
