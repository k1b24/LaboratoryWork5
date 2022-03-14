package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.entities.CollectionManager;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.HumanInfoInput;
import k1b.lab5.client.user_command_line.SuccessMessage;

import java.util.ArrayList;
import java.util.Arrays;

public class Update extends AbstractCommand {

    public Update() {
        super("update", "Обновить элемент коллекции по его ID, принимает на вход [ID, Имя, наличие героизма(true/false), наличие зубочистки(true/false), скорость удара]", 5);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            try {
                int id = Integer.parseInt(args[0]);
                HumanInfoInput humanInfoInput = new HumanInfoInput(Config.getCollectionManager().getHumanById(id), Arrays.copyOfRange(args, 1, args.length));
                humanInfoInput.inputHuman();
                Config.getCollectionManager().setHumanById(id, humanInfoInput.getNewHumanToInput());
                return new SuccessMessage("Объект успешно добавлен в коллекцию");
            } catch (IllegalArgumentException e) {
                return new ErrorMessage(e.getMessage());
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }

}
