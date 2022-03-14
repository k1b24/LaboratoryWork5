package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.entities.CollectionManager;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;

public class Info extends AbstractCommand {


    public Info() {
        super("info", "Вывести информацию о коллекции", 0);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            return new SuccessMessage(Config.getCollectionManager().getInfoAboutCollection());
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
