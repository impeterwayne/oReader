package com.genesys.core.database.di

import android.content.Context
import androidx.room.Room
import com.genesys.core.database.NotebookDatabase
import com.genesys.core.database.dao.NotebookDao
import com.genesys.core.database.dao.NotebookFolderDao
import com.genesys.core.database.dao.NotebookImageDao
import com.genesys.core.database.dao.NotebookKeyValueDao
import com.genesys.core.database.dao.NotebookPageDao
import com.genesys.core.database.dao.NotebookStrokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotebookDatabaseModule {
    @Provides
    @Singleton
    fun provideNotebookDatabase(
        @ApplicationContext appContext: Context
    ): NotebookDatabase {
        return Room.databaseBuilder(
            appContext,
            NotebookDatabase::class.java,
            "oreader_notebook.db"
        ).build()
    }

    @Provides
    fun provideNotebookFolderDao(database: NotebookDatabase): NotebookFolderDao =
        database.notebookFolderDao()

    @Provides
    fun provideNotebookDao(database: NotebookDatabase): NotebookDao =
        database.notebookDao()

    @Provides
    fun provideNotebookPageDao(database: NotebookDatabase): NotebookPageDao =
        database.notebookPageDao()

    @Provides
    fun provideNotebookStrokeDao(database: NotebookDatabase): NotebookStrokeDao =
        database.notebookStrokeDao()

    @Provides
    fun provideNotebookImageDao(database: NotebookDatabase): NotebookImageDao =
        database.notebookImageDao()

    @Provides
    fun provideNotebookKeyValueDao(database: NotebookDatabase): NotebookKeyValueDao =
        database.notebookKeyValueDao()
}
