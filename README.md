# 김영한님 querydsl 강의 학습 정리

## JPQL vs Querydsl
JPQL 
 - 문자(실행 시점 오류)
 - 파라미터 바인딩 직접

Querydsl
 - 코드(컴파일 시점 오류)
 - 파라미터 바인딩 자동처리

## JPAQueryFactory
스프링 프레임워크는 여러 쓰레드에서 동시에 같은 EntityManager에 접근해도, 트랜잭션 마다 별도의 영속성 컨텍스트를 제공하기 때문에, 동시성 문제는 걱정하지 않아도 된다.
```java
@Autowired
EntityManager em;

JPAQueryFactory queryFactory;

@BeforeEach
public void before() {
  queryFactory = new JPAQueryFactory(em);
}
```

## Q-type 활용
Q클래스 인스턴스를 사용하는 2가지 방법
```java
QMember qMember = new QMember("m"); // alias 직접 지정
QMember qMember = QMember.member;   // 기본 인스턴스 사용
```
기본 인스턴스를 static import와 함께 사용
```java
import static study.querydsl.entity.QMember.*;
```
JPQL 로그 확인 방법
```
spring.jpa.properties.hibernate.use_sql_comments: true
```

## 결과 조회
 - fetch() :  리스트 조회, 데이터 없으면 빈 리스트 반환
 - fetchOne() : 단 건 조회 -> 결과가 없으면 null, 결과가 둘 이상이면 com.querydsl.core.NonUniqueResultException 발생
 - fetchFirst() : limit(1).fetchOne()
 - fetchResults() : 페이징 정보 포함, total count 쿼리 추가 수행
 - fetchCount() : count 쿼리로 변경해서 count 수 조회
 
 ## 조인 - on절
 ### 조인 대상 필터링
 ```java
 public void join_on_filtering() throws Exception {
    List<Tuple> result = queryFactory
          .select(member, team)
          .from(member)
          .leftJoin(member.team, team).on(team.name.eq("teamA"))
          .fetch();
}
 ```
on 절을 활용해 조인 대상을 필터링 할 때, inner join을 사용하면, where 절에서 필터링 하는 것과 기능이 동일하다. <br/>
따라서 on 절을 활용한 조인 대상 필터링을 사용할 때, inner join 이면 익숙한 where 절로 해결하고, 정말 outer join이 필요한 경우에만 이 기능을 사용하자

### 연관관계 없는 엔티티 외부 조인
```java
public void join_on_no_relation() throws Exception {
    List<Tuple> result = queryFactory
          .select(member, team)
          .from(member)
          .leftJoin(team).on(member.username.eq(team.name))
          .fetch();
}
```
leftJoin() 부분에 일반 조인과 다르게 엔티티 하나만 들어간다.
 - 일반조인: leftJoin(member.team, team)
 - on조인 : from(member).leftJoin(team).on(xxx)
 
## 서브쿼리
select 절과 where 절에 서브쿼리 가능 <br/>
from 절에서의 서브쿼리(인라인 뷰)는 지원하지 않는다.

### from 절의 서브쿼리 해결방안
1. 서브쿼리를 join으로 변경한다. (가능한 상황도 있고, 불가능한 상황도 있다.)
2. 애플리케이션에서 쿼리를 2번 분리해서 실행한다.
3. nativeSQL을 사용한다.
 
