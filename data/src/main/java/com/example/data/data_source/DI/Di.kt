package com.example.data.data_source.DI

import com.example.data.data_source.local_data_source.LocalDataSource
import com.example.data.data_source.local_data_source.LocalDataSourceImpl
import com.example.data.data_source.remote_data_source.RemoteDataSource
import com.example.data.data_source.remote_data_source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {

    @Binds
    abstract fun bindRemoteDataSourceImpl(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}