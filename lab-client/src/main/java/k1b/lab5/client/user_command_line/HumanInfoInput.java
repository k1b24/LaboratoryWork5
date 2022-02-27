package k1b.lab5.client.user_command_line;

import k1b.lab5.client.entities.HumanBeing;
import k1b.lab5.client.entities.enums.Mood;
import k1b.lab5.client.entities.enums.WeaponType;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс, считывающий информации о человеке в интерактивном режиме
 */
public class HumanInfoInput {

    /**
     * Поле человека, информацию о котором мы заполняем в интерактивном режиме
     */
    private final HumanBeing newHumanToInput;

    /**
     * Примитивное поле отвечающее за имя человека
     */
    private final String name;

    /**
     * Примитивное поле отвечающее за героизм человека
     */
    private final String realHero;

    /**
     * Примитивное поле отвечающее за наличие зубочистки у человека
     */
    private final String hasToothpick;

    /**
     * Примитивное поле отвечающее за скорость удара человека
     */
    private final String impactSpeed;

    /**
     * Конструктор создающий человека информацию о котором мы хотим получить автоматически
     * @param name имя человека
     * @param realHero героизм человека
     * @param hasToothpick наличие зубочистки у человека
     * @param impactSpeed скорость удара человека
     */
    public HumanInfoInput(String name, String realHero, String hasToothpick, String impactSpeed) {
        this.name = name;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        newHumanToInput = new HumanBeing(false);
        newHumanToInput.setId();
        setPrimitives();

    }

    /**
     * Конструктор принимающий человека информацию о котором мы хотим изменить
     * @param newHumanToInput человек информацию о котором мы хотим изменить
     * @param name имя человека
     * @param realHero героизм человека
     * @param hasToothpick наличие зубочистки у человека
     * @param impactSpeed скорость удара человека
     */
    public HumanInfoInput(HumanBeing newHumanToInput, String name, String realHero, String hasToothpick, String impactSpeed) {
        this.name = name;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.newHumanToInput = newHumanToInput;
        setPrimitives();
    }

    /**
     * метод отвечающий за присвоение имени человеку
     */
    private void inputName() {
        newHumanToInput.setName(name);
    }

    /**
     * метод отвечающий за присвоение координаты X человеку
     */
    private void inputX() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите X(целое число): ");
        try {
            newHumanToInput.getCoordinates().setX(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода числа X");
            inputX();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputX();
        }
    }

    /**
     * метод отвечающий за присвоение координаты Y человеку
     */
    private void inputY() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите Y(число с плавающей точкой): ");
        try {
            newHumanToInput.getCoordinates().setY(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода числа Y");
            inputY();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputY();
        }
    }

    /**
     * метод отвечающий за присвоение героизма человеку
     */
    private void inputRealHero() {
        newHumanToInput.setRealHero(realHero);
    }

    /**
     * метод отвечающий за присвоение наличия зубочистки человеку
     */
    private void inputHasToothpick() {
        newHumanToInput.setHasToothpick(hasToothpick);
    }

    /**
     * метод отвечающий за присвоение скорости удара человеку
     */
    private void inputImpactSpeed() {
        newHumanToInput.setImpactSpeed(impactSpeed);
    }

    /**
     * метод отвечающий за присвоение типа оружия человеку
     */
    private void inputWeaponType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите тип оружия из предложенных вариантов или оставьте пустую строку, если оружия нет: ");
        System.out.println(Arrays.toString(WeaponType.values()));
        try {
            newHumanToInput.setWeaponType(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода типа оружия");
            inputWeaponType();
        }
    }

    /**
     * метод отвечающий за присвоение настроения человеку
     */
    private void inputMood() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите настроение из предложенных вариантов или оставьте пустую строку, если человек дед инсайд: ");
        System.out.println(Arrays.toString(Mood.values()));
        try {
            newHumanToInput.setMood(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода настроения");
            inputMood();
        }
    }

    /**
     * метод отвечающий за присвоение скорости машины
     */
    private void inputCarSpeed() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите скорость машины: ");
        try {
            newHumanToInput.getCar().setCarSpeed(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода скорости машины");
            inputCarSpeed();
        }
    }

    /**
     * метод отвечающий за присвоение крутости машины
     */
    private void inputCarCoolness() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Машина крутая?[y/n]: ");
        String inputString = scanner.nextLine().toLowerCase();
        if ("y".equals(inputString)) {
            inputString = "true";
        } else if ("n".equals(inputString)) {
            inputString = "false";
        }
        try {
            newHumanToInput.getCar().setCarCoolness(inputString);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputCarCoolness();
        }
    }

    /**
     * метод отвечающий за присвоение наличия у человека машины
     */
    private void inputCar() {
        System.out.println("Есть ли у человека машина?[y/n]");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if ("y".equals(userAnswer)) {
            inputCarCoolness();
            inputCarSpeed();
        } else if ("n".equals(userAnswer)) {
            newHumanToInput.setCar(null);
        } else {
            System.out.println("Ошибка ввода");
            inputCar();
        }
    }

    /**
     * метод отвечающий за присвоение примитивов человеку
     */
    public void setPrimitives() {
        inputName();
        inputRealHero();
        inputHasToothpick();
        inputImpactSpeed();
    }

    /**
     * метод отвечающий за ввод не примитивных типов данных человека
     */
    public void inputHuman() {
        inputImpactSpeed();
        inputX();
        inputY();
        inputWeaponType();
        inputMood();
        inputCar();
    }

    /**
     * @return введенный пользователем человек
     */
    public HumanBeing getNewHumanToInput() {
        return newHumanToInput;
    }
}
