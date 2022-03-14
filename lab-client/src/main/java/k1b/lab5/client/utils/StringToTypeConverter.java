package k1b.lab5.client.utils;

import k1b.lab5.client.entities.enums.Mood;
import k1b.lab5.client.entities.enums.WeaponType;

import java.time.LocalDate;

public class StringToTypeConverter {

    public static Object toObject(Class<?> requiredClass, String value) throws IllegalArgumentException {
        if (Boolean.class == requiredClass || Boolean.TYPE == requiredClass) return Boolean.parseBoolean(value);
        if (Byte.class == requiredClass || Byte.TYPE == requiredClass) return Byte.parseByte(value);
        if (Short.class == requiredClass || Short.TYPE == requiredClass) return Short.parseShort(value);
        if (Integer.class == requiredClass || Integer.TYPE == requiredClass) return Integer.parseInt(value);
        if (Long.class == requiredClass || Long.TYPE == requiredClass) return Long.parseLong(value);
        if (Float.class == requiredClass || Float.TYPE == requiredClass) return Float.parseFloat(value);
        if (Double.class == requiredClass || Double.TYPE == requiredClass) return Double.parseDouble(value);
        if (LocalDate.class == requiredClass) return LocalDate.parse(value);
        if (Mood.class == requiredClass) return Mood.valueOf(value.toUpperCase());
        if (WeaponType.class == requiredClass) return WeaponType.valueOf(value.toUpperCase());
        return value;
    }

}
