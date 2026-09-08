# 02. Happy Birthday

생일 축하 카드 화면. 배경 이미지 위에 축하 문구와 서명을 얹는다.

## 배운 것

- `Column` / `Box`로 레이아웃 쌓기
- `Image`와 `ContentScale.Crop`으로 배경 채우기
- `stringResource`로 하드코딩 문자열 분리
- `textAlign`과 `Modifier.align`의 차이

## 메모

### `textAlign`과 `Modifier.align`은 다른 층위의 정렬

`textAlign`은 **Text가 실제로 측정된 자기 너비 안에서 줄(line)을 정렬**하고,
`Modifier.align`은 **Text 컴포저블이라는 박스 자체를 부모 안에서 배치**한다.

```kotlin
Text(
    text = from,
    textAlign = TextAlign.Center,  // 이것만으로는 움직이지 않는다
)
```

짧은 한 줄짜리 텍스트는 wrap content로 동작해서 자기 너비가 글자 폭만큼으로 확정된다.
정렬할 여백이 0이므로 `Center`든 `Start`든 결과가 같다. `fillMaxWidth()`로 너비를 늘려야 비로소 눈에 보인다.

측정 순서로 보면 이렇다.
부모가 제약을 준다 → Text가 그 안에서 자기 너비를 정한다(Modifier가 개입) → 그 너비 안에서 `textAlign`이 줄을 정렬한다 → 확정된 박스를 `align`이 부모 안에 배치한다.

여러 줄일 때는 `textAlign`이 줄 단위로 각각 적용되므로,
보통은 `Column`의 `horizontalAlignment`와 `textAlign`을 함께 쓴다.
