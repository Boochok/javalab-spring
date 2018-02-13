package lab.model;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "person")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component("person")
public class UsualPerson implements Person {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    private int age;
    private float height;
    private boolean programmer;
    @Wither
    private boolean broke;
    private List<Contact> contacts;

}