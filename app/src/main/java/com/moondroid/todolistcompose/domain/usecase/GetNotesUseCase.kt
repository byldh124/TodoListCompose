package com.moondroid.todolistcompose.domain.usecase

import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.repository.NoteRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository,
) {
    suspend fun execute() = getNotes()
    suspend operator fun invoke() = getNotes()
    private suspend fun getNotes() = repository.getNotes()
}