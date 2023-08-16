package com.moondroid.todolistcompose.di

import com.moondroid.todolistcompose.data.datasource.LocalDataSource
import com.moondroid.todolistcompose.data.datasource.LocalDataSourceImpl
import com.moondroid.todolistcompose.data.repository.NoteRepositoryImpl
import com.moondroid.todolistcompose.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideNoteRepository(repository: NoteRepositoryImpl): NoteRepository

}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalBindModule {
    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}