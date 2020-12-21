package com.kycn.expoapp.characters.di

import android.app.Activity
import android.view.LayoutInflater
import com.kycn.expoapp.characters.datasource.CharactersRemoteDataSourceImpl
import com.kycn.expoapp.characters.repository.CharactersRepositoryImpl
import com.kycn.expoapp.characters.service.CharactersApi
import com.kycn.expoapp.characters.usecase.GetCharactersUseCase
import com.kycn.expoapp.common.view.GlideImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object CharactersModule {
    @Provides
    fun providesCharactersApi(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    @Provides
    fun providesCharactersRemoteDataSource(charactersApi: CharactersApi): CharactersRemoteDataSourceImpl {
        return CharactersRemoteDataSourceImpl(charactersApi)
    }

    @Provides
    fun providesCharactersRepository(charactersRemoteDataSourceImpl: CharactersRemoteDataSourceImpl): CharactersRepositoryImpl {
        return CharactersRepositoryImpl(charactersRemoteDataSourceImpl)
    }

    @Provides
    fun providesGetCharactersUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideLayoutInflater(activity: Activity) = LayoutInflater.from(activity)

    @Provides
    fun provideGlideImageLoader(activity: Activity) = GlideImageLoader(activity)
}
