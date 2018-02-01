package commons;

import lab.model.SimpleContact;
import lab.model.SimpleCountry;
import lab.model.UsualPerson;

import java.util.Arrays;

public class Tests {
    public static String APPLICATION_CONTEXT_XML_FILE_NAME =
            "application-context.xml";

    public static UsualPerson getExpectedPerson() {
        return new UsualPerson(
                1,
                "John",
                "Smith",
                new SimpleCountry(1, "Russia", "RU"),
                35,
                Arrays.asList(
                        new SimpleContact("TELEPHONE", "222-33-22"),
                        new SimpleContact("EMAIL","jkhafg@kjhsd.ru")));
    }
}
