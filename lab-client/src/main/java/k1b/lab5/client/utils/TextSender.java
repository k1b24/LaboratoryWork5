package k1b.lab5.client.utils;

import java.io.PrintStream;

public class TextSender {

    public static final String MESSAGE_COLOR = "\u001B[35m"; //ANSI_PURPLE
    public static final String ERROR_COLOR = "\u001B[31m"; //ANSI_RED
    public static final String ANSI_RESET = "\u001B[0m";
    public static PrintStream printStream = System.out;

    public static void printMessage(String message) {
        printStream.println(MESSAGE_COLOR + message + ANSI_RESET);
    }

    public static void printError(String message) {
        printStream.println(ERROR_COLOR + message + ANSI_RESET);
    }

    public static void changePrintStream(PrintStream newPrintStream) {
        printStream = newPrintStream;
    }
}
