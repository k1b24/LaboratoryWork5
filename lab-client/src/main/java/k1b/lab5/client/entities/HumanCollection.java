package k1b.lab5.client.entities;

import k1b.lab5.client.entities.enums.Mood;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Класс для работы с коллекцией экземпляров HumanBeing
 */
public class HumanCollection {

    /**
     * Коллекция экземпляров HumanBeing
     */
    private final PriorityQueue<HumanBeing> humanQueue = new PriorityQueue<>();

    /**
     * Дата инициализации коллекции
     */
    private final LocalDate initializationDate;

    /**
     * Имя файла в котором хранятся данные о коллекции
     */
    private final String fileName;

    /**
     * Конструктор, задаюший дату инициализации коллекции и поле fileName
     * @param fileName имя файла, в котором хранятся данные о коллекции
     */
    public HumanCollection(String fileName) {
        this.fileName = fileName;
        initializationDate = LocalDate.now();
    }

    /**
     * @return имя файла с данными о коллекции
     */
    public String getFileName() {
        return System.getenv(fileName);
    }

    /**
     * Метод, добавляющий экземпляр HumanBeing в коллекцию
     * @param human экзепмпляр HumanBeing
     */
    public void addHuman(HumanBeing human) {
        humanQueue.add(human);
    }

    /**
     * Метод, удаляющий человека из коллекции по заданному ID
     * @param id id человека
     */
    public void removeHumanById(int id) {
        humanQueue.removeIf(human -> human.getId() == id);
    }

    /**
     * Метод, удаляющий любого человека из коллекции по заданному значению настроения
     * @param mood значение настроения
     */
    public void removeHumanByAnyMood(Mood mood) {
        humanQueue.removeIf(human -> human.getMood() == mood);
    }

    /**
     * Метод, позволяющий получить человека из коллекции по id
     * @param id id человека
     * @return экземпляр HumanBeing, соответствующий полученному значению ID
     * @throws IllegalArgumentException
     */
    public HumanBeing getHumanById(int id) throws IllegalArgumentException {
        for (HumanBeing human : humanQueue) {
            if (human.getId() == id) {
                return human;
            }
        }
        throw new IllegalArgumentException("Человек с таким ID не найден");
    }

    /**
     * Метод, позволяющий обновить информацию о человеке из коллекции по id
     * @param id id человека
     * @param humanToSet новая информация о человеке
     * @throws IllegalArgumentException
     */
    public void setHumanById(int id, HumanBeing humanToSet) throws IllegalArgumentException {
        for (HumanBeing human : humanQueue) {
            if (human.getId() == id) {
                human = humanToSet;
                return;
            }
        }
        throw new IllegalArgumentException("Человек с таким ID не найден");
    }

    /**
     * Метод выводящий информацию о коллекции
     */
    public void show() {
        for (HumanBeing human : humanQueue) {
            System.out.println(human);
        }
    }

    /**
     * Метод, позволяющий заполнить коллекцию при помощи массива HumanBeing
     * @param arrayOfPeople массив HumanBeing
     */
    public void fillWithArray(ArrayList<HumanBeing> arrayOfPeople) {
        for (HumanBeing human : arrayOfPeople) {
            addHuman(human);
        }
    }

    /**
     * @return наибольший элемент коллекции
     */
    public HumanBeing returnHead() {
        return humanQueue.peek();
    }

    /**
     * @return массив людей коллекции, расположенных в порядке убывания
     */
    public ArrayList<HumanBeing> returnDescending() {
        ArrayList<HumanBeing> descendingList = new ArrayList<>(humanQueue);
        Collections.reverse(descendingList);
        return descendingList;
    }

    /**
     * @param speed скорость машины
     * @return все элементы коллекции, скорость которых меньше заданной
     */
    public ArrayList<HumanBeing> filterByCarSpeed(int speed) {
        ArrayList<HumanBeing> filtered = new ArrayList<>();
        for (HumanBeing human : humanQueue) {
            if (human.getCar() != null && human.getCar().getSpeed() < speed) {
                filtered.add(human);
            }
        }
        return filtered;
    }

    /**
     * Метод, добавляющий человека в коллекцию, если его значение является минимальным
     * @param newHuman человек для добавления
     */
    public void addIfMin(HumanBeing newHuman) {
        for (HumanBeing human : humanQueue) {
            if (newHuman.compareTo(human) < 0) {
                return;
            }
        }
        humanQueue.add(newHuman);
    }

    /**
     * Метод, полностью очищающий коллекцию
     */
    public void clearCollection() {
        humanQueue.clear();
    }

    /**
     * @return информация о коллекции в строковом формате
     */
    public String getInfoAboutCollection() {
        return "Информация о коллекции. Тип: " + humanQueue.getClass() + " Дата инициализации: "
                + initializationDate.toString() + " Количество элементов: " + humanQueue.size();
    }

    /**
     * @return информация обо всех элементах коллекции в строковом формате
     */
    public ArrayList<String> getArrayOfInfo() {
        ArrayList<String> arrayOfInfo = new ArrayList<>();
        for (HumanBeing human : humanQueue) {
            String humanInfo = human.getName() + "," + human.getCoordinates().getX() + ","
                    + human.getCoordinates().getY() + "," + human.getCreationDate().toString() + ","
                    + human.isRealHero() + "," + human.isHasToothpick() + ","
                    + (human.getImpactSpeed() == null ? "null," : human.getImpactSpeed() + ",")
                    + (human.getWeaponType() == null ? "null," : human.getWeaponType() + ",")
                    + (human.getMood() == null ? "null," : human.getMood() + ",")  + (human.getCar() == null ? "," : (human.getCar().getCool() == null ? "null," : human.getCar().getCool()) + "," + human.getCar().getSpeed());
            arrayOfInfo.add(humanInfo);
        }
        return arrayOfInfo;
    }
}