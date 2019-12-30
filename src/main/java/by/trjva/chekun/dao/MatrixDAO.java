package by.trjva.chekun.dao;

import by.trjva.chekun.dao.exception.DAOException;
import by.trjva.chekun.model.entity.Matrix;

public interface MatrixDAO {

    public Matrix readMatrixParameters() throws DAOException;
}
