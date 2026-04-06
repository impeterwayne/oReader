package com.genesys.core.data.di

import com.genesys.core.data.repository.notebook.NotebookFolderRepositoryImpl
import com.genesys.core.data.repository.notebook.NotebookImageRepositoryImpl
import com.genesys.core.data.repository.notebook.NotebookKeyValueRepositoryImpl
import com.genesys.core.data.repository.notebook.NotebookPageRepositoryImpl
import com.genesys.core.data.repository.notebook.NotebookRepositoryImpl
import com.genesys.core.data.repository.notebook.NotebookStrokeRepositoryImpl
import com.genesys.core.data.repository.template.TemplateRepositoryImpl
import com.genesys.core.domain.repository.notebook.NotebookFolderRepository
import com.genesys.core.domain.repository.notebook.NotebookImageRepository
import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.domain.repository.notebook.NotebookStrokeRepository
import com.genesys.core.domain.repository.template.TemplateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindTemplateRepository(
        templateRepositoryImpl: TemplateRepositoryImpl
    ): TemplateRepository

    @Binds
    fun bindNotebookRepository(
        impl: NotebookRepositoryImpl
    ): NotebookRepository

    @Binds
    fun bindNotebookPageRepository(
        impl: NotebookPageRepositoryImpl
    ): NotebookPageRepository

    @Binds
    fun bindNotebookStrokeRepository(
        impl: NotebookStrokeRepositoryImpl
    ): NotebookStrokeRepository

    @Binds
    fun bindNotebookImageRepository(
        impl: NotebookImageRepositoryImpl
    ): NotebookImageRepository

    @Binds
    fun bindNotebookFolderRepository(
        impl: NotebookFolderRepositoryImpl
    ): NotebookFolderRepository

    @Binds
    fun bindNotebookKeyValueRepository(
        impl: NotebookKeyValueRepositoryImpl
    ): NotebookKeyValueRepository
}
