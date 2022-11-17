# Spring MVC part1

## 스프링 MVC 구조

1. Handler 매핑을 통해 요청 url에 매핑된 핸들러(컨트롤러) 조회한다.
2. 핸들러를 처리할 수 있는 핸들러 어댑터를 조회하여 핸들러 어댑터를 실행한다.
3. 핸들러 어댑터가 핸들러의 반환 정보를 ModleAndView 형태로 변환해서 반환한다.
4. viewResolver 호출 : jsp의 경우에는 InternalResourceviewResolver가 자동 등록 및 사용된다.
5. viewResolver가 View 객체를 반환한다.
6. view를 렌더링 한다.

## DispatcherServlet

적합한 컨트롤러에 위임해주는 프론트 컨트롤러(Front Controller)<br/>
디스패처 서블릿을 통해 요청을 처리할 컨트롤러를 찾아서 위임하고 컨트롤러가 반환값을 반환한다.<br/>

## 동작 과정

1. 핸들러 어댑터 호출    //논리 뷰 이름을 얻는다.
2. ViewResolver 호출    //InternalResourceViewResolver 호출
3. InternalResourceView //forward() 호출해 처리하는 경우 사용
4. view.render()        //jsp 실행

