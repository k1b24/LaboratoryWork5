package k1b.lab5.client.user_command_line;

import k1b.lab5.client.abstractions.AbstractMessage;

public class SuccessMessage extends AbstractMessage {

    private static final String MESSAGE_COLOR = "\u001B[32m"; //ANSI_GREEN

    public SuccessMessage(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return MESSAGE_COLOR + super.getMessage();
    }
}
