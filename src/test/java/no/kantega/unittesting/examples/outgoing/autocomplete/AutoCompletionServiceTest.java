package no.kantega.unittesting.examples.outgoing.autocomplete;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AutoCompletionServiceTest {

    private CityRepository cityRepository;
    private AutoCompletionService autoCompletionService;
    private List<String> cities;
    private String input;

    @Before
    public void setup() {
        cityRepository = mock(CityRepository.class);
        autoCompletionService = new AutoCompletionService(cityRepository);
        cities = Arrays.asList("Oslo", "Otta");
        input = "O";
    }

    @Test
    public void shallReturnMatchingCitiesAsXml() throws Exception {

        //given
        given(cityRepository.findMatches(input)).willReturn(cities);

        //when
        String citiesAsXml = autoCompletionService.getCitiesAsXml(input);

        //then
        assertMatchingCitiesAsXml(cities, citiesAsXml);

    }

    private void assertMatchingCitiesAsXml(List<String> cities, String citiesAsXml) throws Exception {
        InputSource source = new InputSource(new StringReader(citiesAsXml));
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression xPathExpression = xpath.compile("/cities/city");
        NodeList nodes = (NodeList) xPathExpression.evaluate(source, XPathConstants.NODESET);
        List<String> citiesInResult = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            citiesInResult.add(nodes.item(i).getTextContent());
        }
        Assert.assertEquals(cities, citiesInResult);
    }

}
