package no.kantega.unittesting.exercises.dictionary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * http://www.manning.com/EffectiveUnitTesting/
 */
public class DictionaryTest {

    @Test
    public void returnsAnIteratorForContents() {

        Dictionary dictionary = new Dictionary();
        dictionary.add("A", 3L);
        dictionary.add("B", "21");

        for (Dictionary.Entry entry : dictionary) {
            if ("A".equals(entry.getKey())) {
                assertEquals(3L, entry.getValue());
            } else if ("B".equals(entry.getKey())) {
                assertEquals("21", entry.getValue());
            }
        }

    }
}
