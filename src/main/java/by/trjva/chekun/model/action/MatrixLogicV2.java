package by.trjva.chekun.model.action;

import by.trjva.chekun.model.action.exception.MatrixLogicException;
import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.state.exception.WrongActionException;
import by.trjva.chekun.model.state.ChangeCellNextToDiagonalElementState;
import by.trjva.chekun.printer.Printer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixLogicV2 {

    private static MatrixLogicV2 instance = new MatrixLogicV2();


    private MatrixLogicV2() {
    }

    public static MatrixLogicV2 getInstance() {
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


    public int work(int threadName) throws MatrixLogicException {


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


    private void moveMatrixToNextStatus() throws WrongActionException {
        Printer.print("Go to next stage: Current  " + matrix.getStatus());
        matrix.nextState();
    }





}
