package com.moondroid.todolistcompose.data.repository

import com.moondroid.todolistcompose.data.datasource.LocalDataSource
import com.moondroid.todolistcompose.data.mapper.DataMapper.toNote
import com.moondroid.todolistcompose.data.mapper.DataMapper.toNoteEntity
import com.moondroid.todolistcompose.data.model.entity.NoteEntity
import com.moondroid.todolistcompose.domain.model.Note
import com.moondroid.todolistcompose.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : NoteRepository {
    override suspend fun insertNote(note: Note): Flow<Long> {
        return flow {
            emit(localDataSource.insertNote(note.toNoteEntity()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteNote(note: Note): Flow<Int> {
        return flow {
            emit(localDataSource.deleteNote(note.toNoteEntity()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return flow {
            localDataSource.getNotes().run {
                emit(this.map { it.toNote() })
            }
        }.flowOn(Dispatchers.IO)
    }
}
