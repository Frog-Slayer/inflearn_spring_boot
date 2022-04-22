package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    /**
        Q. 그럼 spring을 안 쓰는 이런 테스트(단위 테스트)는 쓸 필요가 없는 거 아닌가요?
        A. Spring을 쓰는 경우, Test case가 많아지면 너무 느려짐. 보통의 경우 좋은 테스트는 좋은 단위 테스트.
     */
    MemberService memberService;
    MemoryMemberRepository memberRepository;


    /**
     * memberRepository랑 memberService가 같은 instance를 사용하게 (dependency injection)
     */
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void duplicateMemberException(){
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");

        /**
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
        }
        */

    }

    @Test
    void findMembers() {


    }

    @Test
    void findOne() {
    }
}