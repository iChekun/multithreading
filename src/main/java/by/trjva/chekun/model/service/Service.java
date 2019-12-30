package by.trjva.chekun.model.service;

import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.service.exception.ServiceException;

public interface Service {

//    public void initMatrix(String path) throws ServiceException;
//
//    public void initThreads(String path) throws ServiceException;

    public void startWorking(String matrixFilePath, String threadFilePath) throws ServiceException;

}
