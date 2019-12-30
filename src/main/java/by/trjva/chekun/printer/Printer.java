package by.trjva.chekun.printer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Printer {

    private static final Printer instance = new Printer();

    public static Printer getInstance() {
        return instance;
    }

    private static final Logger logger = LogManager.getLogger(Printer.class);

    private Printer() {
    }

    public static void print(String message) {
       logger.info(message);
        System.out.println(message);
    }

    public static void print(Exception exception) {
        logger.error(exception.getMessage());
        System.err.println(exception);
    }

    public static void print(Object object) {
       logger.info(object);
        System.out.println(object);
    }


}
