package com.study.springbasic;

import com.study.springbasic.discount.DiscountPolicy;
import com.study.springbasic.discount.FixDiscountPolicy;
import com.study.springbasic.discount.RateDiscountPolicy;
import com.study.springbasic.member.MemberRepository;
import com.study.springbasic.member.MemberService;
import com.study.springbasic.member.MemberServiceImpl;
import com.study.springbasic.member.MemoryMemberRepository;
import com.study.springbasic.order.OrderService;
import com.study.springbasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
