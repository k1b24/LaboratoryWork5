package k1b.lab5.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация Command используется над методами, которые реализуют логику консольной команды в интерактивном режиме
 */
@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.METHOD)
public @interface Command {
    String name();

    String args();

    int amountOfArgs() default 0;

    String description();

}
