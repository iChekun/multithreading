package by.trjva.chekun.model.state;

import by.trjva.chekun.model.entity.Matrix;

public class CalculateSumInLineState implements MatrixState {

    private static final CalculateSumInLineState instance = new CalculateSumInLineState();

    private CalculateSumInLineState() {

    }

    public static CalculateSumInLineState getInstance() {
        return instance;
    }

    @Override
    public void previous(Matrix matrix) {
        matrix.setMatrixState(ChangeCellNextToDiagonalElementState.getInstance());
    }

    @Override
    public void next(Matrix matrix) {
        matrix.setMatrixState(CalculateSumInColumnState.getInstance());
    }

    @Override
    public String getStatus(Matrix matrix) {
        return "calculate sum in line";
    }
}
