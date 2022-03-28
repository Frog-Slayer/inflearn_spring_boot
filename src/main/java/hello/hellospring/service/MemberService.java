package hello.hellospring.service;

import hello.hellospring.domain.Member;

public class MemberRepository {
    private final MemberRepository memberRepository = new MemberRepository();

    public Long join(Member member){
        //중복회원 금지
        memberRepository.findByName(member.getName());

        memberRepository.save(member);


    }

}
