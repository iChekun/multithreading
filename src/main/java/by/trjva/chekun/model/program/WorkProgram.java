package by.trjva.chekun.model.program;

import by.trjva.chekun.model.action.MatrixLogic;
import by.trjva.chekun.model.action.MatrixLogicV2;
import by.trjva.chekun.model.action.MatrixLogicCalculation;
import by.trjva.chekun.model.action.exception.MatrixLogicException;
import by.trjva.chekun.printer.Printer;

import java.util.concurrent.TimeUnit;


public class WorkProgram {

    private static WorkProgram instance = new WorkProgram();

    public static WorkProgram getInstance() {
        return instance;
    }

    private WorkProgram() {

    }


    public void workProgram(int name) {

        try {
//            Printer.print("THREAD NUMBER " + name + " TRY TO START WORKING");
//            int cellPositionToCalculateSum = MatrixLogicV2.getInstance().work(name);
//
//            sleep(50);
//
//            Printer.print("\nTHREAD NUMBER " + name + " STARTED CALCULATE SUM IN ROW");
//            MatrixLogicCalculation.getInstance().work(cellPositionToCalculateSum);


            Printer.print("THREAD NUMBER " + name + " TRY TO START WORKING");
            int cellPositionToCalculateSum = MatrixLogic.getInstance().workWithReplace(name);

            sleep(50);

            Printer.print("\nTHREAD NUMBER " + name + " TRY TO CALCULATE SUM IN ROW");
            MatrixLogic.getInstance().workWithCalculation(cellPositionToCalculateSum);
        } catch (MatrixLogicException e) {
            Printer.print("Can`t do work in thread with name = " + name + ", cause:  " + e.getMessage());
        }
    }

    private void sleep(int sleepTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            Printer.print(e);
        }
    }
}
