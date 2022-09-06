package CallableSummator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ColumnSummatorCall implements Callable<Integer> {

    private int summOfColumns;

    private final int[][] matrix;
    private final List<Integer> numberColumnsForCount = new ArrayList<>();

    public ColumnSummatorCall(int[][] matrix, int nThreads, int columnSummatorId) {
        this.matrix = matrix;

        for (int i = 0; i < matrix[0].length; i++) {
            if (i % nThreads == columnSummatorId) {
                numberColumnsForCount.add(i);
            }
        }
    }

    @Override
    public Integer call() {

        String nameOfThread = Thread.currentThread().getName();

        System.out.println(nameOfThread + " started count summ");

        for (int i = 0; i < matrix[0].length; i++) {
            if (numberColumnsForCount.contains(i)) {
                for (int j = 0; j < matrix.length; j++) {
                    summOfColumns += matrix[j][i];
                }
            }
        }

        System.out.println(nameOfThread + " ended count summ");

        return summOfColumns;
    }
}
