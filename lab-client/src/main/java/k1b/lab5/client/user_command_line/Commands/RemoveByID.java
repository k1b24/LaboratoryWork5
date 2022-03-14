package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;
import k1b.lab5.client.utils.TextSender;

public class RemoveByID extends AbstractCommand {

    public RemoveByID() {
        super("remove_by_id", "Удалить человека из коллекции по его ID, принимает на вход [ID]", 1);
    }
    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            try {
                int id = Integer.parseInt(args[0]);
                if (id <= Config.getCollectionManager().getLength() && id > 0) {
                    Config.getCollectionManager().removeHumanById(id);
                    return new SuccessMessage("Человек с ID " + id + " успешно удален");
                } else {
                    return new ErrorMessage("Человек с таким ID не найден");
                }
            } catch (NumberFormatException e) {
                return new ErrorMessage("Передано неправильное значение ID");
            }
        } else {
            return new ErrorMessage("Передано неверное количество аргументов");
        }
    }
}
