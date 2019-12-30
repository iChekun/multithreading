package by.trjva.chekun.model.entity;

import by.trjva.chekun.model.action.MatrixAction;
import by.trjva.chekun.model.program.WorkProgram;
import by.trjva.chekun.printer.Printer;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class WorkerHandler implements Callable<WorkerResult> {

    private int threadName;

    public WorkerHandler(int threadName) {
        this.threadName = threadName;
    }


    @Override
    public WorkerResult call() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignore) {
            Printer.print(ignore);
        }

        Printer.print("THREAD NUMBER  " + threadName + "  STARTED");
        //WorkerResult result  =
        WorkProgram.getInstance().workProgram(threadName);


        return new WorkerResult();
    }


}
