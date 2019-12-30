package by.trjva.chekun.dao.impl;

import by.trjva.chekun.dao.MatrixDAO;
import by.trjva.chekun.dao.exception.DAOException;
import by.trjva.chekun.model.entity.Matrix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MatrixDAOImpl implements MatrixDAO {

    private final String filePath;

    public MatrixDAOImpl(String path) {
        this.filePath = path;
    }

    @Override
    public Matrix readMatrixParameters() throws DAOException {
        try {
            final InputStream fileInputStream = new FileInputStream(filePath);

            final Properties properties = new Properties();

            properties.load(fileInputStream);

            final int matrixSize = Integer.parseInt(properties.getProperty("size"));

            Matrix matrix = new Matrix(matrixSize);

            return matrix;
        } catch (NumberFormatException | IOException e) {
            throw new DAOException("Can`t load matrix data!: ", e);
        }
    }
}
