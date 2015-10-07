package no.kantega.unittesting.exercises.dictionary;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * http://www.manning.com/EffectiveUnitTesting/
 */
public class DictionaryTest {

    @Test
    public void returnsAnIteratorForContents() {

        Dictionary dictionary = new Dictionary();
        dictionary.add("A", 3L);
        dictionary.add("B", "21");

        assertContains(dictionary, "A", 3L);
        assertContains(dictionary, "B", "21");

    }

    private void assertContains(Dictionary dictionary, String key, Object value) {
        for (Dictionary.Entry entry : dictionary) {
            if (key.equals(entry.getKey())) {
                assertEquals(value, entry.getValue());
                return;
            }
        }
        fail("Iterator didn't contain " + key + " => " + value);
    }
}
