package ru.myapp.pexels_app.api

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.myapp.pexels_app.db.repository.impl.CategoryPicsRepositoryImpl
import ru.myapp.pexels_app.db.repository.impl.CuratedPicsRepositoryImpl
import ru.myapp.pexels_app.db.repository.impl.SearchPicsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
//    @Singleton
    fun providePexelsApi(): PexelsApi {
        return RetrofitClient.instance
    }

    @Provides
//    @Singleton
    fun provideCuratedPicsRepository(api: PexelsApi): CuratedPicsRepositoryImpl {
        return CuratedPicsRepositoryImpl(api)
    }

    @Provides
//    @Singleton
    fun provideCategoryPicsRepository(api: PexelsApi): CategoryPicsRepositoryImpl {
        return CategoryPicsRepositoryImpl(api)
    }

    @Provides
//    @Singleton
    fun provideSearchPicsRepository(api: PexelsApi): SearchPicsRepositoryImpl {
        return SearchPicsRepositoryImpl(api)
    }
}