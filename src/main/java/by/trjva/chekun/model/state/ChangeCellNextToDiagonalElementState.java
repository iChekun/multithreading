package by.trjva.chekun.model.state;

import by.trjva.chekun.model.entity.Matrix;

public class ChangeCellNextToDiagonalElementState implements MatrixState {

    private static final ChangeCellNextToDiagonalElementState instance = new ChangeCellNextToDiagonalElementState();

    private ChangeCellNextToDiagonalElementState() {

    }

    public static ChangeCellNextToDiagonalElementState getInstance() {
        return instance;
    }


    @Override
    public void previous(Matrix matrix) {
        matrix.setMatrixState(ChangeCellInDiagonalState.getInstance());
    }

    @Override
    public void next(Matrix matrix) {
        matrix.setMatrixState(CalculateSumInLineState.getInstance());
    }

    @Override
    public String getStatus(Matrix matrix) {
        return "change sell in line or column near diagonal element";
    }
}
