# 05. Compose Quadrant

화면을 사분면으로 나눠 각 칸에 Compose 기본 컴포저블의 이름과 설명을 표시한다.

## 배운 것

- `Modifier.weight`로 남은 공간을 비율대로 나누기
- `Column` 안에 `Row`를 중첩해 격자 레이아웃 만들기
- `Modifier.background`로 영역에 배경색 칠하기
- 같은 카드를 파라미터만 바꿔 네 번 재사용

## 메모

### `weight`는 부모의 남은 공간을 비율로 나눈다

```kotlin
Column(modifier = Modifier.fillMaxWidth()) {
    Row(modifier = Modifier.weight(1f)) {        // 세로 절반
        ComposeCard(modifier = Modifier.weight(1f))  // 가로 절반
        ComposeCard(modifier = Modifier.weight(1f))  // 가로 절반
    }
    Row(modifier = Modifier.weight(1f)) { ... }  // 세로 절반
}
```

같은 `weight(1f)`이라도 **부모가 무엇이냐에 따라 방향이 다르다.**
Column 안에서는 세로를, Row 안에서는 가로를 나눈다.
`weight`는 `ColumnScope` / `RowScope` 안에서만 쓸 수 있는 modifier라서 부모 밖에서는 호출조차 안 된다.

값은 비율이다. `1f` / `1f`면 반반, `2f` / `1f`면 2:1이다.

### `modifier`를 안 넘기면 `weight`가 증발한다

처음에 이렇게 짰다.

```kotlin
fun ComposeCard(..., modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier          // ← 받은 modifier 대신 새로 시작
            .fillMaxSize()
            ...
```

호출하는 쪽에서 `Modifier.weight(1f)`을 넘겨도 `ComposeCard`가 버리니 weight가 사라진다.
그러면 각 카드엔 `fillMaxSize()`만 남아서, Row 안의 **첫 번째 카드가 가로 전체를 차지하고 두 번째 카드는 밀려나 안 보인다.**
사분면이 아니라 위아래 두 칸만 나온다.

`Modifier` → `modifier` 한 글자 차이다. `Modifier`도 유효한 값이라 **컴파일 에러가 안 나고 화면을 봐야만 알 수 있다.**
03번에서 "받았으면 반드시 전달한다"고 적었던 게 실제로 터진 사례.

받은 modifier는 체인의 **맨 앞**에 둔다.

```kotlin
modifier = modifier
    .fillMaxSize()
    .background(backgroundColor)
    .padding(16.dp)
```

### modifier는 순서대로 적용된다

위 체인에서 `background` 다음에 `padding`이 오기 때문에 배경색이 칸 전체를 채우고 글자만 안쪽으로 들어간다.
`padding`을 먼저 쓰면 여백 부분엔 배경색이 안 칠해진다.
