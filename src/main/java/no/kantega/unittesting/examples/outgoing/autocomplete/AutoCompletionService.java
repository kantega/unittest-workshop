package no.kantega.unittesting.examples.outgoing.autocomplete;

import java.util.List;

public class AutoCompletionService {

    private CityRepository cityRepository;

    public AutoCompletionService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public String getCitiesAsXml(String startsWith) {
        List<String> cities = cityRepository.findMatches(startsWith);
        StringBuilder sb = new StringBuilder();
        sb.append("<cities>");
        for (int i = 0; i < cities.size(); i++) {
            sb.append("<city>");
            sb.append(cities.get(i));
            sb.append("</city>");
        }
        sb.append("</cities>");
        return sb.toString();
    }

}
