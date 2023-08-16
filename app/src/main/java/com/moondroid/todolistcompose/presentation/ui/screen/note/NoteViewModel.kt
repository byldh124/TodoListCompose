package com.moondroid.todolistcompose.presentation.ui.screen.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moondroid.todolistcompose.common.BoxColor
import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.usecase.GetNotesUseCase
import com.moondroid.todolistcompose.domain.usecase.InsertNoteUseCase
import com.moondroid.todolistcompose.domain.usecase.UpdateNoteUseCase
import com.moondroid.todolistcompose.presentation.debug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
) : ViewModel() {

    private var tempNote: Note? = null

    private val _data = mutableStateOf("")
    val data: State<String> get() = _data

    private val _boxColor = mutableStateOf(BoxColor.getRandom())
    val boxColor: State<BoxColor> get() = _boxColor

    private val _saveDone = mutableStateOf(false)
    val saveDone: State<Boolean> get() = _saveDone

    fun setText(newData: String) {
        _data.value = newData
    }

    fun getNote(id: Int) {
        viewModelScope.launch {
            getNotesUseCase.getNote(id).collect { note ->
                tempNote = note
                note?.let {
                    _data.value = it.description
                    _boxColor.value = it.boxColor
                }
            }
        }
    }

    fun setBoxColor(boxColor: BoxColor) {
        _boxColor.value = boxColor
    }

    fun save() {
        tempNote?.let {
            it.description = _data.value
            it.boxColor = _boxColor.value

            update(it)
        } ?: run {
            val note = Note(
                id = 0,
                description = _data.value,
                date = System.currentTimeMillis(),
                boxColor = _boxColor.value
            )
            insert(note)
        }
    }

    private fun update(note: Note) {
        viewModelScope.launch {
            debug("UPDATE : $note")
            updateNoteUseCase.update(note).collect { long ->
                debug("RESULT : $long")
                _saveDone.value = true
            }
        }
    }

    private fun insert(note: Note) {
        viewModelScope.launch {
            debug("INSERT : $note")
            insertNoteUseCase.insertNote(note).collect { long ->
                debug("RESULT : $long")
                _saveDone.value = true
            }
        }
    }
}