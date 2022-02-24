package k1b.lab5.client.csv_parser;

import k1b.lab5.client.abstractions.AbstractFileReader;
import k1b.lab5.client.entities.HumanBeing;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс, реализующий чтение данных из CSV файла, наследует абстрактный класс AbstractFileReader
 */
public class CSVReader extends AbstractFileReader {

    /**
     * Поле, хранящее в себе scanner файла
     */
    private Scanner scannerOfFile;
    /**
     * Поле, хранящее в себе параметры csv файла (первую строку)
     */
    private String[] parameters;
    /**
     * Поле, хранящее в себе информацию об элементах коллекции в виде строк
     */
    private final ArrayList<String> peopleStrings = new ArrayList<>();
    /**
     * Поле, хранящее в себе хеш-таблицы, содержащие информацию о каждом элементе коллекции в виде <название параметра: значение>
     */
    private final ArrayList<HashMap<String, String>> peopleInfo = new ArrayList<>();
    /**
     * Поле, хранящее в себе созданные элементы коллекции
     */
    private final ArrayList<HumanBeing> humanArray = new ArrayList<>();
    /**
     * Поле, хранящее в себе все поля элемента коллекции, которые заполняет парсер
     */
    private final Field[] humanBeingFields;

    /**
     * Конструктор класса CSVReader, при инициализации с помощью рефлексии задает значение humanBeingFields
     */
    public CSVReader() {
        humanBeingFields = HumanBeing.class.getDeclaredFields();
    }

    /**
     * Метод, возвращающий массив прочитанных элементов коллекции из файла
     */
    @Override
    public ArrayList<HumanBeing> getInfoFromFile() {
        return humanArray;
    }

    /**
     * Метод, заполняющий массив элементов коллекции, читая информацию о них из файла
     */
    @Override
    public void parseFile() {
        readPeople();
        for (HashMap<String, String> humanInfo : peopleInfo) {
            HumanBeing newHuman = null;
            newHuman = createHuman(humanInfo);
            humanArray.add(newHuman);
        }
    }

    /**
     * Метод, создающий один элемент коллекции, по хеш-таблице <имя поля : значение поля>
     * @param humanInfo
     * @return HumanBeing
     */
    private HumanBeing createHuman(HashMap<String, String> humanInfo) {
        HumanBeing newHuman = new HumanBeing(true);
        for (Map.Entry<String, String> element : humanInfo.entrySet()) {
            for (Field field: humanBeingFields) {
                Class<?> cl = field.getType();
                if (field.getName().equals(element.getKey())) {
                    try {
                        Method setter = HumanBeing.class.getDeclaredMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), String.class);
                        setter.invoke(newHuman, element.getValue());
                    } catch (Exception e) {
                        System.out.println("Ошибка при чтении файла");
                        System.exit(2);
                    }
                } else {
                    Field[] innerFields = cl.getDeclaredFields();
                    for (Field innerField : innerFields) {
                        if (innerField.getName().equals(element.getKey())) {
                            try {

                                Method innerSetter = cl.getDeclaredMethod("set" + innerField.getName().substring(0, 1).toUpperCase() + innerField.getName().substring(1), String.class);
                                Method getter = HumanBeing.class.getDeclaredMethod("get" + cl.getSimpleName().substring(0,1).toUpperCase() + cl.getSimpleName().substring(1));
                                System.out.println(getter.getName());
                                innerSetter.invoke(getter.invoke(newHuman), element.getValue());
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Ошибка при чтении файла");
                                System.exit(2);
                            }
                        }
                    }
                }

            }
        }
        return newHuman;
    }

    /**
     * Метод, инициализирующий файл для чтения, получающий в качестве параметра имя этого файла
     * @param fileName имя файла
     * @throws FileNotFoundException
     */
    @Override
    public void initializeFile(String fileName) throws FileNotFoundException {
        File infoFile = new File(fileName);
        scannerOfFile = new Scanner(infoFile);
    }

    /**
     * Метод, читающий строки с информацией из файла, в нем задаются поля: parameters и peopleStrings
     */
    private void readStringsFromFile() {
        ArrayList<String> stringsFromFile = new ArrayList<>();
        while (scannerOfFile.hasNextLine()) {
            stringsFromFile.add(scannerOfFile.nextLine());
        }
        parameters = stringsFromFile.get(0).split(",");
        for (int i = 1; i < stringsFromFile.size(); i++) {
            peopleStrings.add(stringsFromFile.get(i));
        }
    }

    /**
     * Метод, собирающий хеш-таблицу из каждой строки прочтенного файла
     */
    private void readPeople() {
        readStringsFromFile();
        for (String peopleString : peopleStrings) {
            HashMap<String, String> newHuman = new HashMap<>();
            String[] humanInfo = peopleString.split(",", -1);
            for (int j = 0; j < parameters.length; j++) {
                newHuman.put(parameters[j], humanInfo[j]);
            }
            peopleInfo.add(newHuman);
        }
    }
}
