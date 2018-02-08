package ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static commons.Tests.APPLICATION_CONTEXT_XML_FILE_NAME;
import static commons.Tests.getExpectedPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleAppTest {
    private BeanFactory context = new ClassPathXmlApplicationContext(
            APPLICATION_CONTEXT_XML_FILE_NAME);

    @Test
    void testInitPerson() {
        assertEquals(getExpectedPerson(),
                // FYI: Another way to achieve the bean
                // context.getBean(UsualPerson.class)
                context.getBean("person")
        );
    }
}
