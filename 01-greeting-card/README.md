# 01. Greeting Card

첫 Compose 프로젝트. 이름을 받아 인사말을 표시하는 화면.

## 배운 것

- `@Composable` 함수 정의와 호출
- `Surface`로 배경색 지정
- `Modifier.padding`으로 여백 주기
- `@Preview`로 앱 실행 없이 미리보기

## 메모

### 컴포저블은 UI를 "그리는 절차"가 아니라 "결과"를 기술한다

XML + findViewById 방식은 뷰를 만들고 부모에 붙이는 과정을 직접 쓰지만,
Compose는 `Greeting("Meghan")`처럼 **어떤 화면이어야 하는지**만 선언하면 나머지는 컴파일러와 런타임이 처리한다.

### `modifier`는 파라미터로 받아서 넘긴다

```kotlin
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "...", modifier = modifier.padding(24.dp))
}
```

호출하는 쪽이 바깥에서 레이아웃을 조정할 수 있게 하는 관례.
기본값이 빈 `Modifier`라 아무도 안 넘기면 비용이 없다.
