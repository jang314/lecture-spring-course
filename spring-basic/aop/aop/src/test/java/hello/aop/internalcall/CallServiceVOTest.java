package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceVOTest {
    @Autowired
    private CallServiceVO callServiceVO;
    @Test
    void external() {
        callServiceVO.external();
     log.info("target={}", callServiceVO.getClass());
    }

    @Test
    void internal() {
        callServiceVO.internal();
    }
}