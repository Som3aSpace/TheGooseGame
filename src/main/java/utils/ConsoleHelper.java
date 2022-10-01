package utils;

import java.util.Scanner;

public class ConsoleHelper {
    /**
     * this function takes input is the title that ask user to write input
     * @return the command that user have just written
     */
    public String readCommand() {
        String cmd = null;
        try {
            write(System.lineSeparator());
            Scanner scanner = new Scanner(System.in);
            cmd = scanner.nextLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cmd;
    }

    public void write(String outputText){
        System.out.print(outputText);
    }
}
