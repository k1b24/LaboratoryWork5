package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.entities.HumanBeing;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterLessThanCar extends AbstractCommand {

    public FilterLessThanCar() {
        super("filter_less_than_car", "Вывести элементы, значение скорости которых меньше заданного, принимает аргумент [Speed]", 1);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            try {
                int speedFilter = Integer.parseInt(args[0]);
                ArrayList<HumanBeing> listToReturn = Config.getCollectionManager().filterByCarSpeed(speedFilter);
                return new SuccessMessage(listToReturn.stream()
                        .map(HumanBeing::toString)
                        .collect(Collectors.joining("\n")));
            } catch (NumberFormatException e) {
                return new ErrorMessage("Ошибка ввода скорости машины");
            }
        } else {
            return new ErrorMessage("Введено неправильное количество аргументов");
        }
    }
}
