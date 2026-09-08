# Android Basics with Compose

[Android Basics with Compose](https://developer.android.com/courses/android-basics-compose/course) 코스를 따라가며 만든 실습 프로젝트 모음입니다.
각 폴더는 독립된 Gradle 프로젝트이며, **루트가 아니라 해당 하위 폴더를 Android Studio로 열어야** 합니다.

## 진행 상황

| # | 프로젝트 | 배운 것 |
|---|---------|--------|
| 01 | [Greeting Card](01-greeting-card) | `@Composable` 함수 정의와 호출, `Surface`로 배경 지정, `Modifier.padding`, `@Preview`로 미리보기 |
| 02 | [Happy Birthday](02-happy-birthday) | `Column` / `Box`로 레이아웃 쌓기, `Image`와 `ContentScale.Crop`, `stringResource`로 문자열 분리, `textAlign`과 `align`의 차이 |
| 03 | [Compose Article](03-compose-article) | 여러 `Text`를 `Column`으로 배치, `TextAlign.Justify`, 데이터를 파라미터로 받는 컴포저블 분리, `modifier` 전달 관례 |
| 04 | [Task Manager](04-task-manager) | `Arrangement.Center` + `Alignment.CenterHorizontally`로 화면 정중앙 배치, `FontWeight`로 굵기 지정, `compileSdk` / `targetSdk` / `minSdk`의 차이 |

자세한 학습 메모는 각 프로젝트 폴더의 README에 있습니다.
