# 04. Task Manager

Compose 기초 연습 문제. 할 일을 모두 마쳤다는 완료 화면을 만든다.

## 배운 것

- `Column`의 `verticalArrangement` + `horizontalAlignment`로 화면 정중앙 배치
- `FontWeight.Bold`, `fontSize`로 텍스트 강조
- `compileSdk` / `targetSdk` / `minSdk`의 역할 구분

## 메모

### 화면 정중앙에 놓기

```kotlin
Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,      // 세로 가운데
    horizontalAlignment = Alignment.CenterHorizontally,  // 가로 가운데
)
```

Column은 **세로로 쌓는** 컨테이너라 두 축의 이름이 다르다.
쌓이는 방향(세로)은 `Arrangement`가 자식들 사이의 배분을 정하고,
그 반대 방향(가로)은 `Alignment`가 각 자식의 위치를 정한다.
`Row`는 정확히 반대다.

`fillMaxSize()`가 없으면 Column이 자식 크기만큼만 차지해서
가운데로 보낼 여백 자체가 없다. 02번의 `textAlign`과 같은 이야기다.

### `compileSdk` / `targetSdk` / `minSdk`는 각각 다른 것을 정한다

androidx 라이브러리를 올리다 보면 이런 에러가 난다.

```
Dependency 'androidx.core:core-ktx:1.19.0' requires ... compile against
version 37 or later of the Android APIs. :app is currently compiled against android-36.1.
```

- `compileSdk` — **컴파일할 때** 어느 API까지 쓸 수 있는지. 라이브러리가 요구하는 건 이것뿐이다.
- `targetSdk` — 새 OS의 **동작 변경**을 적용받을지. 올리면 권한·백그라운드 제한 등 런타임 동작이 바뀐다.
- `minSdk` — **설치 가능한** 최소 기기 버전.

그래서 이 에러는 `compileSdk`만 올리면 해결된다.
셋을 한꺼번에 올리면 앱 동작까지 바뀌므로 필요한 것만 건드린다.
