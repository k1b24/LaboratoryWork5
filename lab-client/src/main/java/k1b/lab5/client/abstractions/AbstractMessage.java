package k1b.lab5.client.abstractions;

public abstract class AbstractMessage {

    private final String message;
    public static final String ANSI_RESET = "\u001B[0m"; //ANSI_RESET

    public AbstractMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message + ANSI_RESET;
    }
}
