import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(new LockThread(lock, "A")).start();
        new Thread(new LockThread(lock, "B")).start();
    }
}

class Shared {
    static int count = 0;
}

class LockThread implements Runnable {
    ReentrantLock lock;
    String name;

    public LockThread(ReentrantLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Staring " + this.name);

        try {
            System.out.println(this.name + " is waiting to lock count.");
            lock.lock();
            System.out.println(this.name + " is locking count.");

            Shared.count ++;
            System.out.println(this.name + " : " + + Shared.count);

            System.out.println(this.name + " is sleeping.");

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(this.name + " is unlocking count.");
            this.lock.unlock();
        }
    }
}
