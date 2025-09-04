package com.ecemm.popcornia.di
import android.content.Context
import androidx.room.Room
import com.ecemm.popcornia.data.datasource.FilmsDataSource
import com.ecemm.popcornia.data.repo.FilmsRepository
import com.ecemm.popcornia.room.Database
import com.ecemm.popcornia.room.FilmsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//singleton, tek bir nesneyi pekçok yerde kullanabilmemize olanak sağlar
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFilmsDataSource(filmsDao:FilmsDao) : FilmsDataSource{
        return FilmsDataSource(filmsDao)
    }

    @Provides
    @Singleton
    fun provideFilmsRepository(filmsDataSource:FilmsDataSource) : FilmsRepository {
        return FilmsRepository(filmsDataSource)
    }

    @Provides
    @Singleton
    fun provideFilmsDao(@ApplicationContext context: Context) : FilmsDao{
        //todo: bu kısımda veritabanı ile ilgili tetikleme & çalıştırma & emülatöre kopyalama işlemleri yapılır
        val db= Room.databaseBuilder(context, Database::class.java, "popcornia.sqlite") //burası veritabanımıza erişimi sağlar
            .createFromAsset("popcornia.sqlite").build() //bu kısım ise sayfamıza kopyalama işlemini yapıyo
        return db.getFilmsDao()
    }
}