package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {
    @Test
    void checked_test() {
        MyCheckedException.Service service = new MyCheckedException.Service();
        service.callCatch();
    }

    @Test
    void checkedThrow() {
        MyCheckedException.Service service = new MyCheckedException.Service();

        Assertions.assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }
    static class MyCheckedException extends Exception {

        /**
         * Exception을 상속받은 예외는 체크예외가 된다.
         */
        public MyCheckedException(String message) {
            super(message);
        }


        static class Service {
            Repository repository = new Repository();

            /**
             * 예외를 잡아서 처리하는 코드
            */
            public void callCatch() {
                try {
                    repository.call();
                } catch (MyCheckedException e) {
                    // 예외처리 로직
                    log.info("예외처리, message ={}", e.getMessage(), e);
                }
            }

            /**
             * 에크 예외를 밖으로 던지는 코드
             * 체크예외는 예외를 잡지 않고 밖으로 던지려면 throws예외를 메서드에 필수로 선언해야함.
             * @Throws MyCheckedException
            */
            public void callThrow() throws MyCheckedException {
                Repository repository = new Repository();
                repository.call();;
            }
        }
        static class Repository {
            // check예외는 던지거나 trycatch로 묵ㄲ어줘야함
            public void call() throws MyCheckedException {
                throw new MyCheckedException("ex");
            }
        }
    }
}
