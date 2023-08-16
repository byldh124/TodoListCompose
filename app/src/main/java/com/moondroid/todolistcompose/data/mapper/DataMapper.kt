package com.moondroid.todolistcompose.data.mapper

import com.moondroid.todolistcompose.data.model.entity.NoteEntity
import com.moondroid.todolistcompose.domain.model.Note

object DataMapper {
    fun NoteEntity.toNote(): Note =
        Note(id = id, description = description, date = date, boxColor = boxColor)

    fun Note.toNoteEntity(): NoteEntity =
        NoteEntity(description = description, date = date, boxColor = boxColor)
}