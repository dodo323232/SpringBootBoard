[README.md](https://github.com/user-attachments/files/32433170/README.md)
# Spring Boot 기초 정리

## 시작하기

Spring 프로젝트는 [start.spring.io](https://start.spring.io)에 들어가서 필요한 의존성을 선택한 뒤 다운로드해서 시작한다.

---

1. 백엔드 및 통신 핵심 용어 정리
  - API (Application Programming Interface): 프로그램과 프로그램이 서로 데이터를 주고받기 위한 규약 및 약속입니다.
  - REST API: HTTP 메서드(GET, POST, PUT, DELETE 등)를 활용하여 URI에 데이터 조작 의도를 명확히 표현하는 웹 API 설계 스타일입니다.
  - JSON (JavaScript Object Notation): 클라이언트와 서버 간 데이터를 주고받을 때 사용하는 Key-Value 형태의 표준 텍스트 포맷입니다.
  - Lombok (롬복): Getter, Setter, 기본 생성자 등을 어노테이션 한 줄로 자동 생성해 주는 자바 라이브러리입니다.

## 기본 어노테이션

### `@RestController` (클래스 위)

- 이게 붙어 있으면 Spring이 이 클래스를 찾아서 등록한다.
- 메서드가 `return`한 값을 응답으로 내보낸다.
  - 문자열은 글자로, 리스트나 맵은 JSON으로 바뀐다.

### `@RequestMapping` (클래스 위)

- 클래스의 주소 앞부분을 공통으로 지정한다.
- `@RequestMapping("/api")`라고 쓰면 모든 주소 앞에 `/api`가 붙는다.

### `@GetMapping` (메서드 위)

- GET 요청이 이 주소로 오면 메서드가 답하도록 연결해 준다.
- 주소를 적지 않으면 클래스의 기본 주소(루트)에 이 메서드가 연결된다.

---

## GET 좀 더 알아보기

### Path Param

`@PathVariable`은 메서드의 **매개변수 괄호 안**에 붙인다.

```java
@GetMapping("/path/{first}/blog/{second}")
public List<Map<String,String>> getPathParams(
        @PathVariable String first,
        @PathVariable String second
) {
    return List.of(
            Map.of("first", first),
            Map.of("second", second)
    );
}
```

- 주소의 값이 변수에 **잠깐 저장**된다. (요청이 끝나면 사라진다.)

### Query Param

주소 `?` 뒤에 붙어 오는 값을 꺼내는 방법이다. 받는 방식은 총 세 가지다.

#### 1) 이름별로 하나씩 받기

```java
@GetMapping("/user-info")
public Map<String, String> getUserInfo(
        @RequestParam String name,
        @RequestParam int age
) {
    return Map.of("name", name, "age", String.valueOf(age));
}
```

#### 2) Map으로 한 번에 받기

```java
@GetMapping("/user-info-map")
public Map<String, String> getUserInfoMap(@RequestParam Map<String, String> params) {
    return params;
}
```

- `?` 뒤의 값이 전부 맵 하나에 담겨서 온다.

#### 3) 클래스로 받기

```java
@GetMapping("/user-info-request")
public List<Map<String,String>> getQueryParamMap(
        UserInfoRequest request
) {
    String name = request.getName();
    String age = request.getAge();
    return List.of(
            Map.of("name", name),
            Map.of("age", age)
    );
}
```

- 매개변수 자리에 `UserInfoRequest request`가 들어가는 이유는, 클래스에 `?` 뒤의 값들을 넣기 위해서다.
- 클래스로 받을 때는 `@Setter`가 **반드시 필요**하다. (Spring이 값을 넣을 때 사용)
- `@Getter`가 있으면 `request.getName()`처럼 값을 바로 꺼내 쓸 수 있다.
- 둘을 한 번에 만들려면 `@Data`를 붙이면 된다.

---

## 패스 파라미터 vs 쿼리 파라미터

| | 패스 파라미터 | 쿼리 파라미터 |
|---|---|---|
| 예시 | `/api/hello/성진` | `/api/search?keyword=스프링&page=2` |
| 어노테이션 | `@PathVariable` | `@RequestParam` |
| 용도 | 특정한 **대상 하나**를 가리킬 때 | 검색어, 페이지 번호, 정렬 방식 같은 **조건**을 얹을 때 |

- **패스 파라미터**: `/api/hello/성진`처럼 주소의 한 조각이 곧 값이다. 예를 들어 `/todos/15`는 "15번 할 일"이라는 특정한 대상 하나를 가리킨다.
- **쿼리 파라미터**: `/api/search?keyword=스프링&page=2`처럼 `?` 뒤에 붙인다. 대상은 `search`로 정해져 있고, 거기에 검색어, 페이지 번호, 정렬 방식 같은 조건을 얹는다.

> **주의:** 패스 파라미터와 쿼리 파라미터를 같이 쓸 때는 쿼리 파라미터(`?...`)가 주소 맨 뒤에 온다. 즉 패스 파라미터가 주소 맨 뒤에 오면 안 된다.
>
> 예: `/api/users/3/todos?page=2` (O)

---

### @postMapping
url에 json을 보내면 @RequestBody를 통해 자바 객체로 변환시켜준다
json은 key-value 형식으로 써야한다

---

### @putMapping

이거도 @RequestBody를 활용하여 자바객체만든다

---

### @DeleteMapping

삭제 신호 보냄

---

### JPA & Entity 개념

1. JPA
   - 자바코드와 데이터베이스 사이에 자동 번역기
   - SQL을 직접 짜지 않고 메서드를 활용해서 SQL문을 쓸 수 있음

2. Entity
   - 자바의 클래스를 데이터베이스 구조로 매핑하는 것
   - @Entity: 클래스 위에 씀으로써 클래스를 데이터베이스 구조로 만들 수 있다 (클래스와 데이터베이스가 1:1로 매핑되는 객체라는 것을 JPA에 알려주는 것)
   -  @Id: DB 테이블의 Primary Key(기본키/고유 식별자)로 지정합니다.
   - @GeneratedValue(strategy = GenerationType.IDENTITY): ID 번호를 DB가 1, 2, 3... 순서대로 자동으로 1씩 증가시켜 주도록 설정합니다.
   - @Column: 컬럼의 제약 조건(NOT NULL, 길이 제한, TEXT 타입 등)을 설정합니다.

3. @NoArgsConstructor는 필수다.
- JPA가 데이터베이스에서 select를 하고 자바 객체로 복원할 때 아무런 파라미터가 없는 기본생성자가 반드시 필요하기 때문이다.
