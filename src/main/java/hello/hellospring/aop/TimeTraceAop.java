package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    /**
     * 핵심 관심 사항과 공통 관심 사항을 분리
     * --> 핵심 관심 사항을 깔끔하게 관리
     * --> 공통 관심 사항 변경 시 쉽게 수정할 수 있음.
     * --> 적용 대상도 여기서 바꿀 수 있음.
     * ==> spring bean에 등록할 때 아래의 적용 대상들에 대해, 원 대상이 아니라 그 proxy를 등록함.
     * ==> jointPoint.proceed()에서 원래 대상들이 불러짐.
     */
    @Around("execution(* hello.hellospring..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally{
            long finsh = System.currentTimeMillis();
            long timeMs = finsh - start;
            System.out.println("End: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }


}
