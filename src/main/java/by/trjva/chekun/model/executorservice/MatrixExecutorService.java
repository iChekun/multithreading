package by.trjva.chekun.model.executorservice;

import by.trjva.chekun.model.entity.WorkerHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixExecutorService {

    private ExecutorService executorService;

    public void init(int count) {
        System.out.println("ПОТОКОВ БУДЕТЬ::: "+ count);
        executorService = Executors.newFixedThreadPool(count);

    }


    public ExecutorService getExecutorService() {
        return executorService;
    }


    public void shutdownService() {
        executorService.shutdown();
    }
}

