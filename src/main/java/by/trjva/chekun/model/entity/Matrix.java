package by.trjva.chekun.model.entity;

import by.trjva.chekun.model.state.exception.WrongActionException;
import by.trjva.chekun.model.state.ChangeCellNextToDiagonalElementState;
import by.trjva.chekun.model.state.MatrixState;



public class Matrix {

    private MatrixState matrixState;

    private int[][] matrix;
    private int size;


    public Matrix(int size) {
        this.size = size;
        matrix = new int[size][size];
        matrixState = ChangeCellNextToDiagonalElementState.getInstance();
    }

    public void nextState() throws WrongActionException {
        matrixState.next(this);
    }

    public void setMatrixState(MatrixState matrixState) {
        this.matrixState = matrixState;
    }

    public void setByCoordinates(int line, int column, int value) {
        matrix[line][column] = value;
    }

    public int getValueByCoordinates(int line, int column) {
        return matrix[line][column];
    }


    public int getMatrixSize() {
        return size;
    }

    public int[][] getMatrix() {
        return matrix;
    }


    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }


    public String getStatus() {
        return "Status: " + matrixState.getStatus(this);
    }


}
