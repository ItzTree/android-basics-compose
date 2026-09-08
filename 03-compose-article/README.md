# 03. Compose Article

Jetpack Compose 소개 글을 보여주는 화면. 이미지 + 제목 + 본문 두 단락.

## 배운 것

- 여러 `Text`를 `Column`으로 세로 배치
- `TextAlign.Justify`로 양쪽 정렬
- 데이터를 파라미터로 받는 재사용 가능한 컴포저블 분리
- `modifier` 파라미터를 받아 루트에 전달하는 관례

## 메모

### 리소스를 조달하는 쪽과 그리는 쪽을 나눈다

```kotlin
@Composable
fun ComposeArticleApp() {          // 데이터 조달
    ArticleCard(
        title = stringResource(R.string.jetpack_compose_tutorial),
        imagePainter = painterResource(R.drawable.bg_compose_background),
        ...
    )
}

@Composable
fun ArticleCard(title: String, ..., modifier: Modifier = Modifier) {  // 렌더링만
    Column(modifier = modifier) { ... }
}
```

`ArticleCard`는 안드로이드 리소스를 전혀 모른다.
덕분에 임의의 문자열로 카드를 여러 장 찍을 수 있고,
아주 긴 제목이나 빈 본문 같은 경우를 Preview에서 즉석으로 확인할 수 있다.

`ComposeArticleApp`에 직접 파라미터를 달아도 동작은 같지만,
그러면 이 경계가 사라져서 리소스를 아는 컴포저블 하나만 남는다.

### `modifier`는 받았으면 반드시 전달한다

```kotlin
fun ArticleCard(..., modifier: Modifier = Modifier) {
    Column() { ... }              // X — 받아놓고 버림
    Column(modifier = modifier) { ... }  // O
}
```

전달을 빼먹으면 호출하는 쪽이 `ArticleCard(modifier = Modifier.padding(8.dp))`를 넘겨도
조용히 무시된다. 에러가 안 나서 더 찾기 어렵다.

### strings.xml의 아포스트로피 이스케이프

Android Studio의 "Extract string resource"(전구 버튼)가
코드의 `app\'s`를 XML로 옮기면서 `app\\\'s`로 한 번 더 이스케이프하는 경우가 있다.
XML에서 `\\`는 백슬래시 한 개로 해석되므로 화면에 `app\'s`처럼 백슬래시가 그대로 보인다.
`\'` 하나면 충분하다.
