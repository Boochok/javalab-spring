package lab.model;

import static java.lang.String.format;

public interface Person {
    String getName ();
    default String gatHello(Person person) {
        return format("Hello, %s! I`m %s", person.getName(), getName());
    }
}