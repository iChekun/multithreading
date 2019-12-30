package by.trjva.chekun.model.action;

import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.printer.Printer;


public class MatrixAction {

    private static final MatrixAction instance = new MatrixAction();

    public static MatrixAction getInstance() {
        return instance;
    }

    private MatrixAction() {

    }

    private Matrix matrix;

    private static final String STRING_FORMAT = "%5d";


//    public void fillMatrix() {
//
//        int[][] mas = new int[matrix.getMatrixSize()][matrix.getMatrixSize()];
//         Random random = new Random();
//        for (int i = 0; i < matrix.getMatrixSize(); i++) {
//            for (int j = 0; j < matrix.getMatrixSize(); j++) {
//                mas[i][j] = random.nextInt(25 - 2);
//            }
//        }
//        matrix.setMatrix(mas);
//    }


    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public void showMatrix() {
        Printer.print("------------------------");
        int[][] mas = matrix.getMatrix();

        for (int i = 0; i < matrix.getMatrixSize(); i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < matrix.getMatrixSize(); j++) {
                // System.out.printf("%4d ", mas[i][j]);
                line.append(String.format(STRING_FORMAT, mas[i][j]));
            }
            Printer.print(line);
        }
        Printer.print("------------------------");
    }
}
