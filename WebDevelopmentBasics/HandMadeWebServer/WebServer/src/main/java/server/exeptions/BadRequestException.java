package server.exeptions;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
