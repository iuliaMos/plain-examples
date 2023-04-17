import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo2 {

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();

        double[] nums = new double[5000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) (((i % 2) == 0) ? i : -i);
        }

        SumTask task = new SumTask(nums, 0, nums.length);
        double summation = fjp.invoke(task);

        System.out.println("Summation " + summation);
    }
}

class SumTask extends RecursiveTask<Double> {
    final int seqThreshold = 500;
    double[] data;
    int start, end;

    public SumTask(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        double sum = 0;

        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
        } else {
            int middle = (start + end) / 2;

            SumTask t1 = new SumTask(data, start, middle);
            SumTask t2 = new SumTask(data, middle, end);

            t1.fork();
            t2.fork();

            sum = t1.join() + t2.join();
        }

        return sum;
    }
}
