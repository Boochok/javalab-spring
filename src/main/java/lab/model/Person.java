package lab.model;

import java.util.List;

import static java.lang.String.format;

public interface Person {
    String getFirstName();
    String getLastName();
    Country getCountry();
    int getAge();
    float getHeight();
    boolean isProgrammer();
    boolean isBroke();
    List<Contact> getContacts();
    Person setBroke(boolean isBroke);

    default String getName() {
        return format("%s %s", getFirstName(), getLastName());
    }

    default String gatHello(Person person) {
        return format("Hello, %s! I`m %s", person.getName(), getName());
    }
}
