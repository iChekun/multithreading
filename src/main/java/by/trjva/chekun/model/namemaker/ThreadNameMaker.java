package by.trjva.chekun.model.namemaker;

public class ThreadNameMaker {

    private static final ThreadNameMaker instance = new ThreadNameMaker();

    private static int size = 0;

    private ThreadNameMaker() {
    }

    public static ThreadNameMaker getInstance() {
        return instance;
    }

    public int getUniqueName() {

        int name = size;

        boolean notFound = true;

//        while (notFound) {
//
//            notFound = false;
//
//            for (int i = 0; i < size; i++) {
//                if (name == masWithNames[i]) {
//                    notFound = true;
//                }
//            }
//            // name = random.nextInt(5005) * 10_000;
//            name++;
//        }

       // size++;

        return size++;
    }
}
