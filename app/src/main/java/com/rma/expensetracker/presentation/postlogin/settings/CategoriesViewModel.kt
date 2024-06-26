package com.rma.expensetracker.presentation.postlogin.settings

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.ToastState
import com.rma.expensetracker.data.interactors.CategoryInteractor
import com.rma.expensetracker.data.interactors.RecordInteractor
import com.rma.expensetracker.data.models.mock.Category
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class CategoriesViewModel : ViewModel() {
    private val _currentUser: StateFlow<User?> =  CurrentUser.currentUser

    private val _categoriesList = MutableStateFlow(emptyList<Category>())
    val categoriesList: StateFlow<List<Category>> = _categoriesList

    private val _unusedCategories = MutableStateFlow(emptyList<Category>())
    val unusedCategories: StateFlow<List<Category>> = _unusedCategories

    private val _isDeleteDialogOpen = MutableStateFlow(false)
    val isDeleteDialogOpen: StateFlow<Boolean> = _isDeleteDialogOpen

    private val _isAddDialogOpen = MutableStateFlow(false)
    val isAddDialogOpen: StateFlow<Boolean> = _isAddDialogOpen

    private val _categoryToDeleteId = MutableStateFlow<String?>(null)

    private val _titleState = MutableStateFlow(
        InputFieldState(
            text = "",
            onTextChange = this::onTitleTextChange
        )
    )
    val titleState: StateFlow<InputFieldState> = _titleState

    private val _selectedColor = MutableStateFlow(Color.Red)
    val selectedColor: StateFlow<Color> = _selectedColor

    init {
        BottomNavBarIndicator.hideBottomNavBar()
        resetCategoriesScreen()
    }

    fun resetCategoriesScreen() {
        val categories = _currentUser.value?.let {
            CategoryInteractor.getCategoriesByUserId(it.id)
        }
        _categoriesList.value = categories?: emptyList()

        val unused: MutableList<Category> = mutableListOf()
        unused.addAll(_categoriesList.value)

        val allUserRecords = _currentUser.value?.let {
            RecordInteractor.getRecordsByUserId(it.id)
        }?: emptyList()

        unused.removeAll(allUserRecords.map { record -> record.category}.toSet())

        _unusedCategories.value = unused
    }
    private fun onTitleTextChange(newValue: String) {
        _titleState.update { it.copy(text = newValue) }
    }
    fun onUsedCategoryClicked() {
        ToastState.triggerToast("Kategorija je u upotrebi. Nije ju moguće izbrisati.")
    }

    fun onDeletableCategoryClicked(categoryId: String) {
        _isDeleteDialogOpen.value = true
        _categoryToDeleteId.value = categoryId
    }

    fun onDeleteDismissed() {
        _isDeleteDialogOpen.value = false
        _categoryToDeleteId.value = null
    }

    fun onDeleteConfirmed() {
        _categoryToDeleteId.value?.let { CategoryInteractor.deleteCategory(it) }
        onDeleteDismissed()
        resetCategoriesScreen()
        ToastState.triggerToast("Kategorija izbrisana.")
    }

    fun onAddClicked() {
        _isAddDialogOpen.value = true
    }

    fun onAddDismissed() {
        _isAddDialogOpen.value = false
    }

    fun onAddCategorySaved() {
        if(_currentUser.value == null) {
            onAddDismissed()
            ToastState.triggerToast("Greška")
            return
        }

        if(_titleState.value.text.isBlank()) {
            ToastState.triggerToast("Naziv ne smije biti prazan!")
            return
        }

        if(CategoryInteractor.findCategory(_currentUser.value!!.id, _titleState.value.text) != null) {
            ToastState.triggerToast("Kategorija tog naziva već postoji!")
            return
        }

        CategoryInteractor.addCategory(
            Category(
                id = UUID.randomUUID().toString(),
                title = titleState.value.text,
                color = _selectedColor.value,
                userId = _currentUser.value!!.id
            )
        )

        //dodaj kategoriju
        onAddDismissed()
        resetCategoriesScreen()
        ToastState.triggerToast("Kategorija dodana.")
    }

    fun onColorChange(color: Color) {
        _selectedColor.value = color
    }
}