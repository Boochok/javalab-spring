package lab.model;

import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import java.util.List;

@Value
@Component("person")
public class UsualPerson implements Person {
    private int id;
    private String firstName;
    private String lastName;
    private Country country;
    private int age;
    private float height;
    private boolean programmer;
    @Wither
    private boolean broke;
    private List<Contact> contacts;
}