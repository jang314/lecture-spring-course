package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0() {
        Hello target = new Hello();
        // 공통 로직1시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드가 다름.
        log.info("result={}", result1);

        String result2 = target.callB();
        log.info("result={}", result2);
        //공통 로직2시작
    }

    @Test
    void reflectionTest1 () throws Exception{
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);

        log.info("result1={}", result1);

        // callA메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);

        log.info("result1={}", result2);


    }
    @Test
    void reflectionTest2 () throws Exception{
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dyamicCall(methodCallA, target);


        // callA메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dyamicCall(methodCallB, target);
    }

    private void dyamicCall(Method method, Object target) throws Exception{
        log.info("start");
        // callA메서드 정보
        Object result1 = method.invoke(target);

        log.info("result1={}", result1);
    }
    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
