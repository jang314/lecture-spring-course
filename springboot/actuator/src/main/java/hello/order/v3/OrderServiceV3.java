package hello.order.v3;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

@Slf4j
public class OrderServiceV3 implements OrderService {
    private final MeterRegistry registry;
    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV3(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order").register(registry);

        timer.record(() -> {
            log.info("주문");
            stock.decrementAndGet();
                this.sleep(500);
        });
    }

    @Override
    public void cancel() {
        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("order").register(registry);

        timer.record(() -> {
            log.info("취소");
            stock.incrementAndGet();
            this.sleep(200);
        });
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