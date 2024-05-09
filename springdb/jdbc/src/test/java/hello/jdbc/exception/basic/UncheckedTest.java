package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedTest {

    @Test
    void unchecked_catch() {
        Service service = new Service();
        service.callCatch();;
    }
    @Test
    void unchecked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyUncheckedException.class);
    }


    /**
     * RuntimeException을 상속받은 예외는 언체크 예외가 된다.
    */
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException (String message) {
            super(message);
        }
    }

    /**
     * Unchecked예외는
     * 예외를 잡거나, 던지지 않아도된다.
     * 예외를 잡지 않으면 자동으로 밖으로 던진다.
    */
    static class Service {
        Repository repository = new Repository();

        /**
         * 필요한 경우 예외를 잡아 처리하면 된다.
        * */
        public void callCatch() {
            try{
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("예외처리, message={}", e.getMessage(), e);
            }
        }
        /**
         * 예외를 잡지 않아도 된다.
         * 자연스럽게 상위로 넘어간다.
         * 체크 예외가 다르게 throws 예외 선언을 안해도된다.
         * MyUncheckedException를 선언을 해도 안되도 되지만, 선언을 해야
         * 개발자가 이런 예외가 발생한다는 점을 IDE를 통해 인지할 수 있다.
         * 대부분 생략함.
         */
        public void callThrow() throws MyUncheckedException{
            repository.call();
        }


    }

    static class Repository {
        public void call() {
            throw new MyUncheckedException("ex");
        }
    }
}
