package by.trjva.chekun.model.state;

import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.state.exception.WrongActionException;

public class CalculateSumInColumnState implements MatrixState {


    private static final CalculateSumInColumnState instance = new CalculateSumInColumnState();

    private CalculateSumInColumnState() {

    }

    public static CalculateSumInColumnState getInstance() {
        return instance;
    }

    @Override
    public void previous(Matrix matrix)  {
        matrix.setMatrixState(CalculateSumInLineState.getInstance());
    }

    @Override
    public void next(Matrix matrix) throws WrongActionException {
        throw new WrongActionException("No next state for CalculateSumInColumnState State");
    }

    @Override
    public String getStatus(Matrix matrix) {
        return "calculate sum in column";
    }
}
