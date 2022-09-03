public class CloudMismatchException extends RuntimeException {
    public CloudMismatchException(){
        super("Некорректные данные о количестве облаков");
    }
}
