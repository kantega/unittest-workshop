package no.kantega.unittesting.exercises.configuration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * http://www.manning.com/EffectiveUnitTesting/
 */
public class ConfigurationTest {

    private Configuration configuration;

    @Before
    public void setup() {
        configuration = new Configuration();
    }

    @Test
    public void testDefaultOptions() {

        assertFalse(configuration.isDebuggingEnabled());
        assertFalse(configuration.isWarningsEnabled());
        assertFalse(configuration.isVerbose());
        assertFalse(configuration.shouldShowVersion());

    }

    @Test
    public void testExplicitOptions() {

        String[] args = {"-f", "hello.txt", "-v", "-d", "-w", "--version"};
        configuration.processArguments(args);

        assertEquals("hello.txt", configuration.getFileName());
        assertTrue(configuration.isDebuggingEnabled());
        assertTrue(configuration.isWarningsEnabled());
        assertTrue(configuration.isVerbose());
        assertTrue(configuration.shouldShowVersion());

    }

    @Test(expected = InvalidArgumentException.class)
    public void testConfigurationErrors() {

        String[] args = {"-f"};

        configuration.processArguments(args);

    }
}
