public class CloudBaseMismatchException extends RuntimeException {
    public CloudBaseMismatchException(){
        super("Некорректные данные о высоте нижней границы облаков");
    }
}
