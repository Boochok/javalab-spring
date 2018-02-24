package lab.model;

import lombok.*;
import lombok.experimental.Wither;

import javax.persistence.*;

@Entity
@Data
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
public class SimpleCountry implements Country {
    @Id
    @Wither
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "code_name")
    private String codeName;
}
