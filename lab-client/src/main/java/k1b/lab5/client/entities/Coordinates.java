package k1b.lab5.client.entities;

/**
 * Класс описывающий объект координаты
 */
public class Coordinates {

    /**
     * final поле, отвечающее за минимальное значение координаты по X
     */
    private static final long MIN_X_VALUE = -759;

    /**
     * Поле, хранящее в себе значение координаты по X
     */
    private long x; //Значение поля должно быть больше -759

    /**
     * Поле, хранящее в себе значение координаты по Y
     */
    private float y;

    /**
     * @return координата по X
     */
    public long getX() {
        return this.x;
    }

    /**
     * Метод, позволяющий задать координату по X
     * @param x значение координаты по X в строковом формате
     */
    public void setX(String x) {
        long newX = Long.parseLong(x);
        if (newX <= MIN_X_VALUE) {
            throw new IllegalArgumentException("Значение координаты по X не должно быть меньше -759");
        }
        this.x = newX;
    }

    /**
     * @return координата по Y
     */
    public float getY() {
        return this.y;
    }

    /**
     * Метод, позволяющий задать координату по Y
     * @param y значение координаты по X в строковом формате
     */
    public void setY(String y) {
        float newY = Float.parseFloat(y);
        if (newY == Float.POSITIVE_INFINITY || newY == Float.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException("Передано слишком большое число");
        }
        this.y = newY;
    }
}
