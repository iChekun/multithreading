package by.trjva.chekun.model.program;

import by.trjva.chekun.model.action.MatrixAction;
import by.trjva.chekun.model.entity.WorkerHandler;
import by.trjva.chekun.model.executorservice.MatrixExecutorService;
import by.trjva.chekun.model.namemaker.ThreadNameMaker;
import by.trjva.chekun.printer.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkersController implements Runnable {

    private int workersCount;
    private int matrixSize;
    private final CountDownLatch downLatch;


    public WorkersController(int workersCount, int matrixSize, CountDownLatch downLatch) {
        this.workersCount = workersCount;
        this.matrixSize = matrixSize;
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        int threadsCount = workersCount * matrixSize;

        final List<WorkerHandler> workerHandlers = new ArrayList<>(threadsCount);
        makeWorkers(workerHandlers, threadsCount);

        final int delimiter = threadsCount / workersCount;
        final int sleepTime = threadsCount;
        MatrixExecutorService matrixExecutorService = new MatrixExecutorService();

        Printer.print("All threads = " + threadsCount);
        Printer.print("Count in wave =  " + delimiter);

        for (int readyThreadsToWork = delimiter; readyThreadsToWork <= threadsCount; readyThreadsToWork += delimiter) {

            System.out.println("\n\n\n  " + readyThreadsToWork + "  ПОТОКВ СЕЙЧАС \n\n\n");
            matrixExecutorService.init(delimiter);
            submitWorkers(matrixExecutorService, workerHandlers);

            sleep(delimiter + workersCount);


            Printer.print("\nMATRIX AFTER  " + readyThreadsToWork + " THREADS HAVE WORKED WITH IT");
            MatrixAction.getInstance().showMatrix();
            matrixExecutorService.shutdownService();
        }

        sleep(sleepTime);

        downLatch.countDown();
    }

    private void sleep(int delimiter) {
        try {
            TimeUnit.SECONDS.sleep(delimiter);
        } catch (InterruptedException e) {
            Printer.print(e);
        }
    }


    private void makeWorkers(List<WorkerHandler> workerHandlers, int workersCount) {
        for (int i = 0; i < workersCount; i++) {
            final int threadName = ThreadNameMaker.getInstance().getUniqueName();
            workerHandlers.add(new WorkerHandler(threadName));
        }
    }

    private void submitWorkers(MatrixExecutorService matrixExecutorService, List<WorkerHandler> workerHandlers) {
        workerHandlers.forEach(c -> {
            ExecutorService executorService = matrixExecutorService.getExecutorService();
            executorService.submit(c);
        });
    }
}
