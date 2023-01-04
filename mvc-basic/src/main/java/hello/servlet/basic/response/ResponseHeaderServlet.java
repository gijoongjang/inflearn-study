package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);

        resp.setHeader("Content-Type", "text/plain;charset-utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate" );
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello"); //custom

        content(resp);
        cookie(resp);
        redirect(resp);

        PrintWriter pw = resp.getWriter();
        pw.println("ok");
    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(2);
    }

    private void cookie(HttpServletResponse response) {
        //기존 방법
//        response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        response.sendRedirect("/basic/hello-form.html");
    }
}
