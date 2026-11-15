package ua.opu.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.opu.contactsapp.navigation.AppNavigation
import ua.opu.contactsapp.ui.theme.ContactsAppTheme
import ua.opu.contactsapp.ui.viewmodel.ContactsViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ContactsAppTheme {
                val contactsViewModel: ContactsViewModel = viewModel()
                AppNavigation(viewModel = contactsViewModel)
            }
        }
    }
}
