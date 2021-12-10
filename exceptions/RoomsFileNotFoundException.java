package exceptions;

public class RoomsFileNotFoundException extends RuntimeException{
    public RoomsFileNotFoundException(String message) {
        super(message);
    }
}
