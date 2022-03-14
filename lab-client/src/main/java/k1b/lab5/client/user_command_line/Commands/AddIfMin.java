package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.HumanInfoInput;
import k1b.lab5.client.user_command_line.SuccessMessage;

public class AddIfMin extends AbstractCommand {

    public AddIfMin() {
        super("add_if_min", "Добавить введенный элемент в коллекцию, если его значение минимально, принимает на вход [Имя, наличие героизма(true/false), наличие зубочистки(true/false), скорость удара]", 4);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            try {
                HumanInfoInput humanInfoInput = new HumanInfoInput(args);
                humanInfoInput.inputHuman();
                Config.getCollectionManager().addIfMin(humanInfoInput.getNewHumanToInput());
                return new SuccessMessage("Объект успешно добавлен в коллекцию");
            } catch (IllegalArgumentException e) {
                return new ErrorMessage(e.getMessage());
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
