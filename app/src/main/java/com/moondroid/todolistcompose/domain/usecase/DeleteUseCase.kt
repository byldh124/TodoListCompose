package com.moondroid.todolistcompose.domain.usecase

import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val repository: NoteRepository,
) {
    suspend fun delete(note: Note) = repository.deleteNote(note)
}