package k1b.lab5.client.utils;

import k1b.lab5.client.abstractions.AbstractMessage;

import java.io.PrintStream;

public class TextSender {

    public static final String MESSAGE_COLOR = "\u001B[32m"; //ANSI_GREEN
    public static final String ERROR_COLOR = "\u001B[31m"; //ANSI_RED
    public static final String ANSI_RESET = "\u001B[0m";
    public static PrintStream printStream = System.out;

    public static void printText(String message) {
        printStream.println(MESSAGE_COLOR + message + ANSI_RESET);
    }

    public static void printError(String message) {
        printStream.println(ERROR_COLOR + message + ANSI_RESET);
    }

    public static void printMessage(AbstractMessage message) {
        printStream.println(message.getMessage());
    }

    public static void changePrintStream(PrintStream newPrintStream) {
        printStream = newPrintStream;
    }
}
