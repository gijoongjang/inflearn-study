package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest reqeust, HttpServletResponse response) throws ServletException, IOException {
        String username = reqeust.getParameter("username");
        int age = Integer.parseInt(reqeust.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);
        reqeust.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = reqeust.getRequestDispatcher(viewPath);
        dispatcher.forward(reqeust, response);
    }
}
