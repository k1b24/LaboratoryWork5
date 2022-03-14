package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;

public class Clear extends AbstractCommand {

    public Clear() {
        super("clear", "Очистить коллекцию", 0);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            Config.getCollectionManager().clearCollection();
            return new SuccessMessage("Коллекция успешно очищена");
        } else {
            return new ErrorMessage("Передано неверное количество аргументов");
        }
    }
}
