package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.entities.enums.Mood;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;

import java.util.Arrays;

public class RemoveByAnyMood extends AbstractCommand {

    public RemoveByAnyMood() {
        super("remove_by_any_mood", "Удалить любого человека из коллекции по его настроению, принимает на вход тип настроения " + Arrays.toString(Mood.values()), 0);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            Mood moodToRemove;
            if ("".equals(args[0])) {
                moodToRemove = null;
            } else {
                try {
                    moodToRemove = Mood.valueOf(args[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    return new ErrorMessage("Такого настроения не существует, введите одно из: \n" + Arrays.toString(Mood.values()));
                }
            }
            Config.getCollectionManager().removeHumanByAnyMood(moodToRemove);
            return new SuccessMessage("Случайный человек с настроением " + args[0] + " удален");
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
