package ioc;

import lab.model.UsualPerson;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static commons.Tests.getExpectedPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:aop.xml")
class SpringTCFAppTest {

    @Setter(onMethod = @__({@Autowired}))
    private UsualPerson person;

    @Test
    void testInitPerson() {
        assertEquals(getExpectedPerson(), person);
    }
}