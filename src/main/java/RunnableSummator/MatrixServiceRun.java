package RunnableSummator;

public class MatrixServiceRun {

    private boolean valueGet = false;

    private volatile int summa;

    public int sum(int[][] matrix, int nThreads) {

        for (int i = 0; i < nThreads; i++) {
            new Thread(new ColumnSummatorRun(this, matrix, nThreads, i)).start();
        }

        while (checkChanges()) {
        }

        return summa;
    }

    private boolean checkChanges() {
        int currSumm = summa;

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return currSumm == summa;
    }

    public synchronized int getSumma() {
        while (valueGet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        valueGet = true;
        notify();
        return summa;
    }

    public synchronized void setSumma(int summa) {
        while (!valueGet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        this.summa = summa;
        valueGet = false;
        notify();
    }
}