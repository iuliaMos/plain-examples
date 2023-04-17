import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new EndBarAction());

        System.out.println("Starting");

        new Thread(new WorkerThread(cb, "A")).start();
        new Thread(new WorkerThread(cb, "B")).start();
        new Thread(new WorkerThread(cb, "C")).start();
        new Thread(new WorkerThread(cb, "X")).start();
        new Thread(new WorkerThread(cb, "Y")).start();
        new Thread(new WorkerThread(cb, "Z")).start();
    }
}

class WorkerThread implements Runnable {

    CyclicBarrier cb;
    String name;

    WorkerThread(CyclicBarrier cb, String name) {
        this.cb = cb;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);

        try {
            this.cb.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EndBarAction implements Runnable {

    @Override
    public void run() {
        System.out.println("Barrier Reached!");
    }
}
