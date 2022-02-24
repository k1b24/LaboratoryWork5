package k1b.lab5.client.entities;

/**
 * Класс описывающий объект машина
 */
public class Car {

    /**
     * Поле отвечающее за крутость машины, может принимать значение null
     */
    private Boolean carCoolness; //Поле может быть null

    /**
     * Поле, хранящее в себе скорость машины
     */
    private int carSpeed;

    /**
     * @return Скорость машины
     */
    public int getCarSpeed() {
        return carSpeed;
    }

    /**
     * Метод, позволяющий задать скорость машины
     * @param newSpeed новая скорость в строковом формате
     */
    public void setCarSpeed(String newSpeed) {
        this.carSpeed = Integer.parseInt(newSpeed);
    }

    /**
     * @return Крутость машины
     */
    public Boolean getCarCoolness() {
        return carCoolness;
    }

    /**
     * Метод, позволяющий задать крутость машины
     * @param newCool
     */
    public void setCarCoolness(String newCool) {
        if ("".equals(newCool) || "null".equals(newCool)) {
            this.carCoolness = null;
            return;
        }
        if (!"false".equalsIgnoreCase(newCool) && !"true".equalsIgnoreCase(newCool)) {
            throw new IllegalArgumentException("Передано неправильное значение, машина крутая или нет?");
        }
        this.carCoolness = "true".equalsIgnoreCase(newCool);
    }

    @Override
    public String toString() {
        return "CAR COOLNESS: " + this.getCarCoolness() + " CAR SPEED: " + this.getCarSpeed();
    }
}
