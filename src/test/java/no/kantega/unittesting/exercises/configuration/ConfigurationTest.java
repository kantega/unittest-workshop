package no.kantega.unittesting.exercises.configuration;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * http://www.manning.com/EffectiveUnitTesting/
 */
public class ConfigurationTest {

    @Test
    public void testParsingCommandLineArguments() {

        String[] args = { "-f", "hello.txt", "-v", "--version" };
        Configuration c = new Configuration();
        c.processArguments(args);

        assertEquals("hello.txt", c.getFileName());
        assertFalse(c.isDebuggingEnabled());
        assertFalse(c.isWarningsEnabled());
        assertTrue(c.isVerbose());
        assertTrue(c.shouldShowVersion());

        c = new Configuration();
        try {
            c.processArguments(new String[] { "-f" });
            fail("Should have failed");
        } catch (InvalidArgumentException e) {
            //expected
        }


    }
}
