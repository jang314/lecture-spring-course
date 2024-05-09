package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StatefuleServiceTest {
    @Test
    void statefuleServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        StatefulService statefulService1 =   ac.getBean(StatefulService.class);
        StatefulService statefulService2 =   ac.getBean(StatefulService.class);

        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        // ThreadA 사용자가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }



}
