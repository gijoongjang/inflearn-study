package com.study.springbasic;

import com.study.springbasic.member.Grade;
import com.study.springbasic.member.Member;
import com.study.springbasic.member.MemberService;
import com.study.springbasic.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        AppConfig appConfig = new AppConfig();

//        MemberService memberService = new MemberServiceImpl();
//        MemberService memberService = appConfig.memberService();
        MemberService memberService = applicationContext.getBean("memberrService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
