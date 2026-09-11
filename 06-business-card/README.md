# 06. Business Card

명함 앱. 위쪽엔 로고·이름·직함, 아래쪽엔 연락처를 배치한다. 정답 코드 없이 직접 설계한 자유 과제.

## 배운 것

- `weight`로 한 영역은 가운데, 다른 영역은 아래로 보내기
- 정렬 단위를 맞추기 위해 여러 줄을 하나의 Column으로 묶기
- `Icon` + Material 아이콘 사용 (의존성 추가, import 함정)
- `Color(0xAARRGGBB)`의 알파 채널

## 메모

### 가운데 정렬은 자식마다 따로 적용된다

부모 Column에 `horizontalAlignment = CenterHorizontally`를 걸고 연락처 줄 두 개를 바로 넣었더니,
길이가 다른 두 줄이 **각자** 가운데로 가서 아이콘 위치가 어긋났다.

```
      📞 +82 10-0000-0000
    ✉️ ItzTree@example.com
```

두 줄을 `ContactSection`이라는 Column 하나로 묶으면 부모 입장에서 자식이 하나가 된다.
묶음은 통째로 가운데에 놓이고, 묶음 안에서는 Column 기본값(`Start`)대로 왼쪽에 맞춰진다.
**정렬을 줄 단위로 걸지 말고, 원하는 단위로 박스를 먼저 만든 뒤 그 박스를 배치한다.**

### `weight`로 "가운데 + 아래" 배치

```kotlin
Column(horizontalAlignment = Alignment.CenterHorizontally) {
    BusinessCardProfile(modifier = Modifier.weight(1f))   // 남은 공간 전부
    ContactSection(modifier = Modifier.padding(bottom = 48.dp))
}
```

Column은 weight 없는 자식(`ContactSection`)부터 재고, 남은 세로 공간을 전부 `BusinessCardProfile`에 준다.
프로필 안쪽 Column의 `verticalArrangement = Center`가 그 공간 안에서 내용을 가운데로 보낸다.

정확히는 화면 정중앙이 아니라 **연락처를 뺀 나머지의 가운데**다. 명함으로는 이게 자연스럽고, 작은 화면에서도 겹치지 않는다.
화면 정중앙이 꼭 필요하면 `Box`에 `Alignment.Center` / `Alignment.BottomCenter`로 따로 배치할 수 있지만 겹칠 수 있다.

### 컴포저블을 뺄지 인라인으로 둘지

`ContactSection`은 한 번만 쓰이지만 뺐다. 옆에 `BusinessCardProfile`이 이미 분리돼 있어서,
한쪽만 날것의 Column이면 같은 층위의 두 영역이 서로 다른 모양으로 보인다.
**부모 함수 안에서는 추상화 수준을 맞춘다.**

빼는 게 나을 때: 여러 번 쓰일 때, 부모가 길어질 때, 붙일 이름이 분명할 때, 이미 분리된 형제가 있을 때.

### Material 아이콘 쓰기

material3 1.4부터 아이콘이 자동으로 딸려오지 않는다. 의존성을 직접 추가해야 한다.

```toml
# libs.versions.toml
androidx-compose-material-icons-core = { group = "androidx.compose.material", name = "material-icons-core" }
```

```kotlin
// app/build.gradle.kts — 버전은 BOM이 맞춰준다
implementation(libs.androidx.compose.material.icons.core)
```

아이콘은 **하나하나가 확장 프로퍼티**라 `Icons`만 import해서는 안 되고 아이콘마다 따로 import해야 한다.

```kotlin
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone   // 빠지면 Unresolved reference 'Phone'
```

### 자동 import가 엉뚱한 `Icon`을 끌어온다

`Icon`이라는 이름이 여러 군데 있다.

```kotlin
import android.graphics.drawable.Icon                            // ✗ 옛 View 시스템 클래스
import androidx.compose.material3.SegmentedButtonDefaults.Icon    // ✗ 버튼 전용 함수
import androidx.compose.material3.Icon                           // ✓
```

틀린 게 섞이면 "constructor is package-private" 같은 엉뚱한 에러가 난다.
자동 import 팝업에서 **`androidx.compose.material3`인지 확인**할 것.

또 `Icon`은 그리는 컴포저블이고 `Icons.Default.Phone`은 그릴 모양 데이터(`ImageVector`)다.
파라미터 타입은 `ImageVector`, 인자 이름은 `imageVector =`.

### 색상 16진수의 앞 두 자리는 알파

```
0x  00   FF   00   00
    A    R    G    B
```

`Color(0x00FF0000)`은 완전 투명한 빨강이라 칠해도 안 보인다. 불투명은 `0xFF...`.
CSS `#FF0000`처럼 RGB만 쓰던 습관으로 앞에 `00`을 붙이기 쉽다.

### 밝은 배경에 밝은 초록 글씨는 피한다

안드로이드 그린 `#3DDC84`는 흰 배경 위에서 명도 대비가 약 1.9:1이라 작은 글자가 잘 안 읽힌다.
밝은 배경에서 초록 **글씨**가 필요하면 `#006D3B` 같은 짙은 초록을 쓰고, `#3DDC84`는 아이콘처럼 크기가 있는 요소에만 쓴다.
