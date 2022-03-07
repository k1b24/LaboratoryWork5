package k1b.lab5.client;

import k1b.lab5.client.csv_parser.CSVReader;
import k1b.lab5.client.entities.CollectionManager;
import k1b.lab5.client.user_command_line.CommandListener;
import k1b.lab5.client.utils.TextSender;

import java.io.FileNotFoundException;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        CollectionManager humanCollection = new CollectionManager("HUMAN_INFO");
        CSVReader reader = new CSVReader();
        try {
            reader.initializeFile(humanCollection.getFileName());
            reader.parseFile();
            humanCollection.fillWithArray(reader.getInfoFromFile());
            CommandListener commandListener = new CommandListener(humanCollection);
            commandListener.readCommandsFromSystemIn();
        } catch (FileNotFoundException e) {
            TextSender.printError("Файл: " + humanCollection.getFileName() + " не найден");
        } catch (NullPointerException e) {
            TextSender.printError("Пожалуйста проинциализируйте системную переменную HUMAN_INFO, " +
                    "содержащую путь до файла с информацией о коллекции");
        }
    }
}
