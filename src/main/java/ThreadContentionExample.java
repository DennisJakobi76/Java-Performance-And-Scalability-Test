import java.util.concurrent.locks.ReentrantLock;

public class ThreadContentionExample {

    private static final ReentrantLock lock = new ReentrantLock();
    private static int counter = 0;

    public static void main(String[] args) {

        Runnable task = () -> {

            for (int i = 0; i < 5; i++){
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + " is incrementing the counter.");
                    counter++;
                    Thread.sleep(100L);
                }
                catch (InterruptedException e ){
                    Thread.currentThread().interrupt();
                }
                finally {
                    System.out.println(Thread.currentThread().getName() + " is releasing the lock.");
                    lock.unlock();
                }

            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();


    }

}
