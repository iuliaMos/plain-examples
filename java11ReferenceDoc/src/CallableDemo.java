import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        Future<Integer> f1;
        Future<Double> f2;
        Future<Integer> f3;

        System.out.println("Starting");
        f1 = es.submit(new Sum(10));
        f2 = es.submit(new Hypot(3, 4));
        f3 = es.submit(new Factorial(5));

        try {
            System.out.println("Sum: " + f1.get());
            System.out.println("Hypotenuse: " + f2.get());
            System.out.println("Factorial: " + f3.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        es.shutdown();
        System.out.println("Done");
    }
}

class Sum implements Callable<Integer> {
    int stop;

    Sum(int v) {
        this.stop = v;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < stop; i++) {
            sum += i;

        }
        return sum;
    }
}

class Hypot implements Callable<Double> {
    double side1, side2;

    Hypot(double d1, double d2) {
        this.side1 = d1;
        this.side2 = d2;
    }

    @Override
    public Double call() throws Exception {
        return Math.sqrt(side1 * side1 + side2 * side2);
    }
}

class Factorial implements Callable<Integer> {
    int stop;

    Factorial(int v) {
        this.stop = v;
    }

    @Override
    public Integer call() throws Exception {
        int fact = 1;
        for (int i = 2; i < this.stop; i++) {
            fact *= i;
        }
        return fact;
    }
}
