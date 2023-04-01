# 김영한님 스프링 mvc 2편 강의 정리

## 메시지 
메시지를 하드코딩으로 적게 된다면 수정사항이 발생할 경우 수십개 이상의 파일을 수정해야한다.<br/>
스프링에서 제공하는 메시지 기능을 사용한다면 이러한 문제를 해결할 수 있다.

## 국제화
메시지에서 더 나아가 나라별로 관리할 수 있다.

## 스프링 메시지 소스 설정
스프링은 기본적인 메시지 관리 기능을 제공한다.
메시지 관리 기능을 사용하려면 스프링이 제공하는 MessageSource를 스프링 빈으로 등록하면 된다.

```java
@Bean
public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames("messages", "errors");
    messageSource.setDefaultEncoding("utf-8");
    return messageSource;
}
```
하지만 스프링 부트를 사용하면 스프링 부트가 MessageSource를 자동으로 스프링 빈으로 등록해준다.
## 스프링부트 메시지 소스 설정
### application.properties
```properties
spring.messages.basename=messages,config.i18n.messages
```

## 스프링 국제화 메시지 선택
메시지 기능은 Locale 정보를 알아야 언어를 선택할 수 있다.<br/>
스프링은 언어 선택시 기본으로 Accept-Language 헤더의 값을 사용한다.

## LocaleResolver
스프링은 Locale 선택 방식을 변경할 수 있도록 LocaleResolver 인터페이스를 제공한다.<br/>
스프링부트는 기본으로 Accpet-Language를 활용하는 AcceptHeaderLocaleResolver를 사용한다.

## LocaleResolver 변경
Locale 선택 방식을 변경하려면 LocaleResolver의 구현체를 변경해서 쿠키나 세션 기반의 Locale 선택 기능을 사용할 수 있다.
