package aop;

import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static commons.Tests.fromSystemOut;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
class AopAspectJTest {

    @Autowired
    private Bar bar;

    @Autowired
    private Person person;

    private String out;

    @BeforeEach
    void setUp() throws Exception {
        out = fromSystemOut(() -> bar.sellSquishee(person));
    }

    @Test
    void testBeforeAdvice() {
        assertTrue("Before advice is not good enought...", out.contains("Hello"));
        assertTrue("Before advice is not good enought...", out.contains("How are you doing?"));
    }

    @Test
    void testAfterAdvice() {
        assertTrue("After advice is not good enought...", out.contains("Good Bye!"));
    }

    @Test
    void testAfterReturningAdvice() {
        assertTrue("Customer is broken", out.contains("Good Enough?"));
    }

    @Test
    void testAroundAdvice() {
        assertTrue("Around advice is not good enought...", out.contains("Hi!"));
        assertTrue("Around advice is not good enought...", out.contains("See you!"));
    }

    @Test
    void testAllAdvices() {
        assertFalse(bar instanceof ApuBar, "barObject instanceof ApuBar");
    }
}