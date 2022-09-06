package CallableSummator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MatrixServiceCall {

    private volatile int summa = 0;

    public int sum(int[][] matrix, int nThreads) {

        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        List<Future<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < nThreads; i++) {
            tasks.add(executorService.submit(new ColumnSummatorCall(matrix, nThreads, i)));
        }

        executorService.shutdown();

        for (Future<Integer> task : tasks) {
            try {
                summa += task.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }

        return summa;
    }

}
