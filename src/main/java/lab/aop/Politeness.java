package lab.aop;

import lab.model.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import lab.model.Squishee;

@Aspect
public class Politeness {

    @Before("execution(* sellSquishee(..)) && args(person)")
    public void sayHello(Person person) {
        System.out.printf("Hello %s. How are you doing?%n", person.getName());
    }

    @AfterReturning(pointcut = "execution(* sellSquishee(..))",
            returning = "retVal", argNames = "retVal")
    public void askOpinion(Squishee retVal) {
        System.out.printf("Is %s Good Enough?%n", retVal.getName());
    }

    @AfterThrowing("execution(* sellSquishee(..))")
    public void sayYouAreNotAllowed() {
        System.out.println("Hmmm... \n");
    }

    @After("execution(* sellSquishee(..))")
    public void sayGoodBye() {
        System.out.println("Good Bye! \n");
    }

    @Around("execution(* sellSquishee(..))")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Hi! \n");
        Object retVal = pjp.proceed();
        System.out.println("See you! \n");
        return retVal;
    }

}