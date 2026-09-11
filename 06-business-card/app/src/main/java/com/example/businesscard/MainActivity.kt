package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD2E8D4)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BusinessCardProfile(
            logo = painterResource(R.drawable.android_logo),
            fullName = stringResource(R.string.full_name),
            jobTitle = stringResource(R.string.job_title),
            modifier = Modifier.weight(1f)
        )
        ContactSection(modifier = Modifier.padding(bottom = 48.dp))
    }
}

@Composable
fun BusinessCardProfile(
    logo: Painter,
    fullName: String,
    jobTitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier
                .background(color = Color(0xFF073042))
                .size(144.dp)
        )
        Text(
            text = fullName,
            fontSize = 36.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFF1B1B1B),
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        )
        Text(
            text = jobTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF006D3B),
        )
    }
}

@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ContactRow(
            icon = Icons.Default.Phone,
            content = stringResource(R.string.phone_number),
        )
        ContactRow(
            icon = Icons.Default.Email,
            content = stringResource(R.string.email),
        )
    }
}

@Composable
fun ContactRow(
    icon: ImageVector,
    content: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF3DDC84),
        )
        Text(
            text = content,
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardAppPreview() {
    BusinessCardTheme {
        BusinessCardApp()
    }
}
