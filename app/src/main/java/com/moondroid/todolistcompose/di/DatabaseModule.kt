package com.moondroid.todolistcompose.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.moondroid.todolistcompose.data.datasource.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext app: Context,
    ) = Room.databaseBuilder(
        app,
        NoteDatabase::class.java,
        "MyNote"
    ).build()

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase) = db.noteDao()

}