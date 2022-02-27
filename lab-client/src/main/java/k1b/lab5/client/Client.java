package k1b.lab5.client;

import k1b.lab5.client.csv_parser.CSVReader;
import k1b.lab5.client.entities.HumanCollection;
import k1b.lab5.client.user_command_line.CommandListener;

import java.io.FileNotFoundException;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        HumanCollection humanCollection = new HumanCollection("HUMAN_INFO");
        CSVReader reader = new CSVReader();
        try {
            reader.initializeFile(humanCollection.getFileName());
            reader.parseFile();
            humanCollection.fillWithArray(reader.getInfoFromFile());
            CommandListener commandListener = new CommandListener(humanCollection);
            commandListener.readCommandsFromSystemIn();
        } catch (FileNotFoundException e) {
            System.out.println("Файл: " + humanCollection.getFileName() + " не найден");
        } catch (NullPointerException e) {
            System.out.println("Пожалуйста проинциализируйте системную переменную HUMAN_INFO, " +
                    "содержащую путь до файла с информацией о коллекции");
        }
    }
}
