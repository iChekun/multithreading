package by.trjva.chekun.model.state;

import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.state.exception.WrongActionException;

public interface MatrixState {

    void previous(Matrix matrix) throws WrongActionException;

    void next(Matrix matrix) throws WrongActionException;


    String getStatus(Matrix matrix);
}
