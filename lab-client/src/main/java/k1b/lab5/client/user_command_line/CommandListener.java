package k1b.lab5.client.user_command_line;

import k1b.lab5.client.annotations.Command;
import k1b.lab5.client.csv_parser.CSVSaver;
import k1b.lab5.client.entities.CollectionManager;
import k1b.lab5.client.entities.HumanBeing;
import k1b.lab5.client.entities.enums.Mood;
import k1b.lab5.client.utils.SmartSplitter;
import k1b.lab5.client.utils.TextSender;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Класс отвечающий за работу с пользователем в интерактивном режиме
 */
public class CommandListener {

    private static final int AMOUNT_OF_ARGS_FOR_ADD = 4;
    private static final int AMOUNT_OF_ARGS_FOR_UPDATE = 5;
    private static final int AMOUNT_OF_COMMANDS_TO_SAVE = 10;
    private static final Map<String, Method> COMMANDS = new HashMap<>();
    private final CollectionManager humanCollection;
    private final CSVSaver saver;
    private ArrayList<String> lastCommands = new ArrayList<>();

    /**
     * Конструктор, принимающий на вход ссылку на коллекцию, с которой будет работать пользователь
     * @param humanCollection коллекция, с которой будет работать пользователь
     */
    public CommandListener(CollectionManager humanCollection) {
        this.humanCollection = humanCollection;
        for (Method method : CommandListener.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                COMMANDS.put(command.name(), method);
            }
        }
        saver = new CSVSaver(humanCollection.getFileName());
        TextSender.printMessage("Добро пожаловать в интерактивный режим работы с коллекцией, " +
                "введите help, чтобы узнать информацию о доступных командах");
    }

    /**
     * Консольная команда help, выводящая справку о доступных командах
     */
    @Command(name = "help", args = "", description = "Вывести справку по доступным командам")
    private void help() {
        TextSender.printMessage("Доступные команды: ");
        for (Map.Entry<String, Method> method : COMMANDS.entrySet()) {
            Command command = method.getValue().getAnnotation(Command.class);
            String name = command.name();
            String args = command.args();
            String description = command.description();
            TextSender.printMessage(name + " " + args + ": " + description);
        }
    }

    /**
     * Консольная команда info, выводящая информацию о коллекции
     */
    @Command(name = "info", args = "", description = "Вывести информацию о коллекции")
    private void info() {
        TextSender.printMessage(humanCollection.getInfoAboutCollection());
    }

    /**
     * Консольная команда show, выводящая информацию о всех объектах коллекции
     */
    @Command(name = "show", args = "", description = "Вывести информацию о всех объектах коллекции")
    private void show() {
        humanCollection.show();
    }

    /**
     * Консольная команда add, добавляющая элемент в коллекцию
     */
    @Command(name = "add", args = "[name, real hero?, has toothpick?]", description = "Добавить элемент в коллекцию", amountOfArgs = AMOUNT_OF_ARGS_FOR_ADD)
    private void add(String name, String realHero, String hasToothpick, String impactSpeed) {
        try {
            HumanInfoInput humanInfoInput = new HumanInfoInput(name, realHero, hasToothpick, impactSpeed);
            humanInfoInput.inputHuman();
            humanCollection.addHuman(humanInfoInput.getNewHumanToInput());
        } catch (IllegalArgumentException e) {
            TextSender.printError(e.getMessage());
        }
    }

    /**
     * Консольная команда update, изменяющая информацию об элементе коллекции по его полю id
     */
    @Command(name = "update", args = "[id, name, real hero?, has toothpick?, impact speed]", description = "Обновить информацию о пользователе с определенным номером", amountOfArgs = AMOUNT_OF_ARGS_FOR_UPDATE)
    private void update(String idToUpdate, String name, String realHero, String hasToothpick, String impactSpeed) {
        try {
            int id = Integer.parseInt(idToUpdate);
            HumanInfoInput humanInfoInput = new HumanInfoInput(humanCollection.getHumanById(id), name, realHero, hasToothpick, impactSpeed);
            humanInfoInput.inputHuman();
            humanCollection.setHumanById(id, humanInfoInput.getNewHumanToInput());
        } catch (NumberFormatException e) {
            TextSender.printError("Передано неверное значение id");
        } catch (IllegalArgumentException e) {
            TextSender.printError(e.getMessage());
        }
    }

    /**
     * Консольная команда save, сохраняющая коллекцию в файл
     */
    @Command(name = "save", args = "", description = "Сохранить информацию о коллекции в файл")
    private void save() throws IOException {
        saver.saveToFile(humanCollection.getArrayOfInfo());
        TextSender.printMessage("Файл сохранен");
    }

    /**
     * Консольная команда remove_by_id, удаляющая элемент коллекции по id
     */
    @Command(name = "remove_by_id", args = "[id]", description = "Удалить человека по его номеру", amountOfArgs = 1)
    private void removeById(String idToRemove) {
        int id;
        try {
            id = Integer.parseInt(idToRemove);
            if (id <= humanCollection.getLength() && id > 0) {
                humanCollection.removeHumanById(id);
                TextSender.printMessage("Человек с ID " + id + " успешно удален");
            } else {
                TextSender.printError("Человек с таким ID не найден");
            }
        } catch (NumberFormatException e) {
            TextSender.printError("Ошибка ввода, введите число");
        }
    }

    /**
     * Консольная команда clear, очищающая коллекцию
     */
    @Command(name = "clear", args = "", description = "Очистить коллекцию")
    private void clearCollection() {
        humanCollection.clearCollection();
        TextSender.printMessage("Коллекция очищена");
    }

    /**
     * Консольная команда remove_by_any_mood, удаляющая из коллекции один элемент, значение поля mood которого эквивалентно заданному
     */
    @Command(name = "remove_by_any_mood", args = "[mood]", description = "Удалить из коллекции один элемент, значение поля mood которого эквивалентно заданному", amountOfArgs = 1)
    private void removeByAnyMood(String mood) {
        try {
            Mood moodToRemove;
            if ("".equals(mood)) {
                moodToRemove = null;
            } else {
                moodToRemove = Mood.valueOf(mood.toUpperCase());
            }
            humanCollection.removeHumanByAnyMood(moodToRemove);
        } catch (Exception e) {
            TextSender.printError("Такого настроения не существует, введите одно из:");
            TextSender.printMessage(Arrays.toString(Mood.values()));
        }
    }

    /**
     * Консольная команда head, выводящая первый элемент коллекции
     */
    @Command(name = "head", args = "", description = "вывести первый элемент коллекции")
    private void head() {
        HumanBeing head = humanCollection.returnHead();
        if (head != null) {
            TextSender.printMessage(head.toString());
        } else {
            TextSender.printError("Коллекция пустая :(");
        }
    }

    /**
     * Консольная команда history, выводящая последние команды, введенные пользователем
     */
    @Command(name = "history", args = "", description = "Вывести последние 10 команд, введенных пользователем")
    private void history() {
        for (int i = lastCommands.size() - 1; i >= 0; i--) {
            TextSender.printMessage(i + 1 + ". " + lastCommands.get(i));
        }
    }

    /**
     * Консольная команда print_descending, выводящая элементы коллекции в порядке убывания
     */
    @Command(name = "print_descending", args = "", description = "Вывести элементы коллекции в порядке убывания")
    private void printDescending() {
        List<HumanBeing> arrayToPrint = humanCollection.returnDescending();
        for (HumanBeing human : arrayToPrint) {
            TextSender.printMessage(human.toString());
        }
    }

    /**
     * Консольная команда filter_less_than_car, выводящая элементы коллекции, значениие скорости машины у которых меншье заданного
     */
    @Command(name = "filter_less_than_car", args = "[speed]", description = "Вывести элементы, значение скорости которых меньше заданного", amountOfArgs = 1)
    private void filterLessThanCar(String speed) {
        try {
            int speedFilter = Integer.parseInt(speed);
            ArrayList<HumanBeing> humans = humanCollection.filterByCarSpeed(speedFilter);
            for (HumanBeing human : humans) {
                TextSender.printMessage(human.toString());
            }
        } catch (NumberFormatException e) {
            TextSender.printError("Ошибка ввода, введите число");
        }
    }

    /**
     * Консольная команда add_if_min, добавляющая элемент, введенный пользователем, в коллекцию, если его значение минимально
     */
    @Command(name = "add_if_min", args = "[name, real hero?, has toothpick?]", description = "Добавить введенный элемент в коллекцию, если его значение минимально", amountOfArgs = AMOUNT_OF_ARGS_FOR_ADD)
    private void addIfMin(String name, String realHero, String hasToothpick, String impactSpeed) {
        try {
            HumanInfoInput humanInfoInput = new HumanInfoInput(name, realHero, hasToothpick, impactSpeed);
            humanInfoInput.inputHuman();
            humanCollection.addIfMin(humanInfoInput.getNewHumanToInput());
        } catch (NumberFormatException e) {
            TextSender.printError("Ошибка ввода числового значения");
        } catch (IllegalArgumentException e) {
            TextSender.printError(e.getMessage());
        }
    }

    /**
     * Консольная команда execute_script, исполняющая скрипт из указанного файла
     */
    @Command(name = "execute_script", args = "[file_name]", description = "Cчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.", amountOfArgs = 1)
    private void executeScript(String fileName) {
        ExecutableFileReader fileReader = new ExecutableFileReader();
        try {
            fileReader.initializeFile(fileName);
        } catch (IOException e) {
            TextSender.printError(e.getMessage());
            return;
        }
        fileReader.parseFile();
        ArrayList<String> commands = fileReader.getInfoFromFile();
        for (String command : commands) {
            TextSender.printMessage("");
            if (command.contains("execute_script")) {
                TextSender.printError("Команда: " + command + " пропущена, так как она вызывает скрипт");
                continue;
            }
            runCommand(command);
        }
    }

    private void invokeMethod(String commandName, String[] commandArgs)  {
        try {
            Method method = COMMANDS.get(commandName);
            lastCommands.add(commandName);
            Command command = method.getAnnotation(Command.class);
            if (commandArgs.length != command.amountOfArgs()) {
                TextSender.printError("Неверное количество аргументов команды " + commandName + ". Необходимо: " + command.amountOfArgs());
            } else {
                method.invoke(this, commandArgs);
            }
            if (lastCommands.size() >= AMOUNT_OF_COMMANDS_TO_SAVE) {
                lastCommands = new ArrayList<>(lastCommands.subList(1, AMOUNT_OF_COMMANDS_TO_SAVE));
            }
        } catch (Exception e) {
            TextSender.printError("Ошибка ввода! Команды " + commandName + " не существует, введите команду help, для вывода списка доступных команд");
        }
    }

    private void runCommand(String command) {
        String[] inputString = SmartSplitter.smartSplit(command).toArray(new String[0]);
        String commandName = inputString[0].toLowerCase();
        String[] commandArgs = Arrays.copyOfRange(inputString, 1, inputString.length);
        invokeMethod(commandName, commandArgs);
    }

    /**
     * Метод, читающий команды из System.in до тех пор, пока не возникнет команда exit
     */
    public void readCommandsFromSystemIn() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String line = scanner.nextLine();
                if ("exit".equals(line)) {
                    break;
                }
                runCommand(line);
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
