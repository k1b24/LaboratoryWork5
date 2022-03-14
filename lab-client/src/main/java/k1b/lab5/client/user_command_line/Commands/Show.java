package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;

public class Show extends AbstractCommand {

    public Show() {
        super("show", "Вывести все элементы коллекции", 0);
    }
    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            return new SuccessMessage(Config.getCollectionManager().getStringForShowing());
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
