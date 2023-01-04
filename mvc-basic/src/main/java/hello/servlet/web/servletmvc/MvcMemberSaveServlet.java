package hello.servlet.web.servletmvc;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberSaveSerlvet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터 보관
        req.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";	// WEB-INF 이하의 자원들은 컨트롤러를 통해서 호출한다.
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath); // 다른 servlet이나 JSP로 이동 가능
        dispatcher.forward(req, res);
        // redirect: 클라이언트가 다시 요청.
        // forward: 서버에서 호출하고 끝. 클라이언트가 인지 x.
    }
}
