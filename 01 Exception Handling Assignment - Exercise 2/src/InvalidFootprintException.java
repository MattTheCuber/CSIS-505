public class InvalidFootprintException extends IllegalArgumentException {
    public InvalidFootprintException() {
        super("Invalid parameter value for computing a carbon footprint.");
    }

    public InvalidFootprintException(String message) {
        super(message);
    }

    public InvalidFootprintException(String message, Throwable exception) {
        super(message, exception);
    }

    public InvalidFootprintException(Throwable exception) {
        super(exception);
    }
}
