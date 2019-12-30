package by.trjva.chekun.model.state;

import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.state.exception.WrongActionException;

public class ChangeCellInDiagonalState implements MatrixState {

    private static final ChangeCellInDiagonalState instance = new ChangeCellInDiagonalState();

    private ChangeCellInDiagonalState() {

    }

    public static ChangeCellInDiagonalState getInstance() {
        return instance;
    }

    @Override
    public void previous(Matrix matrix) throws WrongActionException {
        throw new WrongActionException("No previous state for ChangeCellInDiagonalState State");
    }

    @Override
    public void next(Matrix matrix) {
        matrix.setMatrixState(ChangeCellNextToDiagonalElementState.getInstance());
    }

    @Override
    public String getStatus(Matrix matrix) {
        return "change cell in diagonal state";
    }
}
