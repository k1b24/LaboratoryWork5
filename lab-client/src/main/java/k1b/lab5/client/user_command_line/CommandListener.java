package k1b.lab5.client.user_command_line;

import k1b.lab5.client.annotations.Command;
import k1b.lab5.client.csv_parser.CSVSaver;
import k1b.lab5.client.entities.HumanCollection;
import k1b.lab5.client.entities.HumanBeing;
import k1b.lab5.client.entities.enums.Mood;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Класс отвечающий за работу с пользователем в интерактивном режиме
 */
public class CommandListener {

    /**
     * final количество аргументов для команды add
     */
    private static final int AMOUNT_OF_ARGS_FOR_ADD = 4;

    /**
     * final количество аргументов для команды update
     */
    private static final int AMOUNT_OF_ARGS_FOR_UPDATE = 5;

    /**
     * final количество команд введенных пользователем, которые сохраняются в памяти для команды history
     */
    private static final int AMOUNT_OF_COMMANDS_TO_SAVE = 10;

    /**
     * final количество методов с аннотацией Command
     */
    private static final Map<String, Method> COMMANDS = new HashMap<>();

    /**
     * final ссылка на коллекцию с которой работает пользователь
     */
    private final HumanCollection humanCollection;

    /**
     * final объект класса CSVSaver, который сохраняет информацию о коллекции в файл
     */
    private final CSVSaver saver;

    /**
     * Массив для хранения последних команд, которые ввел пользователь
     */
    private ArrayList<String> lastCommands = new ArrayList<>();

    /**
     * Конструктор, принимающий на вход ссылку на коллекцию, с которой будет работать пользователь
     * @param humanCollection коллекция, с которой будет работать пользователь
     */
    public CommandListener(HumanCollection humanCollection) {
        this.humanCollection = humanCollection;
        for (Method method : CommandListener.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                COMMANDS.put(command.name(), method);
            }
        }
        saver = new CSVSaver(humanCollection.getFileName());
    }

    /**
     * Консольная команда help, выводящая справку о доступных командах
     */
    @Command(name = "help", args = "", description = "Вывести справку по доступным командам")
    private void help() {
        System.out.println("Доступные команды: ");
        for (Map.Entry<String, Method> method : COMMANDS.entrySet()) {
            Command command = method.getValue().getAnnotation(Command.class);
            String name = command.name();
            String args = command.args();
            String description = command.description();
            System.out.println(name + " " + args + ": " + description);
        }
    }

    /**
     * Консольная команда info, выводящая информацию о коллекции
     */
    @Command(name = "info", args = "", description = "Вывести информацию о коллекции")
    private void info() {
        System.out.println(humanCollection.getInfoAboutCollection());
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
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода числового значения");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
            System.out.println("Передано неверное значение id");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Консольная команда save, сохраняющая коллекцию в файл
     */
    @Command(name = "save", args = "", description = "Сохранить информацию о коллекции в файл")
    private void save() throws IOException {
        saver.saveToFile(humanCollection.getArrayOfInfo());
        System.out.println("Файл сохранен");
    }

    /**
     * Консольная команда remove_by_id, удаляющая элемент коллекции по id
     */
    @Command(name = "remove_by_id", args = "[id]", description = "Удалить человека по его номеру", amountOfArgs = 1)
    private void removeById(String idToRemove) {
        int id;
        try {
            id = Integer.parseInt(idToRemove);
            humanCollection.removeHumanById(id);
            System.out.println("Человек с ID " + id + " успешно удален");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода, введите число");
        }
    }

    /**
     * Консольная команда clear, очищающая коллекцию
     */
    @Command(name = "clear", args = "", description = "Очистить коллекцию")
    private void clearCollection() {
        humanCollection.clearCollection();
        System.out.println("Коллекция очищена");
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
            System.out.println("Такого настроения не существует, введите одно из:");
            System.out.println(Arrays.toString(Mood.values()));
        }
    }

    /**
     * Консольная команда head, выводящая первый элемент коллекции
     */
    @Command(name = "head", args = "", description = "вывести первый элемент коллекции")
    private void head() {
        HumanBeing head = humanCollection.returnHead();
        if (head != null) {
            System.out.println(head);
        } else {
            System.out.println("Коллекция пустая :(");
        }
    }

    /**
     * Консольная команда history, выводящая последние команды, введенные пользователем
     */
    @Command(name = "history", args = "", description = "Вывести последние 10 команд, введенных пользователем")
    private void history() {
        for (int i = lastCommands.size() - 1; i >= 0; i--) {
            System.out.println(i + 1 + ". " + lastCommands.get(i));
        }
    }

    /**
     * Консольная команда print_descending, выводящая элементы коллекции в порядке убывания
     */
    @Command(name = "print_descending", args = "", description = "Вывести элементы коллекции в порядке убывания")
    private void printDescending() {
        List<HumanBeing> arrayToPrint = humanCollection.returnDescending();
        for (HumanBeing human : arrayToPrint) {
            System.out.println(human.toString());
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
                System.out.println(human.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода, введите число");
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
            System.out.println("Ошибка ввода числового значения");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return;
        }
        fileReader.parseFile();
        ArrayList<String> commands = fileReader.getInfoFromFile();
        for (String command : commands) {
            System.out.println();
            if (command.contains("execute_script")) {
                System.out.println("Команда: " + command + " пропущена, так как она вызывает скрипт, в котором она содержится");
                continue;
            }
            runCommand(command);
        }
    }

    /**
     * Метод, вызывающий метод консольный команды, имя аннотации которой совпадает с именем, введенной пользователем команды
     * @param commandName имя команды
     * @param commandArgs аргументы команды
     */
    private void invokeMethod(String commandName, String[] commandArgs)  {
        try {
            Method method = COMMANDS.get(commandName);
            lastCommands.add(commandName);
            Command command = method.getAnnotation(Command.class);
            if (commandArgs.length != command.amountOfArgs()) {
                System.out.println("Неверное количество аргументов команды " + commandName + ". Необходимо: " + command.amountOfArgs());
            } else {
                method.invoke(this, commandArgs);
            }
            if (lastCommands.size() >= AMOUNT_OF_COMMANDS_TO_SAVE) {
                lastCommands = new ArrayList<>(lastCommands.subList(1, AMOUNT_OF_COMMANDS_TO_SAVE));
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода! Команды " + commandName + " не существует, введите команду help, для вывода списка доступных команд");
        }
    }

    /**
     * Метод, разбивающий команду введенную пользователем на имя и аргументы с помощью SmartSplitter.smartSplit
     * @param command команда, введенная пользователем
     */
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
