package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

public class OmponentFilterAppConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac =  new AnnotationConfigApplicationContext();
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        // Assertions.assertThat(beanA).isNotNull();
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }
    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes =  MyIncludeComponent.class),
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static class ComponentFilterAppConifg {

    }
}
