package com.moondroid.todolistcompose.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moondroid.todolistcompose.data.model.entity.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity): Long

    @Query("SELECT * FROM MyNote")
    fun getNoteAll(): List<NoteEntity>

    @Delete
    fun delete(noteEntity: NoteEntity): Int
}