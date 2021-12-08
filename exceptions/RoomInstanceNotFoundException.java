package exceptions;

public class RoomInstanceNotFoundException extends RuntimeException{
    public RoomInstanceNotFoundException(String message) {
        super(message);
    }
}
