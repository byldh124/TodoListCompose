package com.moondroid.todolistcompose.presentation.ui.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.usecase.DeleteUseCase
import com.moondroid.todolistcompose.domain.usecase.GetNotesUseCase
import com.moondroid.todolistcompose.presentation.debug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteUseCase: DeleteUseCase,
) : ViewModel() {

    private val _data: MutableState<List<Note>> = mutableStateOf(emptyList())
    val data: State<List<Note>> get() = _data

    fun getNote() {
        viewModelScope.launch {
            getNotesUseCase().collect { result ->
                _data.value = result
            }
        }
    }

    fun delete(note : Note) {
        debug("DELETE : $note")
        viewModelScope.launch {
            deleteUseCase.delete(note).collect { response ->
                getNote()
            }
        }
    }
}