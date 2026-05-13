package com.genesys.feature.library

import com.genesys.core.domain.repository.library.LibraryBookOpener
import com.genesys.core.domain.repository.library.LibrarySnapshotLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LibraryFeatureModule {

    @Binds
    fun bindLibrarySnapshotLoader(
        impl: LibrarySnapshotLoaderImpl
    ): LibrarySnapshotLoader

    @Binds
    fun bindLibraryBookOpener(
        impl: BookOpener
    ): LibraryBookOpener
}
