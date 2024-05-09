package hello.jdbc.repository;

import hello.jdbc.domian.Member;


public interface MemberRepository {
    Member save(Member member) ;
    Member findById(String id) ;
    void update(String memberId, int money);
    void delete(String memberId) ;
}
