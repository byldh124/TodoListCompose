package com.moondroid.todolistcompose.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moondroid.todolistcompose.common.BoxColor
import com.moondroid.todolistcompose.data.model.entity.NoteEntity
import com.moondroid.todolistcompose.presentation.debug
import kotlin.math.log

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Query("SELECT * FROM MyNote")
    suspend fun getNoteAll(): List<NoteEntity>

    @Query("UPDATE MyNote SET description=:description, boxColor=:boxColor WHERE id = :id")
    suspend fun update(id: Int, description: String, boxColor: BoxColor) : Int

    @Query("SELECT * FROM MyNote WHERE id = :id")
    suspend fun getNote(id: Int): NoteEntity

    @Delete
    suspend fun delete(noteEntity: NoteEntity): Int
}