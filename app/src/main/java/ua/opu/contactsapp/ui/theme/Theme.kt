package ua.opu.contactsapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Orange = Color(0xFFFF9800)
val ScreenBackground = Color(0xFFF5F5F5)
val CardBorder = Color(0xFFE0E0E0)
val SecondaryText = Color(0xFF666666)

private val LightColors = lightColorScheme(
    primary = Orange,
    onPrimary = Color.White,
    background = ScreenBackground,
    surface = Color.White,
    onSurface = Color.Black
)

@Composable
fun ContactsAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
