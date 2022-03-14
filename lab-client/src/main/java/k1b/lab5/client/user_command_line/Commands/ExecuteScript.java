package k1b.lab5.client.user_command_line.Commands;

import k1b.lab5.client.Config;
import k1b.lab5.client.abstractions.AbstractCommand;
import k1b.lab5.client.user_command_line.CommandListener;
import k1b.lab5.client.user_command_line.CommandManager;
import k1b.lab5.client.user_command_line.ErrorMessage;
import k1b.lab5.client.user_command_line.ExecutableFileReader;
import k1b.lab5.client.user_command_line.SuccessMessage;
import k1b.lab5.client.utils.SmartSplitter;
import k1b.lab5.client.utils.TextSender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExecuteScript extends AbstractCommand {

    CommandListener commandListener;

    public ExecuteScript() {
        super("execute_script", "Выполнить скрипт из файла, принимает на вход один аргумент [file_path]",1);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            String fileName = args[0];
            try {
                commandListener = new CommandListener(initializeFile(fileName));
            } catch (IOException e) {
                return e.getMessage();
            }
            commandListener.readCommands();
            return new SuccessMessage("Скрипт завершил свою работу");
        } else {
            return new ErrorMessage("Передано неверное количество аргументов");
        }
    }

    private InputStream initializeFile(String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }
}