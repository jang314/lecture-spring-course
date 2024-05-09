package hello.pay;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRunner implements ApplicationRunner {
    // 스프링이 뜨는 시점에 실행하고 꺼짐
    private final OrderService orderService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        orderService.order(10000);
    }
}
