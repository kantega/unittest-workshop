package no.kantega.unittesting.exercises.generator;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private AtomicLong value = new AtomicLong();

    public long getUniqueId() {
        return value.getAndIncrement();
    }

}
