package com.moondroid.todolistcompose.domain.usecase

import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.repository.NoteRepository
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
) {
    suspend fun insertNote(note: Note) = repository.insertNote(note)
}