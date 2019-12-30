package by.trjva.chekun.model.service.impl;

import by.trjva.chekun.dao.MatrixDAO;
import by.trjva.chekun.dao.WorkerHandlerDAO;
import by.trjva.chekun.dao.exception.DAOException;
import by.trjva.chekun.dao.impl.MatrixDAOImpl;
import by.trjva.chekun.dao.impl.WorkerHandlerDAOImpl;
import by.trjva.chekun.model.action.MatrixAction;
import by.trjva.chekun.model.action.MatrixLogic;
import by.trjva.chekun.model.action.MatrixLogicV2;
import by.trjva.chekun.model.action.MatrixLogicCalculation;
import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.program.WorkersController;
import by.trjva.chekun.model.service.Service;
import by.trjva.chekun.model.service.exception.ServiceException;
import by.trjva.chekun.printer.Printer;

import java.util.concurrent.*;


public class ServiceImpl implements Service {


    private Matrix matrix;

    private Matrix initMatrix(String path) throws ServiceException {
        try {
            MatrixDAO matrixDAO = new MatrixDAOImpl(path);
            final Matrix matrix = matrixDAO.readMatrixParameters();
            return matrix;
        } catch (DAOException e) {
            throw new ServiceException("Can`t make matrix cause: ", e);
        }

    }

    private int initThreads(String path) throws ServiceException {
        try {
            WorkerHandlerDAO workerHandlerDAO = new WorkerHandlerDAOImpl(path);
            final int workersCount = workerHandlerDAO.readWorkersCount();
            return workersCount;
        } catch (DAOException e) {
            throw new ServiceException("Can`t make matrix cause: ", e);
        }
    }

    @Override
    public void startWorking(String matrixFilePath, String threadFilePath) throws ServiceException {

        int workersCount = initThreads(threadFilePath);
        matrix = initMatrix(matrixFilePath);
        int matrixSize = matrix.getMatrixSize();

        MatrixAction.getInstance().setMatrix(matrix);

//        MatrixLogicV2 logic = MatrixLogicV2.getInstance();
//        logic.init(matrix);
//
//        MatrixLogicCalculation calculationLogic = MatrixLogicCalculation.getInstance();
//        calculationLogic.init(matrix);

        MatrixLogic logic = MatrixLogic.getInstance();
        logic.init(matrix);

        CountDownLatch downLatch = new CountDownLatch(1);

        WorkersController workersController = new WorkersController(workersCount, matrixSize, downLatch);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(workersController);
        //
        while (downLatch.getCount() > 0) {
            {
                Printer.print("\nMAIN THREAD GO SLEEP!");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    Printer.print(e);
                }
                Printer.print("\nMAIN THREAD WAKE UP!");
            }
        }
        //
        Printer.print("\n\nMAIN THREAD can continue!!\n\n");
        executorService.shutdown();

    }

}
