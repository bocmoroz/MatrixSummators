package RunnableSummator;

public class RunnableMain {
    public static void main(String[] args) {

        MatrixServiceRun matrixServiceRun = new MatrixServiceRun();

        int[][] matrix = new int[10][10];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }

        int summa = matrixServiceRun.sum(matrix, 6);

        System.out.println("--------------------------");

        System.out.println("Result with MatrixService: " + summa);

        System.out.println("--------------------------");

        int summaCheck = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                summaCheck += matrix[i][j];
            }
        }

        System.out.println("For check: " + summaCheck);

        System.out.println("--------------------------");
    }
}
