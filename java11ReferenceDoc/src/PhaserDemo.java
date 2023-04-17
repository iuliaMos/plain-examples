import java.util.concurrent.Phaser;

public class PhaserDemo {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        int currPhase = 0;

        System.out.println("Starting");

        new Thread(new WorkerPhaserThread(phaser, "A")).start();
        new Thread(new WorkerPhaserThread(phaser, "B")).start();
        new Thread(new WorkerPhaserThread(phaser, "C")).start();

        currPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currPhase + " Complete");

        currPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currPhase + " Complete");

        currPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currPhase + " Complete");

        phaser.arriveAndDeregister();

        if(phaser.isTerminated()) {
            System.out.println("The Phaser is terminated");
        }
    }
}

class WorkerPhaserThread implements Runnable {
    Phaser phaser;
    String name;

    WorkerPhaserThread(Phaser p, String name) {
        this.phaser = p;
        this.name = name;
        this.phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Thread " + this.name + " Beginning Phase One");
        this.phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + this.name + " Beginning Phase Two");
        this.phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + this.name + " Beginning Phase Three");
        this.phaser.arriveAndDeregister();
    }
}
