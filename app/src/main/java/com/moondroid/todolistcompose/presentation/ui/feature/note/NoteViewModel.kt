package com.moondroid.todolistcompose.presentation.ui.feature.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moondroid.todolistcompose.common.BoxColor
import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.usecase.InsertNoteUseCase
import com.moondroid.todolistcompose.presentation.debug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
) : ViewModel() {
    init {
        insert("Hello Mr.Anderson")
    }

    private val _data = mutableStateOf("")
    val data: State<String> get() = _data

    fun setText(newData: String) {
        _data.value = newData
    }

    fun insert(noteMessage: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertNoteUseCase.insertNote(
                    Note(0, noteMessage, System.currentTimeMillis(), BoxColor.getRandom())
                ).collect { long ->
                    debug("RESULT : $long")
                }
            }
        }
    }
}