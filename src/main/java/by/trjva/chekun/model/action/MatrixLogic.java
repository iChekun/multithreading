package by.trjva.chekun.model.action;

import by.trjva.chekun.model.action.exception.MatrixLogicException;
import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.state.CalculateSumInLineState;
import by.trjva.chekun.model.state.ChangeCellNextToDiagonalElementState;
import by.trjva.chekun.model.state.exception.WrongActionException;
import by.trjva.chekun.printer.Printer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixLogic {
    private static MatrixLogic instance = new MatrixLogic();


    private MatrixLogic() {
    }

    public static MatrixLogic getInstance() {
        return instance;
    }


    private static Random random = new Random();

    private Matrix matrix;
    private Lock lock = new ReentrantLock();
    private int matrixSize;

    public void init(Matrix matrix) {
        this.matrix = matrix;
        this.matrixSize = matrix.getMatrixSize();
    }


    public int workWithReplace(int threadName) throws MatrixLogicException {


        try {

            TimeUnit.SECONDS.sleep(2);
            lock.lock();
            Printer.print("\n--- LOCK --- ");
            Printer.print("HERE THREAD WITH NAME  = " + threadName);

            //1
            final int cellPosition = getCellPosition();
            //2
            writeThreadNameInMatrixDiagonal(cellPosition, threadName + 1);

            //3
            changeValueInColumnOrLine(cellPosition);

            return cellPosition;
        } catch (WrongActionException | InterruptedException e) {
            throw new MatrixLogicException("Can`t make change operation,cause " + e.getMessage());
        } finally {
            Printer.print(" ---UNLOCK---\n");
            matrix.setMatrixState(ChangeCellNextToDiagonalElementState.getInstance());
            lock.unlock();
        }
    }


    private int getCellPosition() {
        int matrixSize = matrix.getMatrixSize();
        ///тут мы получаеам нужную клетку из диагонали куда поток должен записать число
        int numberOfMatrixCellToChange = random.nextInt(matrixSize);
        Printer.print("POSITION TO CELL IN DIAGONAL : " + numberOfMatrixCellToChange);
        return numberOfMatrixCellToChange;
    }

    private void writeThreadNameInMatrixDiagonal(int cellPositionToChange, int threadName) throws WrongActionException {


        Printer.print("Coordinate in main diagonal :  " + cellPositionToChange + ". " + cellPositionToChange);
        //System.out.println("We have cell to write, current value is : " + matrix.getValueByCoordinates(cellPositionToChange, cellPositionToChange));
        // System.out.println("---SET IN DIAGONAL---");
        int newValue = threadName;//random.nextInt(500);
        matrix.setByCoordinates(cellPositionToChange, cellPositionToChange, newValue);
        Printer.print("after value is : " + matrix.getValueByCoordinates(cellPositionToChange, cellPositionToChange));

        moveMatrixToNextStatus();
    }


    private void changeValueInColumnOrLine(int cellAddress) throws WrongActionException {

        Printer.print("-SET VALUE IN COLUMN OR LINE-");
        int changePosition = getChangePosition(cellAddress);

        Printer.print("change position: " + cellAddress + "." + changePosition);
        //  System.out.println("Current value here: " + matrix.getValueByCoordinates(cellAddress, changePosition));

        int newRandomValue = random.nextInt(335 - 5);
        Printer.print("New random value is : " + newRandomValue);

        matrix.setByCoordinates(cellAddress, changePosition, newRandomValue);

        moveMatrixToNextStatus();
    }

    private int getChangePosition(int cellAddressInDiagonal) {
        int result = 0;
        boolean notFound = true;

        while (notFound) {
            notFound = false;
            result = random.nextInt(matrixSize);
            if (result == cellAddressInDiagonal) {
                notFound = true;
            }
        }

        return result;
    }







    public void workWithCalculation(int threadName) throws MatrixLogicException {

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
