package no.kantega.unittesting.exercises.generator;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * http://www.manning.com/EffectiveUnitTesting/
 */
public class IdGeneratorTest {

    private final int threads = 100;
    private final int callsPerThread = 100;
    private IdGenerator generator;

    @Before
    public void setUp() throws Exception {
        generator = new IdGenerator();
    }

    @Test
    public void generatesUniqueIds() throws Exception {

        Set<Long> uniqueValues = Collections.synchronizedSet(new HashSet<>());

        Runnable runnable = () -> {
            for (int i = 0; i < callsPerThread; i++) {
                uniqueValues.add(generator.getUniqueId());
            }
        };

        for (int i = 0; i < threads; i++) {
            new Thread(runnable).start();
        }

        Thread.sleep(500);

        int expectedNoOfValues = threads * callsPerThread;
        assertEquals(expectedNoOfValues, uniqueValues.size());
    }


}
