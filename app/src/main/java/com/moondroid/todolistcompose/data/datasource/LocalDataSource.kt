package com.moondroid.todolistcompose.data.datasource

import com.moondroid.todolistcompose.data.model.entity.NoteEntity


interface LocalDataSource {
    suspend fun insertNote(note: NoteEntity): Long
    suspend fun deleteNote(note: NoteEntity): Int
    suspend fun getNotes(): List<NoteEntity>
}