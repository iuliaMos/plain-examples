import java.util.concurrent.Semaphore;

public class ProdConsSemaphore {

    public static void main(String[] args) {
        Q q = new Q();

        new Thread(new Consumer(q), "Consumer").start();
        new Thread(new Producer(q), "Producer").start();
    }
}

class Q {
    int n;
    static Semaphore semCons = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);

    void get() {
        try {
            semCons.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Got: " + n);
        semProd.release();
    }

    void put(int n) {
        try {
            semProd.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.n = n;
        System.out.println("Put: " + n);
        semCons.release();
    }
}

class Producer implements Runnable {
    Q q;

    Producer(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) this.q.put(i);
    }
}

class Consumer implements Runnable {
    Q q;

    Consumer(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) this.q.get();
    }
}
