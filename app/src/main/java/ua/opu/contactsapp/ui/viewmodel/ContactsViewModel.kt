package ua.opu.contactsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ua.opu.contactsapp.data.Contact

class ContactsViewModel : ViewModel() {

    private val _contacts = MutableStateFlow(
        listOf(
            Contact(1, "Марія Сидоренко", "+380 50 123 45 67", "maria@example.com"),
            Contact(2, "Іван Петренко", "+380 66 987 65 43", "ivan@example.com"),
            Contact(3, "Олена Коваленко", "+380 95 555 11 22", "")
        )
    )

    private val _contactsListUiState = MutableStateFlow(ContactsListUiState(_contacts.value))
    val contactsListUiState: StateFlow<ContactsListUiState> = _contactsListUiState.asStateFlow()

    private val _addEditContactUiState = MutableStateFlow(AddEditContactUiState())
    val addEditContactUiState: StateFlow<AddEditContactUiState> = _addEditContactUiState.asStateFlow()

    private val _contactDetailsUiState = MutableStateFlow(ContactDetailsUiState())
    val contactDetailsUiState: StateFlow<ContactDetailsUiState> = _contactDetailsUiState.asStateFlow()

    fun prepareNewContact() {
        _addEditContactUiState.value = AddEditContactUiState()
    }

    fun prepareEditContact(contactId: Int) {
        val contact = _contacts.value.firstOrNull { it.id == contactId } ?: return
        _addEditContactUiState.value = AddEditContactUiState(
            contactId = contact.id,
            name = contact.name,
            phone = contact.phone,
            email = contact.email,
            isEditMode = true
        )
    }

    fun prepareDetails(contactId: Int) {
        _contactDetailsUiState.value = ContactDetailsUiState(
            contact = _contacts.value.firstOrNull { it.id == contactId }
        )
    }

    fun updateName(name: String) {
        _addEditContactUiState.update { it.copy(name = name) }
    }

    fun updatePhone(phone: String) {
        _addEditContactUiState.update { it.copy(phone = phone) }
    }

    fun updateEmail(email: String) {
        _addEditContactUiState.update { it.copy(email = email) }
    }

    fun saveContact() {
        val state = _addEditContactUiState.value
        if (state.name.isBlank() || state.phone.isBlank()) return

        if (state.isEditMode) {
            updateExistingContact(state)
        } else {
            addNewContact(state)
        }
        refreshListState()
    }

    fun deleteContact(contactId: Int) {
        _contacts.update { contacts -> contacts.filterNot { it.id == contactId } }
        refreshListState()
    }

    private fun addNewContact(state: AddEditContactUiState) {
        val newContact = Contact(
            id = generateNextId(),
            name = state.name.trim(),
            phone = state.phone.trim(),
            email = state.email.trim()
        )
        _contacts.update { contacts -> contacts + newContact }
    }

    private fun updateExistingContact(state: AddEditContactUiState) {
        val contactId = state.contactId ?: return
        _contacts.update { contacts ->
            contacts.map { contact ->
                if (contact.id == contactId) {
                    contact.copy(
                        name = state.name.trim(),
                        phone = state.phone.trim(),
                        email = state.email.trim()
                    )
                } else {
                    contact
                }
            }
        }
        prepareDetails(contactId)
    }

    private fun refreshListState() {
        _contactsListUiState.value = ContactsListUiState(_contacts.value)
    }

    private fun generateNextId(): Int {
        return (_contacts.value.maxOfOrNull { it.id } ?: 0) + 1
    }
}
