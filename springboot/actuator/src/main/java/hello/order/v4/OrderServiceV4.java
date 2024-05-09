package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Timed(value = "my.order")
@Slf4j
public class OrderServiceV4 implements OrderService {
    private AtomicInteger stock = new AtomicInteger(100);


    @Override
    public void order() {
            log.info("주문");
            stock.decrementAndGet();
                this.sleep(500);
    }

    @Override
    public void cancel() {
            log.info("취소");
            stock.incrementAndGet();
            this.sleep(200);
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
    private static void sleep(int i) {
        try {
            Thread.sleep(i + new Random().nextInt(200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
