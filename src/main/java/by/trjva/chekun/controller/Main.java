package by.trjva.chekun.controller;


import by.trjva.chekun.model.action.MatrixAction;
import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.service.Service;
import by.trjva.chekun.model.service.exception.ServiceException;
import by.trjva.chekun.model.service.impl.ServiceImpl;
import by.trjva.chekun.printer.Printer;

/**
 * Матрица. Создано Y*N потоков.
 * Инициализирована целочисленная матрица размерности NxN.
 * Именем каждого потока является некоторое уникальное целое число.
 * <p>
 * Каждый поток записывает в диагональ матрицы свое имя-число
 * и одновременно изменяет один из элементов строки или столбца в котором находится изменяемый диагональный элемент.
 * <p>
 * Только один поток может изменить конкретный элемент стоящий на диагонали и элемент в его строке или столбце.
 * <p>
 * Далее каждый поток должен посчитать сумму всех элементов в строке и столбце с номером своей диагонали.
 * <p>
 * После того как отработают первые N потоков производится запись матрицы и результатов вычисления в файл, и к работе с матрицей допускаются следующие N потоков.
 */
public class Main {

    /**
     * 1 потом записывает в ячейку в диаогональи число
     * 2 меняет значение строки или столбца в котором находится элемент в который записывал число
     * 3 каждый поток считывает сумму всех элементов  в строке и в стогобце с номером диагонали
     */

    public static void main(String[] argc) throws InterruptedException {

//        Matrix matrix = new Matrix(6);
//
//        MatrixAction matrixAction = new MatrixAction(matrix);
//
//
////
//        matrixAction.showMatrix();
////
//        System.out.println("------------------------");


//        MatrixLogicV2 logic = MatrixLogicV2.getInstance();
//        logic.init(matrix);
//
//        MatrixLogicCalculation calculationLogic = MatrixLogicCalculation.getInstance();
//        calculationLogic.init(matrix);
//
//
//        MatrixExecutorService matrixExecutorService = new MatrixExecutorService();
//
//        matrixExecutorService.init(9);


//        List<WorkerHandler> workerHandlers = new ArrayList<>();
//
//        for (int i = 0; i < 6; i++) {
//            int threadName= ThreadNameMaker.getInstance().getUniqueName();
//            workerHandlers.add(new WorkerHandler(threadName));
//        }
//
//
//
//               workerHandlers.forEach(c -> {
//
//            ExecutorService executorService = matrixExecutorService.getExecutorService(c);
//            executorService.submit(c);
//
//
//        });
//
////        workerHandlers.forEach(c -> {
////            ExecutorService executorService = matrixExecutorService.getExecutorService(c);
////            executorService.submit(c);
////
////        });
//
//
//        matrixExecutorService.shutdownService();
//
//        try {
//            TimeUnit.SECONDS.sleep(8);
//        } catch (InterruptedException e) {
//        }
//        matrixAction.showMatrix();


        Service service = new ServiceImpl();

        String matrixPath = "src/main/resources/matrix_data.properties";
        String workersPath = "src/main/resources/threads_workers_data.properties";

        try {
            service.startWorking(matrixPath, workersPath);
        } catch (ServiceException e) {
            Printer.print(e);
        }





         MatrixAction.getInstance().showMatrix();
    }


}
