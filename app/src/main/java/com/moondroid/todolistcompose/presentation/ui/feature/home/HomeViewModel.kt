package com.moondroid.todolistcompose.presentation.ui.feature.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.usecase.GetNotesUseCase
import com.moondroid.todolistcompose.presentation.debug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
) : ViewModel() {

    private val _data: MutableState<List<Note>> = mutableStateOf(emptyList())
    val data: State<List<Note>> get() = _data

    fun getNote() {
        viewModelScope.launch {
            getNotesUseCase().collect { result ->
                debug("LIST : $result")
                _data.value = result
            }
        }
    }
}