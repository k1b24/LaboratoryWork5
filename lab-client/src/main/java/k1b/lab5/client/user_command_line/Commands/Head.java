package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.entities.HumanBeing;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;
import k1b.lab5.client.utils.TextSender;

public class Head extends AbstractCommand {

    public Head() {
        super("head","Вывести первый элемент коллекции(голову очереди)" ,0);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            HumanBeing head = Config.getCollectionManager().returnHead();
            if (head != null) {
                return new SuccessMessage(head.toString());
            } else {
                return new ErrorMessage("Коллекция пустая :(");
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
