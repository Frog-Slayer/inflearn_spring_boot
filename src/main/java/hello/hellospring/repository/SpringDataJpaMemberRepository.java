package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository ~ 쓰면 인터페이스만 만들어 놔도 자동으로 bean에 올려줌.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


    @Override
    Optional<Member> findByName(String name);

    /**
     *     나머지 findById, findAll, save등의 공통 interface는 이미 JpaRepository에 만들어져 있음!
     *     findBy0000으로 하면 ~ JPQL select m from Member m where m.0000 = ? 이 짜지고 SQL로 바뀌어서 전달됨. ㄷㄷ
     *     findBy0000And0000, findBy0000Or0000도 가능 ㄷㄷ
     *     단순한 것들은 interface name만으로도 해결됨.
     */


}
