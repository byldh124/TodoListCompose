package com.moondroid.todolistcompose.data.datasource

import com.moondroid.todolistcompose.data.model.dao.NoteDao
import com.moondroid.todolistcompose.data.model.entity.NoteEntity
import com.moondroid.todolistcompose.presentation.debug
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val noteDao: NoteDao,
) : LocalDataSource {
    override suspend fun insertNote(note: NoteEntity): Long {
        return noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity): Int {
        return noteDao.delete(note)
    }

    override suspend fun getNotes(): List<NoteEntity> {
        return noteDao.getNoteAll()
    }
}