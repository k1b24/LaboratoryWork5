package k1b.lab5.client.entities;

import javax.validation.constraints.Min;

/**
 * Класс описывающий объект координаты
 */
public class Coordinates {

    @Min(-759)
    private long x; //Значение поля должно быть больше -759
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
    public void setX(long x) {
        this.x = x;
    }

    /**
     * @return координата по Y
     */
    public float getY() {
        return this.y;
    }

    /**
     * Метод, позволяющий задать координату по Y
     * @param y значение координаты по X
     */
    public void setY(float y) {
        this.y = y;
    }
}
