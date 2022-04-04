package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 스프링 빈에 등록하는 2가지 방법 중 하나(컴포넌트 스캔) >> 들어가보면 @Component annotation이 포함되어 있음.
 * 다른 하나는 자바 코드로 직접 등록하는 방법.
 * >> Spring을 쓸 거면 대부분 스프링 빈에 등록해서 사용하는 게 좋음.
 * Q. 아무데나 @Component가 있어도 되나요?
 * A. 기본적으로 안 됨. 이 예제에서는 hello.hellospring 패키지 이하(HelloSpringApplication의 @SpringBootApplication 참고)
 *    를 뒤져서 등록함. 그 외에는 기본적으로는 X.
 * 참고) 스프링 빈에 등록 시 싱글톤으로 등록(유일하게 등록, 공유. 같은 스프링 빈이면 모두 같은 인스턴스). 특별한 경우 아니면 싱글톤으로 등록 사용.
 */
@Controller
public class MemberController {
    /**
     * private final MemberService memberService = new MemberService();
     * 로 쓸 수도 있지만, MemberService는 다른 데에서도 동일하게 쓸 수 있음. 새로운 instance를 만들 동일한 필요없이 하나만 있어도 됨.
     */
    private final MemberService memberService;

    /**
     * @Autowired 스프링 빈에 등록되어있는 MemberService 객체를 끌어 넣어서 연결해 줌(Dependency injection)
     */
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }


    //등록 시 post 조회 시 get
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
