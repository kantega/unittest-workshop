package no.kantega.unittesting.exercises.log;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LogFilterTest {

    private LogFilter logFilter = new LogFilter();

    @Before
    public void setup() {
        logFilter = new LogFilter();
    }

    @Test
    public void shallFindErrorsInLog() throws IOException {

        //given
        String originalLog = "" +
                "2014-05-27 12:53:35,034 ERROR ContextLoader - Context initialization failed\n" +
                "2014-05-27 12:54:09,267 INFO  PushyWrapper - APNS host: gateway.push.apple.com\n" +
                "2014-05-27 15:22:14,629 ERROR TaskUtils$LoggingErrorHandler - Unexpected error occurred in scheduled task.\n";
        BufferedReader input = new BufferedReader(new StringReader(originalLog));
        StringWriter stringWriter = new StringWriter();
        PrintWriter output = new PrintWriter(stringWriter);

        //when
        logFilter.filterErrors(input, output);

        //then
        String filteredLog = stringWriter.toString();
        assertThat(filteredLog, containsString("ERROR ContextLoader"));
        assertThat(filteredLog, not(containsString("INFO  PushyWrapper")));
        assertThat(filteredLog, containsString("ERROR TaskUtils"));


    }

}
