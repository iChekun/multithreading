package by.trjva.chekun.dao;

import by.trjva.chekun.model.entity.Matrix;
import by.trjva.chekun.model.entity.WorkerResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WorkerHadlerWriter {

    private static final String SPACE = "\n";
    private static final String filePath ="../src/main/resources/file.txt";
    private final Path workFilePath=   Paths.get(filePath);

    public void save(Matrix matrix, WorkerResult result) {

        if (isFileCorrect()) {
            try {
                Files.write(workFilePath, "".concat(SPACE).getBytes(UTF_8), StandardOpenOption.WRITE,
                        StandardOpenOption.APPEND,
                        StandardOpenOption.SYNC);
            } catch (NullPointerException | IOException e) {
               // throw new WriteDAOException("Can`t save trip! " + e.getMessage());
            }
//            try (FileWriter fileWriter = new FileWriter(workFilePath.toFile(),true)) {
//                fileWriter.write(trip.concat("\n"));
//            } catch (NullPointerException | IOException e) {
//                throw new WriteDAOException("Can`t save trip! " + e.getMessage());
//            }
        } else {
           // throw new NoDataStorageDAOException("no file exc");
        }
    }

    private boolean isFileCorrect() {
        return Files.exists(workFilePath);
    }

}
