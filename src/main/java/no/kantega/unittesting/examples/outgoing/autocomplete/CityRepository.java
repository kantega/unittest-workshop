package no.kantega.unittesting.examples.outgoing.autocomplete;

import java.util.List;

public interface CityRepository {

    List<String> findMatches(String startsWith);

}
