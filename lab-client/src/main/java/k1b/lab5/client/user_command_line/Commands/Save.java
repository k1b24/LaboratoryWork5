package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.csv_parser.CSVSaver;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.SuccessMessage;

import java.io.IOException;

public class Save extends AbstractCommand {
    
    private final String filePath;

    public Save(String filePath) {
        super("save", "Сохранить коллекцию в файл", 0);
        this.filePath = filePath;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            try {
                CSVSaver saver = new CSVSaver(this.filePath);
                saver.saveToFile(Config.getCollectionManager().getArrayOfInfo());
                return new SuccessMessage("Коллекция успешно сохранена");
            } catch (IOException e) {
                return new ErrorMessage("Файл не найден");
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
