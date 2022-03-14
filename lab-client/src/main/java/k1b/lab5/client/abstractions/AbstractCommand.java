package k1b.lab5.client.abstractions;


import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {

    private final String name;
    private final String description;
    private final int AMOUNT_OF_ARGS;
    private static final List<AbstractCommand> commandsList = new ArrayList<>();

    public AbstractCommand(String name, String description, int amountOfArgs) {
        this.name = name;
        this.description = description;
        this.AMOUNT_OF_ARGS = amountOfArgs;
        commandsList.add(this);
    }

    public String getName() {
        return name;
    }

    public List<AbstractCommand> getCommandsList() {
        return commandsList;
    }

    public int getAMOUNT_OF_ARGS() {
        return AMOUNT_OF_ARGS;
    }

    public String getDescription() {
        return name + ": " + description;
    }

    public abstract Object execute(String[] args);
}
