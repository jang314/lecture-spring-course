package hello.order.v1;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV1 implements OrderService {
    private final MeterRegistry registry;

    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV1(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();
    // 메트릭이름
        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order").description("order").register(registry)
                .increment();
    }

    @Override
    public void cancel() {
        stock.incrementAndGet();
        log.info("취소");
        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("cancel")
                .register(registry)
                .increment();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
