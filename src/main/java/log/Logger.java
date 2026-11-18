package log;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private static final String FILE = "gym_log.txt";

    public static void log(String operation, String details) {

        try (FileWriter fw = new FileWriter(FILE, true)) {

            fw.write(
                "[" + LocalDateTime.now() + "] "
                + operation + " -> "
                + details
                + "\n"
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}