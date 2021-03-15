package inventory.errorHandler;

public class errorHandler implements Thread.UncaughtExceptionHandler{
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Unhandled error caught!");
    }
}
