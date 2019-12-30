package by.trjva.chekun.model.action;

import by.trjva.chekun.model.action.exception.MatrixLogicException;
import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.state.exception.WrongActionException;
import by.trjva.chekun.model.state.CalculateSumInLineState;
import by.trjva.chekun.printer.Printer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixLogicCalculation {


    //Далее каждый поток должен посчитать сумму всех элементов в строке и столбце с номером своей диагонали.
    private static MatrixLogicCalculation instance = new MatrixLogicCalculation();


    private MatrixLogicCalculation() {
    }

    public static MatrixLogicCalculation getInstance() {
        return instance;
    }

    private int matrixSize;
    private Matrix matrix;
    private Lock lock = new ReentrantLock();

    public void init(Matrix matrix) {
        this.matrix = matrix;
        this.matrixSize = matrix.getMatrixSize();
    }


    public void work(int threadName) throws MatrixLogicException {

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Printer.print(e);
        }
        try {
            lock.lock();
            Printer.print("\n---LOCK---");
            Printer.print("Starting CALCULATION with THREAD NAME " + threadName);
            if (threadName >= matrixSize) {
                Printer.print("Can`t calculate sum cause number of treadName is bigger than matrix size -> " + threadName + ">=" + matrixSize);
            } else {
                matrix.setMatrixState(CalculateSumInLineState.getInstance());
                //1
                calculateSumInLine(threadName);
                //2
                calculateSumInColumn(threadName);
            }
        } catch (WrongActionException e) {
            throw new MatrixLogicException("Can`t calculate sum, cause  " + e.getMessage());
        } finally {
            Printer.print("---UNLOCK---\n");
            lock.unlock();
        }
    }

    private void calculateSumInLine(int threadName) throws WrongActionException {

        Printer.print("Find in line with address  = " + threadName);
        int sum = 0;
        for (int j = 0; j < matrixSize; j++) {
            sum += matrix.getValueByCoordinates(threadName, j);
        }
        Printer.print(" TREAD WITH NAME = " + threadName + " HAS SUM IN LINE  =  " + sum);

        moveMatrixToNextStatus();
    }

    private void calculateSumInColumn(int threadName) throws WrongActionException {
        int sum = 0;
        Printer.print("Find in column  with address  = " + threadName);
        for (int j = 0; j < matrixSize; j++) {
            sum += matrix.getValueByCoordinates(j, threadName);
        }
        Printer.print(" TREAD WITH NAME = " + threadName + " HAS SUM IN COLUMN  =  " + sum);

        moveMatrixToNextStatus();
    }

    private void moveMatrixToNextStatus() throws WrongActionException {
        Printer.print("Go to next stage: Current " + matrix.getStatus());
        matrix.nextState();
    }

}
