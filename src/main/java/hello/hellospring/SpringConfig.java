package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 자바 코드로 직접 집어 넣기
 *      1) 과거에는 XML로 설정하기도 했지만 최근에는 잘 사용 X
 *      2) DI에는 필드 주입(생성자 없이 필드에다가 @Autowired -> 권장 X), setter 주입(public 노출됨. 문제 발생 가능), 생성자 주입 세 가지 방법이 있다.
 *      의존 관게가 실행 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장
 *      3) 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 등은 컴포넌트 스캔을 사용.
 *         정형화 되지 않거나 상황에 따라 구현 클래스를 변경해야할 시 직접 등록.
 *      4) @Autowired를 통한 DI는 스프링이 관리하는 객체에서만 동작한다. 내가 직접 생성한 객체에서는 동작하지 않음.
 *
 */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}