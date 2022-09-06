package RunnableSummator;

import java.util.ArrayList;
import java.util.List;

public class ColumnSummatorRun implements Runnable {

    private int summOfColumns;

    private final MatrixServiceRun matrixService;
    private final int[][] matrix;
    private final List<Integer> numberColumnsForCount = new ArrayList<>();

    public ColumnSummatorRun(MatrixServiceRun matrixService, int[][] matrix, int nThreads, int columnSummatorId) {
        this.matrixService = matrixService;
        this.matrix = matrix;

        for (int i = 0; i < matrix[0].length; i++) {
            if (i % nThreads == columnSummatorId) {
                numberColumnsForCount.add(i);
            }
        }
    }

    @Override
    public void run() {

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

        matrixService.setSumma(matrixService.getSumma() + summOfColumns);

    }
}
