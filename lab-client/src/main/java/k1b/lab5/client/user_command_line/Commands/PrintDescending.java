package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.entities.HumanBeing;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;

import java.util.List;
import java.util.stream.Collectors;

public class PrintDescending extends AbstractCommand {

    public PrintDescending() {
        super("print_descending", "Вывести коллекциию в порядке убывания", 0);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            List<HumanBeing> listToReturn = Config.getCollectionManager().returnDescending();
            return new SuccessMessage(listToReturn.stream()
                    .map(HumanBeing::toString)
                    .collect(Collectors.joining("\n")));
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
