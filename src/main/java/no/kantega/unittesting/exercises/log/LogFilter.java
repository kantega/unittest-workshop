package no.kantega.unittesting.exercises.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LogFilter {

    public void filterErrors(BufferedReader input, PrintWriter output) throws IOException {

        String line = input.readLine();
        while (line != null) {

            if (line.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3} ERROR .*$")) {
                output.println(line);
            }

            line = input.readLine();
        }
    }

}
