package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberServiceTest {
 @Autowired MemberService memberService;
 @Autowired MemberRepository memberRepository;
 @Autowired LogRepository logRepository;

 /**
  * memberService @Transactional : OFF
  * memberRepository @Transational : ON
  * logRepository @Transational :ON
  * */
    @Test
    void outerTxOff_success() {
        // given
        String username = "outerTxOff_success";

        //when
        memberService.joinV1(username);

        //when : 모든 데이터가 정상 저장
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
 }

    @Test
    void outerTxOff_fail() {
        // given
        String username = "로그예외_outerTxOff_fail";

        //when
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        //when : 모든 데이터가 정상 저장
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());


    }

    /**
     * memberService @Transactional : OFF
     * memberRepository @Transational : ON
     * logRepository @Transational :ON
     * */
    @Test
    void singleTx() {
        // given
        String username = "outerTxOff_success";

        //when
        memberService.joinV1(username);

        //when : 로그 데이터만 롤백되어야 한다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * memberService @Transactional : ON
     * memberRepository @Transational : ON
     * logRepository @Transational :ON
     * */
    @Test
    void OuterTxOn_success() {
        // given
        String username = "outerTxOff_success";

        //when
        memberService.joinV1(username);
        //when : 모든 데이터가 정상 저장
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }
    /**
     * memberService @Transactional : ON
     * memberRepository @Transational : ON
     * logRepository @Transational :On Exception
     * */
    @Test
    void outerTxOn_fail() {
        // given
        String username = "로그예외_outerTxOff_fail";

        //when
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        //when : 모든 데이터가 전체 롤백되어야 함.
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());


    }

    /**
     * memberService @Transactional : ON
     * memberRepository @Transational : ON
     * logRepository @Transational :On Exception
     * */
    @Test
    void recoverException_fail() {
        // given
        String username = "로그예외_recoverException_fail";

        //when
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> memberService.joinV2(username)).isInstanceOf(UnexpectedRollbackException.class);

        //when : 모든 데이터가 전체 롤백되어야 함.
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());


    }

    /**
     * memberService @Transactional : ON
     * memberRepository @Transational : ON
     * logRepository @Transational :On (Requires_new) Exception
     * */
    @Test
    void recoverException_success() {
        // given
        String username = "로그예외_recoverException_fail";

        //when
        memberService.joinV2(username);

        //when : 모든 데이터가 전체 롤백되어야 함.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());


    }
}