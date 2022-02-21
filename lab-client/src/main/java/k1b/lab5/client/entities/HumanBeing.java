package k1b.lab5.client.entities;

import k1b.lab5.client.entities.enums.Mood;
import k1b.lab5.client.entities.enums.WeaponType;

import java.time.LocalDate;

/**
 *  Класс человек, коллекцией экземпляров которого управляет коллекция и пользователь в интерактивном режиме
 */
public class HumanBeing implements Comparable<HumanBeing> {

    /**
     * final поле, отвечающее за максимальное значение скорости удара
     */
    private static final int MAX_SPEED = 712;

    /**
     * Поле, отвечающее за количество созданных ID, во время работы программы
     */
    private static long idCounter = 1;

    /**
     * Поле, хранящее уникальное ID человека
     * Это значение генерируется автоматически
     */
    private long id;

    /**
     * Поле, хранящее имя человека
     * Поле не может быть null
     * Имя не может быть пустым
     */
    private String name;

    /**
     * Поле, хранящее координаты человека
     * Поле не может быть null
     */
    private Coordinates coordinates = new Coordinates();

    /**
     * Поле, хранящее дату создания человека
     * Поле не может быть null
     * Значение этого поля генерируется автоматически
     */
    private LocalDate creationDate;

    /**
     * Поле, отвечающее за героизм человека
     * Поле не может быть null
     */
    private Boolean realHero;

    /**
     * Поле, отвечающее за наличие у человека зубочистки
     */
    private boolean hasToothpick;

    /**
     * Поле, хранящее скорость удара человека
     * Максимальное значение поля: 712
     * Поле может быть null
     */
    private Integer impactSpeed;

    /**
     * Поле, хранящее тип оружия человека
     * Поле может быть null
     */
    private WeaponType weaponType;

    /**
     * Поле, хранящее настроение человека
     * Поле может быть null
     */
    private Mood mood;

    /**
     * Поле, хранящее объект "машина"
     * Поле может быть null
     */
    private Car car = new Car();

    /**
     * Конструктор класса
     * @param setIdAutomatically если true, то значение ID устанавливается автоматически в конструкторе
     */
    public HumanBeing(boolean setIdAutomatically) {
        if (setIdAutomatically) {
            setId();
        }
        creationDate = LocalDate.now();
    }

    /**
     * @return ID человека
     */
    public long getId() {
        return id;
    }

    /**
     * Метод, устанавливающий ID в зависимости от idCounter
     */
    public void setId() {
        this.id = idCounter++;
    }

    /**
     * @return имя человека
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, позволяющий задать имя человека
     * @param name значение имени
     */
    public void setName(String name) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Введено неправильное имя или передано null значение");
        }
        this.name = name;
    }

    /**
     * @return координаты человека
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, позволяющий задать значение координат человека
     * @param coordinates значение координат
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    /**
     * @return дата создания человека
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Метод, позволяющий задать дату создания координат человека
     * @param creationDate дата создания
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = LocalDate.parse(creationDate);
    }

    /**
     * @return героизм человека
     */
    public Boolean isRealHero() {
        return realHero;
    }

    /**
     * Метод, позволяющий задать героизм человека
     * @param realHero героизм человека в строковом формате
     */
    public void setRealHero(String realHero) {
        if (!"false".equalsIgnoreCase(realHero) && !"true".equalsIgnoreCase(realHero)) {
            throw new IllegalArgumentException("Передано неправильное значение героизма человека");
        }
        this.realHero = "true".equalsIgnoreCase(realHero);
    }

    /**
     * @return наличие зубочистки у человека
     */
    public boolean isHasToothpick() {
        return hasToothpick;
    }

    /**
     * Метод, позволяющий задать наличие зубочистки у человека
     * @param hasToothpick нализчие зубочистки у человека в строковом формате
     */
    public void setHasToothpick(String hasToothpick) {
        if (!"false".equalsIgnoreCase(hasToothpick) && !"true".equalsIgnoreCase(hasToothpick)) {
            throw new IllegalArgumentException("Передано неправильное значение наличия у человека зубочистки");
        }
        this.hasToothpick = "true".equalsIgnoreCase(hasToothpick);
    }

    /**
     * @return скорость удара человека
     */
    public Integer getImpactSpeed() {
        return impactSpeed;
    }

    /**
     * Метод, позволяющий задать значение координат человека
     * @param impactSpeed скорость удара в строковом формате
     */
    public void setImpactSpeed(String impactSpeed) {
        if ("".equals(impactSpeed)) {
            this.impactSpeed = null;
        } else {
            int newImpactSpeed = Integer.parseInt(impactSpeed);
            if (newImpactSpeed > MAX_SPEED) {
                throw new IllegalArgumentException("Значение скорости удара не может быть больше 712");
            }
            this.impactSpeed = newImpactSpeed;
        }
    }

    /**
     * @return тип оружия человека
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Метод, позволяющий задать тип оружия человека
     * @param weaponType тип оружия в строковом формате
     */
    public void setWeaponType(String weaponType) {
        String newWeaponType = weaponType.toUpperCase();
        if ("".equals(newWeaponType)) {
            this.weaponType = null;
        } else {
            this.weaponType = WeaponType.valueOf(newWeaponType);
        }
    }

    /**
     * @return настроение человека
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * Метод, позволяющий задать настроение человека
     * @param mood настроение в строковом формате
     */
    public void setMood(String mood) {
        String newMood = mood.toUpperCase();
        if ("".equals(newMood)) {
            this.mood = null;
        } else {
            this.mood = Mood.valueOf(newMood);
        }
    }

    /**
     * @return машина человека
     */
    public Car getCar() {
        return car;
    }

    /**
     * Метод, позволяющий задать машину человека
     * @param car значение машины
     */
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public int compareTo(HumanBeing o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
            result = this.impactSpeed.compareTo(o.impactSpeed);
        }
        return result;
    }

    @Override
    public String toString() {
        return id + ". " + name + ", X: "
                + coordinates.getX() + ", Y: " + coordinates.getY()
                + ", CREATION DATE: " + creationDate + ", REAL HERO: " + realHero
                + ", HAS TOOTHPICK: " + hasToothpick + ", IMPACT SPEED: " + impactSpeed
                + ", WEAPON TYPE: " + weaponType + " MOOD: " + mood + ", CAR INFO: " + (car == null ? "no car" : car.toString());
    }
}
