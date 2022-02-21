package k1b.lab5.client.entities;

/**
 * Класс описывающий объект машина
 */
public class Car {

    /**
     * Поле отвечающее за крутость машины, может принимать значение null
     */
    private Boolean cool; //Поле может быть null

    /**
     * Поле, хранящее в себе скорость машины
     */
    private int speed;

    /**
     * @return Скорость машины
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Метод, позволяющий задать скорость машины
     * @param newSpeed новая скорость в строковом формате
     */
    public void setCarSpeed(String newSpeed) {
        this.speed = Integer.parseInt(newSpeed);
    }

    /**
     * @return Крутость машины
     */
    public Boolean getCool() {
        return cool;
    }

    /**
     * Метод, позволяющий задать крутость машины
     * @param newCool
     */
    public void setCarCoolness(String newCool) {
        if ("".equals(newCool) || "null".equals(newCool)) {
            this.cool = null;
            return;
        }
        if (!"false".equalsIgnoreCase(newCool) && !"true".equalsIgnoreCase(newCool)) {
            throw new IllegalArgumentException("Передано неправильное значение, машина крутая или нет?");
        }
        this.cool = "true".equalsIgnoreCase(newCool);
    }

    @Override
    public String toString() {
        return "CAR COOLNESS: " + this.getCool() + " CAR SPEED: " + this.getSpeed();
    }
}
