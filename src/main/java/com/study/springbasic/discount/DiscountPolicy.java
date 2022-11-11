package com.study.springbasic.discount;

import com.study.springbasic.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
