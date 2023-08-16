package com.moondroid.todolistcompose.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moondroid.todolistcompose.data.model.dao.NoteDao
import com.moondroid.todolistcompose.data.model.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}