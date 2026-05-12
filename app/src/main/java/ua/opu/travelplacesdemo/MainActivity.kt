package ua.opu.travelplacesdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.opu.travelplacesdemo.navigation.AppNavigation
import ua.opu.travelplacesdemo.ui.theme.TravelPlacesDemoTheme
import ua.opu.travelplacesdemo.ui.viewmodel.TravelPlacesViewModel

/**
 * Головна Activity застосунку.
 *
 * У Compose-застосунку Activity зазвичай тільки запускає тему, створює ViewModel
 * і передає її в навігаційний граф. Основна логіка розміщується не тут,
 * а у ViewModel та composable-екранах.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TravelPlacesDemoTheme {
                val travelPlacesViewModel: TravelPlacesViewModel = viewModel()

                AppNavigation(
                    viewModel = travelPlacesViewModel
                )
            }
        }
    }
}
