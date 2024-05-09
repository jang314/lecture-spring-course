package hello.aop.proxyvs;

import hello.aop.order.aop.member.MemberService;
import hello.aop.order.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false);// jdk 동적프록시

        //프록시 인터페이스를 성공
        MemberService memberServiceProxy = (MemberService)factory.getProxy();

        // JDK동적 프록시를 구현 클래스로 캐스팅시도 실패, ClassCast 예외발생
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });



    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true);// jdk 동적프록시

        //프록시 인터페이스를 성공
        MemberService memberServiceProxy = (MemberService)factory.getProxy();

        // JDK동적 프록시를 구현 클래스로 캐스팅시도 성공, ClassCast 예외발생
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;



    }
}
