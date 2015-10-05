package no.kantega.unittesting.exercises.dictionary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Dictionary implements Iterable<Dictionary.Entry> {

    private Map<String, Object> entries = new HashMap<>();

    public void add(String key, Object value) {
        // TODO: finish this :)
    }

    @Override
    public Iterator<Entry> iterator() {
        return new Iterator<Entry>() {

            private Iterator<String> keys;

            {
                keys = entries.keySet().iterator();
            }

            @Override
            public boolean hasNext() {
                return keys.hasNext();
            }

            @Override
            public Entry next() {
                String nextKey = keys.next();
                return new Entry(nextKey, entries.get(nextKey));
            }
        };
    }

    static class Entry {
        private String key;
        private Object value;

        Entry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }
}
