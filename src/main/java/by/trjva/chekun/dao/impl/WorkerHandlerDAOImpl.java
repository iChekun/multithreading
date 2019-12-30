package by.trjva.chekun.dao.impl;

import by.trjva.chekun.dao.WorkerHandlerDAO;
import by.trjva.chekun.dao.exception.DAOException;
import by.trjva.chekun.model.entity.Matrix;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WorkerHandlerDAOImpl implements WorkerHandlerDAO {


    private final String filePath;

    public WorkerHandlerDAOImpl(String path) {
        this.filePath = path;
    }


    @Override
    public int readWorkersCount() throws DAOException {
        try {
            final InputStream fileInputStream = new FileInputStream(filePath);

            final Properties properties = new Properties();

            properties.load(fileInputStream);

            final int threads_count = Integer.parseInt(properties.getProperty("threads_count"));

            return threads_count;
        } catch (NumberFormatException | IOException e) {
            throw new DAOException("Can`t load workerParameters!: ", e);
        }
    }
}
